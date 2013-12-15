package kuvaldis.makeup.lib.service

import com.google.inject.Inject
import kuvaldis.makeup.lib.sql.SqlHolder

/**
 * @author Kuvaldis
 * Create date: 15.12.13 2:02
 */
abstract class AbstractService {

    @Inject
    SqlHolder sqlHolder

    protected sql() {
        sqlHolder.sql
    }
}
