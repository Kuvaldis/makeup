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

    SecurityModule(ServletContext servletContext) {
        super(servletContext)
    }

    @Override
    protected void configureShiroWeb() {
        install(new ShiroAopModule())

        bind(String).annotatedWith(named('security.url.callback')).toInstance('http://localhost:8080/callback');
        bind(String).annotatedWith(named('security.url.failure')).toInstance('/failure');

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
    Clients clients(@Named('security.url.callback') String callbackUrl, ClientsProvider clientsProvider) {
        return new Clients(callbackUrl: callbackUrl,
                clients: clientsProvider.clients);
    }

    @Provides
    @com.google.inject.Singleton
    ClientsProvider clientsProvider() {
        new ClientsProvider(clients: [new FacebookClient(key: '145278422258960', secret: 'be21409ba8f39b5dae2a7de525484da8')])
    }


}
