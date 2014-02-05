package kuvaldis.makeup.app.server

import com.google.inject.Inject
import com.google.inject.name.Named
import groovy.util.logging.Slf4j

/**
 * @author Kuvaldis
 * Create date: 12.12.13 1:24
 */
@Slf4j
@com.google.inject.Singleton
class H2Server implements Server {

    def org.h2.tools.Server server

    @Inject
    H2Server(@Named('db.managementPassword') String managementPassword) {
        server = org.h2.tools.Server.createTcpServer(
                [
                        '-tcpPassword', managementPassword,
                        '-tcpAllowOthers'
                ] as String[])
    }

    @Override
    def start() {
        log.info('Start H2 server')
        def startTime = System.currentTimeMillis()
        server.start()
        log.info("DB server started in ${System.currentTimeMillis() - startTime} ms")
    }

    @Override
    def stop() {
        server.stop()
    }
}
