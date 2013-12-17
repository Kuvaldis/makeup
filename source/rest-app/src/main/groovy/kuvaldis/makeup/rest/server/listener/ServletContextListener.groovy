package kuvaldis.makeup.rest.server.listener

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.servlet.GuiceServletContextListener
import kuvaldis.makeup.rest.module.RestModule
import kuvaldis.makeup.rest.module.SecurityModule

import javax.servlet.ServletContext
import javax.servlet.ServletContextEvent

/**
 * User: NFadin
 * Date: 09.12.13
 * Time: 17:49
 */
class ServletContextListener extends GuiceServletContextListener {

    private ServletContext servletContext

    @Override
    void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent.servletContext
        super.contextInitialized(servletContextEvent)
    }

    @Override
    protected Injector getInjector() {
        Guice.createInjector(new RestModule(), new SecurityModule(servletContext))
    }
}
