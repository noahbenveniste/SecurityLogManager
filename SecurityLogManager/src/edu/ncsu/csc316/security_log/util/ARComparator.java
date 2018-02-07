package edu.ncsu.csc316.security_log.util;

import java.util.Comparator;

import edu.ncsu.csc316.security_log.data.LogEntry;

/**
 * 
 * @author Noah Benveniste
 * @param <E>
 */
public class ARComparator<E> implements Comparator<E> {

    /**
     * Used to compare two log entries first by action, then resource,
     * then time stamp, then user name.
     * 
     * @param l1 first log entry to compare
     * @param l2 second log entry to compare
     * 
     * @return negative integer if l1 lexicographically precedes l2, positive
     *         if l2 lexicographically precedes l1, 0 if l1 and l2 are equal.
     */
    @Override
    public int compare(E o1, E o2) {
        LogEntry l1 = (LogEntry) o1;
        LogEntry l2 = (LogEntry) o2;
        if (!l1.getAction().equals(l2.getAction())) {
            return l1.getAction().compareTo(l2.getAction());
        } else if (!l1.getResource().equals(l2.getResource())) {
            return l1.getResource().compareTo(l2.getResource());
        } else if (l1.getTimeStamp().getVal() != l2.getTimeStamp().getVal()) {
            return l1.getTimeStamp().compareTo(l2.getTimeStamp());
        } else if (!l1.getUser().equals(l2.getUser())) {
            return l1.compareTo(l2);
        } else {
            return 0;
        }
    }

}
