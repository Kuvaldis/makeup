package kuvaldis.makeup.lib.module.provider

import com.google.inject.Inject
import com.google.inject.Provider
import com.google.inject.name.Named
import com.jolbox.bonecp.BoneCPDataSource

import javax.sql.DataSource

/**
 * User: NFadin
 * Date: 11.12.13
 * Time: 17:11
 */
class H2DataSourceProvider implements Provider<DataSource> {

    @Inject @Named('db.url')
    def String url
    @Inject @Named('db.username')
    def String username
    @Inject @Named('db.password')
    def String password

    @Override
    DataSource get() {
        new BoneCPDataSource().with {
            jdbcUrl = this.url
            username = this.username
            password = this.password
            idleConnectionTestPeriodInMinutes = 60
            idleMaxAgeInMinutes = 240
            maxConnectionsPerPartition = 30
            minConnectionsPerPartition = 10
            partitionCount = 3
            acquireIncrement = 5
            statementsCacheSize = 100
            return it
        }
    }
}
