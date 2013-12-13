package kuvaldis.makeup.lib.job

/**
 * User: NFadin
 * Date: 13.12.13
 * Time: 17:15
 */
abstract class AbstractJob implements Job {
    @Override
    JobPriority getPriority() {
        return JobPriority.DEFAULT
    }
}
