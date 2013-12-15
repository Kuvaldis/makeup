package kuvaldis.makeup.lib.data.domain

import kuvaldis.makeup.lib.annotation.Id

/**
 * @author Kuvaldis
 * Create date: 15.12.13 2:11
 */
class Profile {
    @Id
    Integer id
    Integer userId
    String username
}
