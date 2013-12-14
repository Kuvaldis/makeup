package kuvaldis.makeup.lib.data.dao

import com.google.inject.Inject
import groovy.sql.GroovyRowResult
import kuvaldis.makeup.lib.annotation.Id
import kuvaldis.makeup.lib.annotation.Table
import kuvaldis.makeup.lib.data.SqlHolder

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

    def sql() {
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
            tableName = domainClass.simpleName.toLowerCase()
        }
        return tableName
    }

    private static String calculateFieldName(Field field) {
        field.name
    }

    private T toDomain(GroovyRowResult groovyRowResult) {
        def result = domainClass.newInstance()
        def clazz = result.metaClass
        clazz.properties.each { String k, v ->
            clazz.setProperty(result, k, groovyRowResult.getProperty(k))
        }
        return result
    }

    private String getCreateStatement(T t) {
        def fields = []
        def values = []
        t.properties.entrySet().each {
            if (it.key == 'class' || it.key == 'id') return
            fields << it.key
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
        toDomain(sql().firstRow("select * from $tableName where $idFieldName = $id"))
    }

    @Override
    T create(T t) {
        def ids = sql().executeInsert(getCreateStatement(t))
        t.metaClass.setProperty(t, idFieldName, ids[0][0])
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