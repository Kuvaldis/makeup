package kuvaldis.makeup.lib.data

import com.google.inject.Inject
import groovy.sql.Sql
import kuvaldis.makeup.lib.annotation.MainDataSource

import javax.sql.DataSource

/**
 * @author Kuvaldis
 * Create date: 13.12.13 23:15
 */
class SqlHolder {

    @Inject @MainDataSource
    private DataSource dataSource

    private ThreadLocal<Sql> sqlThreadLocal = new ThreadLocal<>();

    def getSql() {
        def sql = sqlThreadLocal.get()
        if (!sql) {
            sql = new Sql(dataSource)
            sqlThreadLocal.set(sql)
        }
        return sql
    }

}
