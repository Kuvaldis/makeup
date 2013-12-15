package kuvaldis.makeup.lib.service

import com.google.inject.Inject
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

    CreateUserResult createUser(String username, String login, String password, Role role) {
        def result = CreateUserResult.SUCCESS
        sql().withTransaction {
            if (userAuthDao.findByLoginAndAuthWay(login, AuthWay.AuthWayKind.MAIN)) {
                result = CreateUserResult.LOGIN_EXISTS
                return
            }
            if (profileDao.findByUsername(username)) {
                result = CreateUserResult.USERNAME_EXISTS
                return
            }
            def user = userDao.create(new User(roles: [role.name()]))
            userAuthDao.create(new UserAuth(
                    userId: user.id,
                    login: login,
                    password: encryptPassword(password),
                    authWay: AuthWay.AuthWayKind.MAIN))
            profileDao.create(new Profile(userId: user.id, username: username))
        }
        return result
    }

    String encryptPassword(String s) {
        return s
    }
}
