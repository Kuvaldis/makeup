package kuvaldis.makeup.rest.module

import kuvaldis.makeup.rest.security.AuthCredentialsMatcher
import kuvaldis.makeup.rest.security.AuthRealm
import org.apache.shiro.authc.credential.CredentialsMatcher
import org.apache.shiro.guice.web.ShiroWebModule

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
        bind(CredentialsMatcher).to(AuthCredentialsMatcher)
        bindRealm().to(AuthRealm)
    }
}
