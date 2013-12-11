package kuvaldis.makeup.rest.module

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import kuvaldis.makeup.rest.server.GrizzlyServer
import kuvaldis.makeup.shared.config.PropertiesHolder

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 15:58
 */
class AppModule extends AbstractModule {

    def PropertiesHolder propertiesHolder

    @Override
    protected void configure() {
        bind(PropertiesHolder).toInstance(propertiesHolder)
        bind(GrizzlyServer)
        Names.bindProperties(binder(), propertiesHolder.toProperties())
    }
}
