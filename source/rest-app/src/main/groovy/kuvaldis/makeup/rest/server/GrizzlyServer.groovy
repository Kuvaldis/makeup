package kuvaldis.makeup.rest.server

import com.google.inject.Inject
import com.google.inject.name.Named
import com.google.inject.servlet.GuiceFilter
import com.sun.jersey.api.core.PackagesResourceConfig
import com.sun.jersey.spi.container.servlet.ServletContainer
import kuvaldis.makeup.rest.server.listener.ServletContextListener
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.grizzly.servlet.WebappContext

import javax.servlet.DispatcherType

/**
 * User: NFadin
 * Date: 06.12.13
 * Time: 18:15
 */
class GrizzlyServer {

    private HttpServer server

    @Inject
    GrizzlyServer(@Named('web.server.port') Integer port, @Named('web.resource.package') String resourcePackage) {
        server = HttpServer.createSimpleServer(".", port)
        def context = new WebappContext('Makeup rest-app context', '')
        context.addListener(ServletContextListener)
        context.addFilter('guiceFilter', GuiceFilter).with {
            addMappingForUrlPatterns(EnumSet.allOf(DispatcherType), '/*')
        }
        context.addServlet('servletContainer', new ServletContainer(new PackagesResourceConfig(resourcePackage))).with {
            addMapping('/*')
        }
        context.deploy(server)
    }

    def start() {
        server.start()
    }
}
