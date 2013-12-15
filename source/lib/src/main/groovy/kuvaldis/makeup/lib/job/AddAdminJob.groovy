package kuvaldis.makeup.lib.job

import com.google.inject.Inject
import groovy.util.logging.Slf4j
import kuvaldis.makeup.lib.data.domain.CreateUserResult
import kuvaldis.makeup.lib.data.domain.Role
import kuvaldis.makeup.lib.service.UserService

/**
 * User: NFadin
 * Date: 13.12.13
 * Time: 17:20
 */
@Slf4j
@com.google.inject.Singleton
class AddAdminJob extends AbstractJob {

    @Inject
    UserService userService

    @Override
    void runJob() {
        switch (userService.createUser('Administrator', 'admin', 'admin', Role.ADMIN)) {
            case CreateUserResult.SUCCESS:
                log.info('Admin has been created successfully')
                break
            case CreateUserResult.LOGIN_EXISTS:
            case CreateUserResult.USERNAME_EXISTS:
                log.info('Admin already exists')
                break
            default:
                throw new IllegalStateException('Error during creation admin')
        }
    }
}
