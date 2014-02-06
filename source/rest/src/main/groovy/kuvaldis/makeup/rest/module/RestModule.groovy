package kuvaldis.makeup.rest.module

import com.sun.jersey.guice.JerseyServletModule
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 14:22
 */
class RestModule extends JerseyServletModule {
    @Override
    protected void configureServlets() {
        serve("/api/*").with(GuiceContainer, ['com.sun.jersey.config.property.packages' : 'kuvaldis.makeup.rest.resource'])
    }
}
