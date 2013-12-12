package kuvaldis.makeup.rest.app

import com.google.inject.Guice
import com.google.inject.Injector
import groovy.util.logging.Slf4j
import kuvaldis.makeup.lib.module.LibModule
import kuvaldis.makeup.rest.module.AppModule
import kuvaldis.makeup.rest.server.GrizzlyServer
import kuvaldis.makeup.rest.server.H2Server
import kuvaldis.makeup.shared.config.PropertiesBuilder
import kuvaldis.makeup.shared.module.SharedModule

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 14:07
 */
@Slf4j
class Start {
    public static void main(String[] args) {
        log.info('Start rest application')
        def startTimeApp = System.currentTimeMillis()
        Injector sharedInjector = Guice.createInjector(new SharedModule())
        def appModule = new AppModule(propertiesHolder: sharedInjector.getInstance(PropertiesBuilder).build())
        Injector injector = sharedInjector.createChildInjector(appModule, new LibModule())
        startHttpServer(injector)
        startDbServer(injector)
        log.info("Application started in ${System.currentTimeMillis() - startTimeApp} ms")
        println "Press any key to stop the server..."
        System.in.read()
    }

    static def startDbServer(Injector injector) {
        log.info('Start DB server')
        def startTime = System.currentTimeMillis()
        def server = injector.getInstance(H2Server)
        server.start()
        log.info("DB server started in ${System.currentTimeMillis() - startTime} ms")
    }

    private static void startHttpServer(Injector injector) {
        log.info('Start http server')
        def startTime = System.currentTimeMillis()
        def server = injector.getInstance(GrizzlyServer)
        server.start()
        log.info("Http server started in ${System.currentTimeMillis() - startTime} ms")
    }
}
