package kuvaldis.makeup.rest.app

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Key
import groovy.util.logging.Slf4j
import kuvaldis.makeup.lib.module.LibModule
import kuvaldis.makeup.rest.annotation.HttpServer
import kuvaldis.makeup.rest.module.AppModule
import kuvaldis.makeup.rest.server.Server
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
        Injector injector = Guice.createInjector(appModule, new LibModule())
        injector.getInstance(Key.get(Server, HttpServer)).start()
        log.info("Application started in ${System.currentTimeMillis() - startTimeApp} ms")
        println "Press any key to stop the server..."
        System.in.read()
    }
}
