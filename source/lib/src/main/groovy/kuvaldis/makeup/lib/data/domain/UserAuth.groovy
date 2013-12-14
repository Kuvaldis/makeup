package kuvaldis.makeup.lib.data.domain

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import kuvaldis.makeup.lib.annotation.Id

/**
 * @author Kuvaldis
 * Create date: 15.12.13 1:05
 */
@ToString
@EqualsAndHashCode
class UserAuth {
    @Id
    Integer id
    Integer userId
    String login
    String password
    AuthWay.AuthWayKind authWay
}
