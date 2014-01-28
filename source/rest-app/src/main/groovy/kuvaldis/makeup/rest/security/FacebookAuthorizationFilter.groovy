package kuvaldis.makeup.rest.security

import com.google.inject.Inject
import io.buji.pac4j.filter.ClientRolesAuthorizationFilter
import org.pac4j.oauth.client.FacebookClient

/**
 * @author Kuvaldis
 * Create date: 17.12.13 3:04
 */
class FacebookAuthorizationFilter extends ClientRolesAuthorizationFilter {

    @Inject
    FacebookAuthorizationFilter(ClientsProvider clientsProvider) {
        super()
        client = clientsProvider.getClient(FacebookClient.class.simpleName)
    }
}
