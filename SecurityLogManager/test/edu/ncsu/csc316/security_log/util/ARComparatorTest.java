package edu.ncsu.csc316.security_log.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.security_log.data.LogEntry;

/**
 * 
 * @author Noah Benveniste
 */
public class ARComparatorTest {

    /**
     * 
     */
    @Test
    public void testCompare() {
        LogEntry l1 = new LogEntry("nnbenven", "01/01/2000 12:00:00AM", "action", "resource");
        LogEntry l2 = new LogEntry("abcd", "01/01/2000 12:00:00AM", "action", "resource");
        LogEntry l3 = new LogEntry("nnbenven", "12/01/2000 12:00:00AM", "action", "resource");
        LogEntry l4 = new LogEntry("nnbenven", "01/01/2000 12:00:00AM", "differentaction", "resource");
        LogEntry l5 = new LogEntry("nnbenven", "01/01/2000 12:00:00AM", "action", "differentresource");
        LogEntry l6 = new LogEntry("nnbenven", "01/01/2000 12:00:00AM", "action", "resource");
        ARComparator<LogEntry> c = new ARComparator<LogEntry>();
        assertTrue(c.compare(l1,l2) > 0);
        assertTrue(c.compare(l1,l3) < 0);
        assertTrue(c.compare(l1,l4) < 0);
        assertTrue(c.compare(l1,l5) > 0);
        assertTrue(c.compare(l1,l6) == 0);
    }

}
