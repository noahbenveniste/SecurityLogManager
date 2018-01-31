package edu.ncsu.csc316.security_log.data;

/**
 * @author Noah Benveniste
 *
 */
public class LogCounter implements Comparable<LogCounter> {

    @Override
    public int compareTo(LogCounter o) {
        // Sorts first by frequency (high to low), then action, then resource
        return 0;
    }

}
