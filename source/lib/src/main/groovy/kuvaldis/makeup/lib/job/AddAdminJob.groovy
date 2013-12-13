package kuvaldis.makeup.lib.job

import com.google.inject.Inject
import kuvaldis.makeup.lib.data.SqlHolder
import kuvaldis.makeup.lib.data.dao.UserDao
import kuvaldis.makeup.lib.data.domain.User

/**
 * User: NFadin
 * Date: 13.12.13
 * Time: 17:20
 */
@com.google.inject.Singleton
class AddAdminJob extends AbstractJob {

    @Inject
    SqlHolder sqlHolder

    @Inject
    UserDao userDao

    @Override
    void runJob() {
        sqlHolder.sql.withTransaction {
            userDao.create(new User())
        }
    }
}
