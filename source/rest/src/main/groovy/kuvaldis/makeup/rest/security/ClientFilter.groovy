package kuvaldis.makeup.rest.security

import com.google.inject.Inject
import com.google.inject.name.Named
import org.pac4j.core.client.Clients

/**
 * @author Kuvaldis
 * Create date: 17.12.13 2:27
 */
class ClientFilter extends io.buji.pac4j.ClientFilter {

    @Inject
    ClientFilter(Clients clients, @Named('security.failure.url') String failureUrl) {
        super()
        this.clients = clients
        this.failureUrl = failureUrl
    }
}
