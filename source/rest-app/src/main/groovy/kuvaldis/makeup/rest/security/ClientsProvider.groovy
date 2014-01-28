package kuvaldis.makeup.rest.security

import org.pac4j.core.client.BaseClient

/**
 * @author Kuvaldis
 * Create date: 26.01.14 20:38
 */
class ClientsProvider {

    Map<String, BaseClient> clientsMap

    BaseClient getClient(String simpleName) {
        clientsMap.get(simpleName)
    }

    def setClients(List<BaseClient> clients) {
        clientsMap = [:]
        clients.each {
            clientsMap << [(it.class.simpleName) : it]
        }
    }

    List<BaseClient> getClients() {
        clientsMap.values() as List
    }
}
