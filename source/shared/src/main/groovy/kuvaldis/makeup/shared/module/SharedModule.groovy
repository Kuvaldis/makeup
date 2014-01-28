package kuvaldis.makeup.shared.module

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import kuvaldis.makeup.shared.config.PropertiesBuilder
import kuvaldis.makeup.shared.config.PropertiesHolder

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 14:52
 */
class SharedModule extends AbstractModule {
    @Override
    protected void configure() {
        PropertiesHolder propertiesHolder = PropertiesBuilder.build([
                'conf/server.groovy',
                'conf/db.groovy',
                'conf/profiles.groovy',
                'conf/security.groovy'
        ])
        bind(PropertiesHolder).toInstance(propertiesHolder)
        Names.bindProperties(binder(), propertiesHolder.toProperties())
    }
}
