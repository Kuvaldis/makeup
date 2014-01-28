package kuvaldis.makeup.rest.server

import com.google.inject.Inject
import com.google.inject.Injector
import com.google.inject.name.Named
import com.google.inject.servlet.GuiceFilter
import groovy.util.logging.Slf4j
import kuvaldis.makeup.rest.server.listener.ServletContextListener
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.grizzly.servlet.WebappContext

import javax.servlet.DispatcherType

/**
 * User: NFadin
 * Date: 06.12.13
 * Time: 18:15
 */
@Slf4j
@com.google.inject.Singleton
class GrizzlyServer implements Server {

    private HttpServer server

    @Inject
    GrizzlyServer(@Named('server.port') Integer port, Injector injector) {
        server = HttpServer.createSimpleServer(".", port)
        def context = new WebappContext('Makeup rest-app context', '')
        context.addListener(ServletContextListener)
        context.setAttribute('injector', injector)
        context.addFilter('guiceFilter', GuiceFilter).with {
            addMappingForUrlPatterns(EnumSet.allOf(DispatcherType), '/*')
        }
        context.deploy(server)
    }

    @Override
    def start() {
        log.info('Start http server')
        def startTime = System.currentTimeMillis()
        server.start()
        log.info("Http server started in ${System.currentTimeMillis() - startTime} ms")
    }

    @Override
    def stop() {
        server.shutdownNow()
    }
}
