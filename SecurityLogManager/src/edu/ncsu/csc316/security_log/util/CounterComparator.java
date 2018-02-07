package edu.ncsu.csc316.security_log.util;

import java.util.Comparator;

import edu.ncsu.csc316.security_log.data.LogCounter;

/**
 * 
 * @author Noah Benveniste
 */
public class CounterComparator<T> implements Comparator<T> {

    /**
     * 
     */
    @Override
    public int compare(T o1, T o2) {
        LogCounter l1 = (LogCounter) o1;
        LogCounter l2 = (LogCounter) o2;
        // Sorts first by frequency (high to low), then action, then resource
        if (l1.getFrequency() != l2.getFrequency()) {
            return l2.getFrequency() - l1.getFrequency();
        } else if (!l1.getAction().equals(l2.getAction())) {
            return l1.getAction().compareTo(l2.getAction());
        } else if (!l1.getResource().equals(l2.getResource())) {
            return l1.getResource().compareTo(l2.getResource());
        } else {
            return 0;
        }
    }

}
