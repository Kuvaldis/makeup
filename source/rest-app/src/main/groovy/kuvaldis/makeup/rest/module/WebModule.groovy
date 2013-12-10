package kuvaldis.makeup.rest.module

import com.sun.jersey.guice.JerseyServletModule
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer
import kuvaldis.makeup.rest.resource.UserResource

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 14:22
 */
class WebModule extends JerseyServletModule {
    @Override
    protected void configureServlets() {
        bind(UserResource)
        serve("/*").with(GuiceContainer)
    }
}
