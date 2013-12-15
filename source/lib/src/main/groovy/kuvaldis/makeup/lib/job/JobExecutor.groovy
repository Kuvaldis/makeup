package kuvaldis.makeup.lib.job

import com.google.inject.Inject
import groovy.util.logging.Slf4j

/**
 * User: NFadin
 * Date: 13.12.13
 * Time: 13:28
 */
@Slf4j
class JobExecutor {

    List<Job> jobs = []

    @Inject
    JobExecutor(Set<Job> jobs) {
        this.jobs = jobs.asList().sort {
            it.priority.priority
        }
    }

    void runJobs() {
        log.info('Execute lib jobs')
        jobs.each {
            log.info("Start")
            it.runJob()
        }
    }
}
