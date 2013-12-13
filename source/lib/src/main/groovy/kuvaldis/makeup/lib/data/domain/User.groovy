package kuvaldis.makeup.lib.data.domain

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import kuvaldis.makeup.lib.annotation.Id

/**
 * @author Kuvaldis
 * Create date: 14.12.13 2:07
 */
@ToString
@EqualsAndHashCode
class User {
    @Id
    Integer id
}
