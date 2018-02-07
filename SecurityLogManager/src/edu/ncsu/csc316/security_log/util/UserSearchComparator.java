package edu.ncsu.csc316.security_log.util;

import java.util.Comparator;

import edu.ncsu.csc316.security_log.data.LogEntry;

/**
 * 
 * @author Noah Benveniste
 *
 * @param <T>
 */
public class UserSearchComparator<T> implements Comparator<T> {

    /**
     * 
     */
    @Override
    public int compare(T o1, T o2) {
        LogEntry l1 = (LogEntry) o1;
        LogEntry l2 = (LogEntry) o2;
        return l1.getUser().compareTo(l2.getUser());
    }

}
