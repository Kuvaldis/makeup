package kuvaldis.makeup.shared.module

import com.google.inject.AbstractModule
import com.google.inject.TypeLiteral
import kuvaldis.makeup.shared.config.PropertiesBuilder

import static com.google.inject.name.Names.named

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 14:52
 */
class SharedModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<List<String>>() {}).annotatedWith(named('config.files.list')).toInstance([
                'conf/server.groovy',
                'conf/db.groovy',
                'conf/profiles.groovy',
                'conf/security.groovy'
        ])
        bind(PropertiesBuilder)
    }
}
