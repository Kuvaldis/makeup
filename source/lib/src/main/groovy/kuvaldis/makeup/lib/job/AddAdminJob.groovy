package kuvaldis.makeup.lib.job

import com.google.inject.Inject
import kuvaldis.makeup.lib.data.domain.Role
import kuvaldis.makeup.lib.service.UserService

/**
 * User: NFadin
 * Date: 13.12.13
 * Time: 17:20
 */
@com.google.inject.Singleton
class AddAdminJob extends AbstractJob {

    @Inject
    UserService userService

    @Override
    void runJob() {
        userService.checkAndCreateUser('Administrator', 'admin', 'admin', Role.ADMIN)
    }
}
