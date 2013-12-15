package kuvaldis.makeup.lib.data.dao

import kuvaldis.makeup.lib.data.domain.AuthWay
import kuvaldis.makeup.lib.data.domain.UserAuth

/**
 * @author Kuvaldis
 * Create date: 15.12.13 1:46
 */
@com.google.inject.Singleton
class UserAuthDao extends AbstractDao<UserAuth> {

    UserAuth findByLoginAndAuthWay(String login, AuthWay.AuthWayKind authWayKind) {
        executeSelect([login: login, authWay: authWayKind])
    }
}
