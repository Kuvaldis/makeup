package kuvaldis.makeup.shared.module

import com.google.inject.AbstractModule
import com.google.inject.Provides
import kuvaldis.makeup.shared.config.PropertiesBuilder

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 14:52
 */
class SharedModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PropertiesBuilder)
    }

    @Provides
    public List<String> configFilesList() {
        [
                'conf/server.groovy',
                'conf/db.groovy',
                'conf/profiles.groovy'
        ]
    }
}
