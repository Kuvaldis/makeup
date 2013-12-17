package kuvaldis.makeup.rest.security

import com.google.inject.Inject
import io.buji.pac4j.filter.ClientRolesAuthorizationFilter
import org.pac4j.core.client.BaseClient
import org.pac4j.core.credentials.Credentials
import org.pac4j.core.profile.CommonProfile
import org.pac4j.http.client.BasicAuthClient

/**
 * @author Kuvaldis
 * Create date: 17.12.13 3:04
 */
class BaseAuthorizationFilter extends ClientRolesAuthorizationFilter {

    @Inject
    BaseAuthorizationFilter(BasicAuthClient client) {
        super()
        this.client = client as BaseClient<Credentials, CommonProfile>
    }
}
