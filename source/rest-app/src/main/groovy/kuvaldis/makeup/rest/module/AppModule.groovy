package kuvaldis.makeup.rest.module

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import kuvaldis.makeup.rest.server.GrizzlyServer

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 15:58
 */
class AppModule extends AbstractModule {

    Properties properties

    AppModule(Properties properties) {
        this.properties = properties
    }

    @Override
    protected void configure() {
        bind(GrizzlyServer)
        Names.bindProperties(binder(), properties)
    }
}
