package kuvaldis.makeup.shared.config

import groovy.util.logging.Slf4j

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 16:50
 */
@Slf4j
class PropertiesBuilder {

    public static PropertiesHolder build(List<String> configFilesList) {
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
