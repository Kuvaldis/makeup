package kuvaldis.makeup.lib.job

import com.google.inject.Inject
import com.googlecode.flyway.core.Flyway
import groovy.util.logging.Slf4j
import kuvaldis.makeup.lib.annotation.MainDataSource

import javax.sql.DataSource

/**
 * User: NFadin
 * Date: 13.12.13
 * Time: 13:39
 */
@Slf4j
@com.google.inject.Singleton
class DbMigrationJob extends AbstractJob {

    private Flyway flyway

    @Inject
    DbMigrationJob(@MainDataSource DataSource dataSource) {
        flyway = new Flyway(dataSource: dataSource)
    }

    @Override
    JobPriority getPriority() {
        return JobPriority.BIG
    }

    @Override
    void runJob() {
        log.info('Start db migration job')
        flyway.migrate()
        log.info('Db migration job is done')
    }
}
