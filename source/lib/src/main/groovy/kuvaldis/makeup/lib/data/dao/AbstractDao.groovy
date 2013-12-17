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

    final String selectStatement
    final String deleteStatement
    final String createStatementP1
    final String createStatementP2 = ") values ("
    final String createStatementP3 = ")"
    final String updateStatement

    protected sql() {
        sqlHolder.sql
    }

    public AbstractDao() {
        Type t = this.class.genericSuperclass;
        ParameterizedType pt = (ParameterizedType) t;
        domainClass = pt.actualTypeArguments[0] as Class<T>;
        idFieldName = calculateIdFieldName()
        tableName = calculateTableName()
        selectStatement = "select * from $tableName where "
        deleteStatement = "delete from $tableName where "
        createStatementP1 = "insert into $tableName ("
        updateStatement = "update $tableName set "
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

    def T toDomain(GroovyRowResult groovyRowResult) {
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

    static def prepareValue(def p) {
        if (p instanceof Enum) {
            return p.name()
        }
        return p
    }

    @Override
    T find(Object id) {
        executeSelect([(idFieldName): id])
    }

    private static execute(Map<String, ?> fieldValueMap, Closure cb) {
        def params = [:]
        def fieldToParam = [:]
        fieldValueMap.each { k, v ->
            params << [("${k}Param".toString()): prepareValue(v)]
            fieldToParam << [("${dbLikeFieldName(k)}".toString()): "${k}Param".toString()]
        }
        cb.call(fieldToParam, params)
    }

    protected T executeSelect(Map<String, ?> fieldValueMap) {
        execute(fieldValueMap) { wr, p ->
            toDomain(sql().firstRow(selectStatement + toAndSeparatedKVString(wr), p as Map))
        } as T
    }

    static String toAndSeparatedKVString(wr) {
        toSeparatedString(wr, ' and ')
    }

    static String toCommaSeparatedKVString(wr) {
        toSeparatedString(wr, ', ')
    }

    static String toSeparatedString(wr, String separator) {
        wr.collect { k, v ->
            "$k = :$v"
        }.join(separator)
    }

    protected List<List> executeCreate(Map<String, ?> fieldValueMap) {
        execute(fieldValueMap) { Map f2v, p ->
            sql().executeInsert(createStatementP1 + "${f2v.keySet().join(', ')}" +
                    createStatementP2 + "${f2v.values().collect { ":$it" }.join(', ')}" +
                    createStatementP3, p as Map)

        } as List<List>
    }

    @Override
    T create(T t) {
        def fieldValueMap = [:]
        t.properties.entrySet().each {
            if (it.key == 'class') return
            if (it.key == idFieldName && !it.value) return
            fieldValueMap << [(dbLikeFieldName(it.key as String)): prepareValue(it.value)]
        }
        def ids = executeCreate(fieldValueMap)
        if (ids) {
            t.metaClass.setProperty(t, idFieldName, ids[0][0])
        }
        return t
    }

    protected executeUpdate(Map<String, ?> fieldValueMap) {
        execute(fieldValueMap) { Map f2v, p ->
            sql().executeUpdate(updateStatement + toCommaSeparatedKVString(f2v))
        }
    }

    @Override
    def update(T t) {
        def fieldValueMap = [:]
        t.properties.each { k, v ->
            if (k == idFieldName || k == 'class') return
            fieldValueMap << [k, v]
        }
        executeUpdate(fieldValueMap)
    }

    protected void executeDelete(Map<String, ?> fieldValueMap) {
        execute(fieldValueMap) { pref, wr, p ->
            sql().execute(deleteStatement + toAndSeparatedKVString(wr), p as Map)
        }
    }

    @Override
    def delete(Object id) {
        executeDelete([(idFieldName): id])
    }


}
