package kuvaldis.makeup.shared.config

/**
 * User: NFadin
 * Date: 11.12.13
 * Time: 12:52
 */
class PropertiesHolder {

    private ConfigObject configObject

    public Properties toProperties() {
        configObject.toProperties()
    }
}
