package kuvaldis.makeup.lib.data.dao

import kuvaldis.makeup.lib.data.domain.Profile

/**
 * @author Kuvaldis
 * Create date: 15.12.13 2:12
 */
@com.google.inject.Singleton
class ProfileDao extends AbstractDao<Profile>{
    Profile findByUsername(String username) {
        executeSelect(username: username)
    }
}
