package kuvaldis.makeup.lib.service

import com.google.inject.Inject
import kuvaldis.makeup.lib.data.dao.AuthWayDao
import kuvaldis.makeup.lib.data.domain.AuthWay
import kuvaldis.makeup.lib.data.domain.AuthWay.AuthWayKind

/**
 * @author Kuvaldis
 * Create date: 15.12.13 13:52
 */
@com.google.inject.Singleton
class AuthService extends WithSqlService {

    @Inject AuthWayDao authWayDao

    def checkAndCreateAuthWay(AuthWayKind kind) {
        sql().withTransaction {
            if (!authWayDao.find(kind)) {
                authWayDao.create(new AuthWay(name: kind))
            }
        }
    }
}
