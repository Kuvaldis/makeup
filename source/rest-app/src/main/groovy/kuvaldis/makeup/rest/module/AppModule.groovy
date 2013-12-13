package kuvaldis.makeup.rest.module

import com.google.inject.AbstractModule
import com.google.inject.TypeLiteral
import com.google.inject.matcher.AbstractMatcher
import com.google.inject.name.Names
import com.google.inject.spi.InjectionListener
import com.google.inject.spi.TypeEncounter
import com.google.inject.spi.TypeListener
import kuvaldis.makeup.rest.annotation.DbServer
import kuvaldis.makeup.rest.annotation.HttpServer
import kuvaldis.makeup.rest.server.GrizzlyServer
import kuvaldis.makeup.rest.server.H2Server
import kuvaldis.makeup.rest.server.Server
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
        bind(Server).annotatedWith(HttpServer).to(GrizzlyServer)
        bindDbServer()
        Names.bindProperties(binder(), propertiesHolder.toProperties())
    }

    private void bindDbServer() {
        bind(Server).annotatedWith(DbServer).to(H2Server).asEagerSingleton()
        bindListener({ TypeLiteral<?> t ->
            H2Server.isAssignableFrom(t.getRawType())
        } as AbstractMatcher, { TypeLiteral<?> type, TypeEncounter<?> encounter ->
            if (H2Server.isAssignableFrom(type.getRawType())) {
                (encounter as TypeEncounter<H2Server>).register({ H2Server s ->
                    s.start()
                } as InjectionListener<H2Server>)
            }
        } as TypeListener)
    }
}
