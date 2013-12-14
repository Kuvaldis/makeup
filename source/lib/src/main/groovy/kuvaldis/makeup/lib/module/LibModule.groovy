package kuvaldis.makeup.lib.module

import com.google.inject.AbstractModule
import com.google.inject.TypeLiteral
import com.google.inject.matcher.AbstractMatcher
import com.google.inject.multibindings.Multibinder
import com.google.inject.spi.InjectionListener
import com.google.inject.spi.TypeEncounter
import com.google.inject.spi.TypeListener
import kuvaldis.makeup.lib.annotation.MainDataSource
import kuvaldis.makeup.lib.data.SqlHolder
import kuvaldis.makeup.lib.data.dao.AuthWayDao
import kuvaldis.makeup.lib.data.dao.UserAuthDao
import kuvaldis.makeup.lib.data.dao.UserDao
import kuvaldis.makeup.lib.job.AddAdminJob
import kuvaldis.makeup.lib.job.DbMigrationJob
import kuvaldis.makeup.lib.job.Job
import kuvaldis.makeup.lib.job.JobExecutor
import kuvaldis.makeup.lib.module.provider.H2DataSourceProvider

import javax.sql.DataSource

/**
 * User: NFadin
 * Date: 11.12.13
 * Time: 17:08
 */
class LibModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DataSource).annotatedWith(MainDataSource).toProvider(H2DataSourceProvider).asEagerSingleton()
        bindDataObjects()
        bindJobs()
    }

    private void bindDataObjects() {
        bind(SqlHolder)
        bindDao()
        bindServices()
    }

    private void bindDao() {
        bind(UserDao)
        bind(AuthWayDao)
        bind(UserAuthDao)
    }

    void bindServices() {

    }

    private void bindJobs() {
        Multibinder<Job> jobsBinder = Multibinder.newSetBinder(binder(), Job)
        jobsBinder.addBinding().to(DbMigrationJob)
        jobsBinder.addBinding().to(AddAdminJob)
        bindJobExecutor()
    }

    private void bindJobExecutor() {
        bind(JobExecutor).asEagerSingleton()
        bindListener({ TypeLiteral<?> t ->
            JobExecutor.isAssignableFrom(t.getRawType())
        } as AbstractMatcher, { TypeLiteral<?> type, TypeEncounter<?> encounter ->
            if (JobExecutor.isAssignableFrom(type.getRawType())) {
                (encounter as TypeEncounter<JobExecutor>).register({ JobExecutor je ->
                    je.runJobs()
                } as InjectionListener<JobExecutor>)
            }
        } as TypeListener)
    }
}
