package kuvaldis.makeup.lib.service

import com.google.inject.Inject
import kuvaldis.makeup.lib.data.dao.AuthWayDao
import kuvaldis.makeup.lib.data.dao.ProfileDao
import kuvaldis.makeup.lib.data.dao.UserAuthDao
import kuvaldis.makeup.lib.data.dao.UserDao
import kuvaldis.makeup.lib.data.domain.*

/**
 * @author Kuvaldis
 * Create date: 15.12.13 2:02
 */
@com.google.inject.Singleton
class UserService extends AbstractService {

    @Inject UserDao userDao
    @Inject UserAuthDao userAuthDao
    @Inject ProfileDao profileDao
    @Inject AuthWayDao authWayDao

    def checkAndCreateUser(String username, String login, String password, Role role) {
        sql().withTransaction {
            def user = userDao.create(new User(roles: [role.name()]))
            userAuthDao.create(new UserAuth(
                    userId: user.id,
                    login: login,
                    password: encryptPassword(password),
                    authWay: AuthWay.AuthWayKind.MAIN))
            profileDao.create(new Profile(userId: user.id, username: username))
        }
    }

    String encryptPassword(String s) {
        return s
    }
}
