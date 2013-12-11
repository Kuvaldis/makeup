package kuvaldis.makeup.rest.module

import org.apache.shiro.guice.web.ShiroWebModule
import org.apache.shiro.realm.text.PropertiesRealm

import javax.servlet.ServletContext

/**
 * @author Kuvaldis
 * Create date: 12.12.13 1:45
 */
class SecurityModule extends ShiroWebModule {

    SecurityModule(ServletContext servletContext) {
        super(servletContext)
    }

    @Override
    protected void configureShiroWeb() {
        bindRealm().toInstance(new PropertiesRealm().with {
            useXmlFormat = true
            resourcePath = 'classpath:shiro.xml'
            return it
        })
        AUTHC_BASIC
    }
}
