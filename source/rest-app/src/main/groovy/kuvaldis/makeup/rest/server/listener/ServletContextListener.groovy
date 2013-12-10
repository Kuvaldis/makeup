package kuvaldis.makeup.rest.server.listener

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.servlet.GuiceServletContextListener
import kuvaldis.makeup.rest.module.RestModule

/**
 * User: NFadin
 * Date: 09.12.13
 * Time: 17:49
 */
class ServletContextListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        Guice.createInjector(new RestModule())
    }
}
