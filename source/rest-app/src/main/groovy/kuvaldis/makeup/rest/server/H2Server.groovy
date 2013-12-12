package kuvaldis.makeup.rest.server

import com.google.inject.Inject
import com.google.inject.name.Named
import org.h2.tools.Server

/**
 * @author Kuvaldis
 * Create date: 12.12.13 1:24
 */
class H2Server {

    def Server server

    @Inject
    H2Server(@Named('db.managementPassword') String managementPassword) {
        server = Server.createTcpServer(
                [
                        '-tcpPassword', managementPassword,
                        '-tcpAllowOthers'
                ] as String[])
    }

    def start() {
        server.start()
    }

    def stop() {
        server.stop()
    }
}
