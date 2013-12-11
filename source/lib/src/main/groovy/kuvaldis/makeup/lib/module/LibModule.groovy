package kuvaldis.makeup.lib.module

import com.google.inject.AbstractModule
import kuvaldis.makeup.lib.db.H2DataSourceProvider

import javax.sql.DataSource

/**
 * User: NFadin
 * Date: 11.12.13
 * Time: 17:08
 */
class LibModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DataSource).toProvider(H2DataSourceProvider).asEagerSingleton()
    }
}
