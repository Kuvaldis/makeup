package kuvaldis.makeup.app

import com.google.inject.Injector
import com.google.inject.Key
import groovy.util.logging.Slf4j
import kuvaldis.makeup.app.annotation.ApiServer
import kuvaldis.makeup.app.annotation.StaticContentServer
import kuvaldis.makeup.app.module.AppModule
import kuvaldis.makeup.app.server.Server
import kuvaldis.makeup.shared.module.SharedGuiceHolder

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
        Injector sharedInjector = SharedGuiceHolder.injector
        Injector injector = sharedInjector.createChildInjector(new AppModule())
        injector.getInstance(Key.get(Server, ApiServer)).start()
        injector.getInstance(Key.get(Server, StaticContentServer)).start()
        log.info("Application started in ${System.currentTimeMillis() - startTimeApp} ms")
        println "Press any key to stop the server..."
        System.in.read()
    }
}
