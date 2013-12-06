package kuvaldis.makeup.server

import org.glassfish.grizzly.http.server.HttpServer

/**
 * User: NFadin
 * Date: 06.12.13
 * Time: 18:15
 */
class Server {

    public static void main(String[] args) {
        def server = HttpServer.createSimpleServer()
        server.start();
        println 'Press any key to stop the server...'
        System.in.read()
    }
}
