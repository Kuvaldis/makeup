package kuvaldis.makeup.lib.job

import com.google.inject.Inject
import kuvaldis.makeup.lib.data.domain.AuthWay
import kuvaldis.makeup.lib.service.AuthService

/**
 * @author Kuvaldis
 * Create date: 15.12.13 13:46
 */
@com.google.inject.Singleton
class AddDataJob extends AbstractJob {

    @Inject AuthService authService

    @Override
    JobPriority getPriority() {
        return JobPriority.BIG
    }

    @Override
    void runJob() {
        AuthWay.AuthWayKind.values().each {
            authService.checkAndCreateAuthWay(it)
        }
    }
}
