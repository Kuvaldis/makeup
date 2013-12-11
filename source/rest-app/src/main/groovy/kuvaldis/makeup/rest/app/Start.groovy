package kuvaldis.makeup.rest.app

import com.google.inject.Guice
import com.google.inject.Injector
import kuvaldis.makeup.rest.module.AppModule
import kuvaldis.makeup.rest.server.GrizzlyServer
import kuvaldis.makeup.shared.config.PropertiesBuilder
import kuvaldis.makeup.shared.module.SharedModule

/**
 * User: NFadin
 * Date: 10.12.13
 * Time: 14:07
 */
class Start {
    public static void main(String[] args) {
        Injector sharedInjector = Guice.createInjector(new SharedModule())
        def appModule = new AppModule(propertiesHolder: sharedInjector.getInstance(PropertiesBuilder).build())
        Injector injector = sharedInjector.createChildInjector(appModule)
        def server = injector.getInstance(GrizzlyServer)
        server.start()
        println "Press any key to stop the server..."
        System.in.read()
    }
}
