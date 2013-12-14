package kuvaldis.makeup.lib.data.domain

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import kuvaldis.makeup.lib.annotation.Id

/**
 * @author Kuvaldis
 * Create date: 15.12.13 1:00
 */
@ToString
@EqualsAndHashCode
class AuthWay {
    @Id
    AuthWayKind name

    enum AuthWayKind {
        MAIN
    }
}
