package kuvaldis.makeup.lib.job

import com.google.inject.Inject
import com.googlecode.flyway.core.Flyway
import kuvaldis.makeup.lib.annotation.MainDataSource

import javax.sql.DataSource

/**
 * User: NFadin
 * Date: 13.12.13
 * Time: 13:39
 */
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
        flyway.migrate()
    }
}
