package kuvaldis.makeup.lib.job

import com.google.common.base.CaseFormat
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
            def jobName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, it.class.simpleName).replace('_', ' ')
            log.info("Start $jobName")
            def start = System.currentTimeMillis()
            it.runJob()
            log.info("Done $jobName in ${System.currentTimeMillis() - start}")
        }
    }
}
