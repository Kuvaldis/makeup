package kuvaldis.makeup.shared.config

/**
 * User: NFadin
 * Date: 11.12.13
 * Time: 12:52
 */
@com.google.inject.Singleton
class PropertiesHolder {

    private ConfigObject configObject

    public Properties toProperties() {
        configObject.toProperties()
    }
}
