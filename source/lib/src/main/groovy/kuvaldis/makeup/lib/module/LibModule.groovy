package kuvaldis.makeup.lib.module

import com.google.inject.AbstractModule
import com.google.inject.TypeLiteral
import com.google.inject.matcher.AbstractMatcher
import com.google.inject.multibindings.Multibinder
import com.google.inject.spi.InjectionListener
import com.google.inject.spi.TypeEncounter
import com.google.inject.spi.TypeListener
import kuvaldis.makeup.lib.annotation.MainDataSource
import kuvaldis.makeup.lib.data.dao.AuthWayDao
import kuvaldis.makeup.lib.data.dao.ProfileDao
import kuvaldis.makeup.lib.data.dao.UserAuthDao
import kuvaldis.makeup.lib.data.dao.UserDao
import kuvaldis.makeup.lib.job.*
import kuvaldis.makeup.lib.module.provider.H2DataSourceProvider
import kuvaldis.makeup.lib.service.AuthService
import kuvaldis.makeup.lib.service.UserService
import kuvaldis.makeup.lib.sql.SqlHolder

import javax.sql.DataSource

/**
 * User: NFadin
 * Date: 11.12.13
 * Time: 17:08
 */
class LibModule extends AbstractModule {

    @Override
    protected void configure() {
        bindDataObjects()
        bindServices()
        bindJobs()
    }

    private void bindDataObjects() {
        bind(DataSource).annotatedWith(MainDataSource).toProvider(H2DataSourceProvider).asEagerSingleton()
        bind(SqlHolder)
        bindDao()
    }

    private void bindDao() {
        bind(UserDao)
        bind(AuthWayDao)
        bind(UserAuthDao)
        bind(ProfileDao)
    }

    void bindServices() {
        bind(UserService)
        bind(AuthService)
    }

    private void bindJobs() {
        Multibinder<Job> jobsBinder = Multibinder.newSetBinder(binder(), Job)
        jobsBinder.addBinding().to(DbMigrationJob)
        jobsBinder.addBinding().to(AddAdminJob)
        jobsBinder.addBinding().to(AddDataJob)
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
