package kuvaldis.makeup.app.server

import com.google.inject.Inject
import com.google.inject.name.Named
import com.google.inject.servlet.GuiceFilter
import groovy.util.logging.Slf4j
import kuvaldis.makeup.app.server.listener.ServletContextListener
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
class GrizzlyApiServer implements Server {

    private HttpServer server

    @Inject
    GrizzlyApiServer(@Named('server.api.port') Integer port) {
        server = HttpServer.createSimpleServer(".", port)
        def context = new WebappContext('Makeup rest-app context', '')
        context.addListener(ServletContextListener)
        context.addFilter('guiceFilter', GuiceFilter).with {
            addMappingForUrlPatterns(EnumSet.allOf(DispatcherType), '/*')
        }
        context.deploy(server)
    }

    @Override
    def start() {
        log.info('Start api server')
        def startTime = System.currentTimeMillis()
        server.start()
        log.info("Api server started in ${System.currentTimeMillis() - startTime} ms")
    }

    @Override
    def stop() {
        server.shutdownNow()
    }
}
