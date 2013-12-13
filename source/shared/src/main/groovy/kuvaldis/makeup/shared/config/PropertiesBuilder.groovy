package kuvaldis.makeup.shared.config

import com.google.inject.Inject
import groovy.util.logging.Slf4j

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 16:50
 */
@Slf4j
@com.google.inject.Singleton
class PropertiesBuilder {

    @Inject
    private List<String> configFilesList;

    public PropertiesHolder build() {
        def env = System.getProperty("env")
        log.info('Run with profile: {}', env)
        ConfigObject resultConfig
        configFilesList.each {
            def config = new ConfigSlurper(env).parse(new File(it).toURI().toURL())
            if (!resultConfig) {
                resultConfig = config
            } else {
                resultConfig.merge(config)
            }
        }
        new PropertiesHolder(configObject: resultConfig)
    }
}
