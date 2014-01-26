package kuvaldis.makeup.rest.module

import com.sun.jersey.guice.JerseyServletModule
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer
import kuvaldis.makeup.rest.resource.FacebookAuthResource
import kuvaldis.makeup.rest.resource.UserResource

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 14:22
 */
class RestModule extends JerseyServletModule {
    @Override
    protected void configureServlets() {
        bind(UserResource)
        bind(FacebookAuthResource)
        serve("/*").with(GuiceContainer)
    }
}
