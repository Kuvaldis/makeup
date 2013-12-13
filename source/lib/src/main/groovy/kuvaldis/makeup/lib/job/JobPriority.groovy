package kuvaldis.makeup.lib.job

/**
 * User: NFadin
 * Date: 13.12.13
 * Time: 17:12
 */
public enum JobPriority {

    SMALL(9),
    MEDIUM(4),
    DEFAULT(4),
    BIG(0)

    def final Integer priority

    JobPriority(Integer priority) {
        this.priority = priority
    }
}