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

    Set<Job> jobs = []

    @Inject
    JobExecutor(Set<Job> jobs) {
        jobs.sort {
            it.priority.priority
        }
        this.jobs = jobs
    }

    void runJobs() {
        log.info("Execute lib jobs")
        jobs.each {
            it.runJob()
        }
    }
}
