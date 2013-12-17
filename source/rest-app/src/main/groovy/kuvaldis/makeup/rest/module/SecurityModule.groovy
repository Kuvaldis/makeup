package kuvaldis.makeup.rest.module

import com.google.inject.Key
import com.google.inject.Provides
import com.google.inject.multibindings.Multibinder
import com.google.inject.name.Named
import io.buji.pac4j.ClientRealm
import kuvaldis.makeup.rest.security.BaseAuthorizationFilter
import kuvaldis.makeup.rest.security.ClientFilter
import org.apache.shiro.guice.aop.ShiroAopModule
import org.apache.shiro.guice.web.ShiroWebModule
import org.pac4j.core.client.Client
import org.pac4j.core.client.Clients
import org.pac4j.http.client.BasicAuthClient
import org.pac4j.http.credentials.SimpleTestUsernamePasswordAuthenticator
import org.pac4j.http.credentials.UsernamePasswordAuthenticator

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

        bind(String).annotatedWith(named('security.url.callback')).toInstance('/callback');
        bind(String).annotatedWith(named('security.url.failure')).toInstance('/failure');

        bindRealm().to(ClientRealm)
        Multibinder<Client> clientsBinder = Multibinder.newSetBinder(binder(), Client)
        clientsBinder.addBinding().to(BasicAuthClient)
        bind(UsernamePasswordAuthenticator).to(SimpleTestUsernamePasswordAuthenticator)

        addFilterChain('/callback', Key.get(ClientFilter))
        addFilterChain('/logout', LOGOUT)
        addFilterChain('/**', ANON)
        addFilterChain('/admin/**', Key.get(BaseAuthorizationFilter))
    }

    @Provides
    @com.google.inject.Singleton
    Clients clients(@Named('security.url.callback') String callbackUrl, List<Client> clients) {
        new Clients(callbackUrl, clients)
    }

    @Provides
    @com.google.inject.Singleton
    List<Client> clients(Set<Client> clients) {
        clients.toList()
    }

    @Provides
    BasicAuthClient basicAuthClient(UsernamePasswordAuthenticator usernamePasswordAuthenticator) {
        new BasicAuthClient(usernamePasswordAuthenticator)
    }

    @Provides
    @com.google.inject.Singleton
    ClientRealm clientRealm(Clients clients) {
        new ClientRealm(clients: clients)
    }
}
