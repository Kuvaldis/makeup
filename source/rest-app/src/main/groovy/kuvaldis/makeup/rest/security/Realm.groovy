package kuvaldis.makeup.rest.security

import com.google.inject.Inject
import io.buji.pac4j.ClientRealm
import org.pac4j.core.client.Clients

/**
 * @author Kuvaldis
 * Create date: 26.01.14 20:04
 */
class Realm extends ClientRealm {

    @Inject
    Realm(Clients clients) {
        super()
        this.clients = clients
        this.defaultRoles = 'ROLE_USER'
    }
}
