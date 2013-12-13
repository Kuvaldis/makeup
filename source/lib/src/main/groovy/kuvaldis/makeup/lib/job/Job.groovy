package kuvaldis.makeup.lib.job

/**
 * User: NFadin
 * Date: 13.12.13
 * Time: 13:26
 */
public interface Job {

    JobPriority getPriority()
    void runJob()
}