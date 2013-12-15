package kuvaldis.makeup.lib.data.dao

import com.google.common.base.CaseFormat
import com.google.inject.Inject
import groovy.sql.GroovyRowResult
import kuvaldis.makeup.lib.annotation.Id
import kuvaldis.makeup.lib.annotation.Table
import kuvaldis.makeup.lib.sql.SqlHolder

import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author Kuvaldis
 * Create date: 13.12.13 23:54
 */
abstract class AbstractDao<T> implements Dao<T> {

    @Inject
    private SqlHolder sqlHolder

    def Class<T> domainClass

    protected String idFieldName

    protected String tableName

    protected sql() {
        sqlHolder.sql
    }

    public AbstractDao() {
        Type t = this.class.genericSuperclass;
        ParameterizedType pt = (ParameterizedType) t;
        domainClass = pt.actualTypeArguments[0] as Class<T>;
        idFieldName = calculateIdFieldName()
        tableName = calculateTableName()
    }

    private def calculateIdFieldName() {
        calculateFieldName(domainClass.declaredFields.find {
            it.annotations*.annotationType().contains(Id)
        })
    }

    private def calculateTableName() {
        def tableName
        def tableAn = domainClass.getAnnotation(Table)
        if (tableAn) {
            tableName = tableAn.value()
        } else {
            tableName = domainClass.simpleName
        }
        return dbLikeTableName(tableName)
    }

    private static String calculateFieldName(Field field) {
        dbLikeFieldName(field.name)
    }

    def static String dbLikeFieldName(String s) {
        CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s)
    }

    def static String dbLikeTableName(String s) {
        CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s)
    }

    private T toDomain(GroovyRowResult groovyRowResult) {
        if (!groovyRowResult) {
            return null
        }
        def result = domainClass.newInstance()
        def clazz = result.metaClass
        clazz.properties.each {
            if (it.name == 'class') return
            clazz.setProperty(result, it.name, groovyRowResult.getProperty(dbLikeFieldName(it.name)))
        }
        return result
    }

    private String getCreateStatement(T t) {
        def fields = []
        def values = []
        t.properties.entrySet().each {
            if (it.key == 'class') return
            if (it.key == idFieldName && !it.value) return
            fields << dbLikeFieldName(it.key as String)
            values << it.value
        }
        "insert into $tableName(${fields.join(',')}) values('${values.join('\',\'')}')"
    }

    private String getUpdateStatement(T t) {
        def list = []
        t.properties.each { k, v ->
            if (k == idFieldName) return
            list << "$k = $v"
        }
        "update $tableName set ${list.join(',')}"
    }

    @Override
    T find(Object id) {
        executeSelect("$idFieldName = '$id'")
    }

    protected T executeSelect(String whereStatement) {
        toDomain(sql().firstRow("select * from $tableName where $whereStatement".toString()))
    }

    @Override
    T create(T t) {
        def ids = sql().executeInsert(getCreateStatement(t))
        if (ids) {
            t.metaClass.setProperty(t, idFieldName, ids[0][0])
        }
        return t
    }

    @Override
    def update(T t) {
        sql().executeUpdate(getUpdateStatement(t))
    }

    @Override
    def delete(Object id) {
        sql().execute("delete from $tableName where $idFieldName = $id")
    }


}
