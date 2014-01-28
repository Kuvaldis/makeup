package kuvaldis.makeup.rest.module

import com.google.inject.Key
import com.google.inject.Provides
import com.google.inject.name.Named
import kuvaldis.makeup.rest.security.ClientFilter
import kuvaldis.makeup.rest.security.ClientsProvider
import kuvaldis.makeup.rest.security.FacebookAuthorizationFilter
import kuvaldis.makeup.rest.security.Realm
import org.apache.shiro.guice.aop.ShiroAopModule
import org.apache.shiro.guice.web.ShiroWebModule
import org.pac4j.core.client.Clients
import org.pac4j.oauth.client.FacebookClient

import javax.servlet.ServletContext

import static com.google.inject.name.Names.named

/**
 * @author Kuvaldis
 * Create date: 12.12.13 1:45
 */
class SecurityModule extends ShiroWebModule {

    def ServletContext servletContext

    SecurityModule(ServletContext servletContext) {
        super(servletContext)
        this.servletContext = servletContext
    }

    @Override
    protected void configureShiroWeb() {
        install(new ShiroAopModule())

        bind(String).annotatedWith(named('security.failure.url')).toInstance('/failure');

        bindRealm().to(Realm)

        def ROLE_USER = 'ROLE_USER'
        def facebook = Key.get(FacebookAuthorizationFilter)
        addFilterChain('/auth/facebook', facebook, config(facebook, ROLE_USER))

        addFilterChain('/callback', Key.get(ClientFilter))
        addFilterChain('/logout', LOGOUT)
        addFilterChain('/**', ANON)
    }

    @Provides
    @com.google.inject.Singleton
    Clients clients(@Named('security.oauth.callback.url') String callbackUrl, ClientsProvider clientsProvider) {
        return new Clients(callbackUrl: callbackUrl,
                clients: clientsProvider.clients);
    }

    @Provides
    @com.google.inject.Singleton
    ClientsProvider clientsProvider(@Named('security.oauth.facebook.key') String key, @Named('security.oauth.facebook.secret') String secret) {
        new ClientsProvider(clients: [new FacebookClient(key: key, secret: secret)])
    }


}
