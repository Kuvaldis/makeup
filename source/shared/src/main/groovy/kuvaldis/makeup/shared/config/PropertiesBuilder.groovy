package kuvaldis.makeup.shared.config

import com.google.inject.Inject

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 16:50
 */
class PropertiesBuilder {

    @Inject
    private List<String> configFilesList;

    public PropertiesHolder build() {
        ConfigObject resultConfig
        configFilesList.each {
            def config = new ConfigSlurper().parse(new File(it).toURI().toURL())
            if (!resultConfig) {
                resultConfig = config
            } else {
                resultConfig.merge(config)
            }
        }
        new PropertiesHolder(configObject: resultConfig)
    }
}
