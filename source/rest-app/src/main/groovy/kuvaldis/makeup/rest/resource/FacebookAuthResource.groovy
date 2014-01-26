package kuvaldis.makeup.rest.resource

import com.google.inject.servlet.RequestScoped

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 14:10
 */
@Path('/auth/facebook')
@RequestScoped
class FacebookAuthResource {

    @GET
    @Produces('application/json')
    public String post() {
        '123'
    }
}
