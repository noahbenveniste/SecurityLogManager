package edu.ncsu.csc316.security_log.util;

import java.util.Comparator;

import edu.ncsu.csc316.security_log.data.LogCounter;
import edu.ncsu.csc316.security_log.data.LogEntry;

/**
 * 
 * @author Noah Benveniste
 */
public class UserComparator<T> implements Comparator<T> {

    /**
     * Used to compare two log entries first by user name, then time stamp,
     * then action, then resource.
     * 
     * @param l1 first log entry to compare
     * @param l2 second log entry to compare
     * 
     * @return negative integer if l1 lexicographically precedes l2, positive
     *         if l2 lexicographically precedes l1, 0 if l1 and l2 are equal.
     */
    @Override
    public int compare(T o1, T o2) {
        LogEntry l1 = (LogEntry) o1;
        LogEntry l2 = (LogEntry) o2;
        if (!l1.getUser().equals(l2.getUser())) {
            return l1.getUser().compareTo(l2.getUser());
        } else if (l1.getTimeStamp().getVal() != l2.getTimeStamp().getVal()) {
            return l1.getTimeStamp().compareTo(l2.getTimeStamp());
        } else if (!l1.getAction().equals(l2.getAction())) {
            return l1.getAction().compareTo(l2.getAction());
        } else if (!l1.getResource().equals(l2.getResource())) {
            return l1.getResource().compareTo(l2.getResource());
        } else {
            return 0;
        }
    }

}
