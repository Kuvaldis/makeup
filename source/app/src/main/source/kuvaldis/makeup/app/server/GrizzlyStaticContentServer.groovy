package kuvaldis.makeup.app.server

import com.google.inject.Inject
import com.google.inject.name.Named
import groovy.util.logging.Slf4j
import org.glassfish.grizzly.http.server.HttpServer

/**
 * User: NFadin
 * Date: 06.02.14
 * Time: 13:20
 */
@Slf4j
@com.google.inject.Singleton
class GrizzlyStaticContentServer implements Server {

    private HttpServer server

    @Inject
    GrizzlyStaticContentServer(@Named('server.static_content.port') Integer port, @Named('server.static_content.path') String pathToStaticContent) {
        server = HttpServer.createSimpleServer(pathToStaticContent, port)
    }

    @Override
    def start() {
        log.info('Start static content server')
        def startTime = System.currentTimeMillis()
        server.start()
        log.info("Static content server started in ${System.currentTimeMillis() - startTime} ms")
    }

    @Override
    def stop() {
        server.shutdownNow()
    }
}
