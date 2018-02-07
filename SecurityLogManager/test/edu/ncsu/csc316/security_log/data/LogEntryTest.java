package edu.ncsu.csc316.security_log.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class LogEntryTest {

    @Test
    public void testCompareTo() {
        LogEntry l1 = new LogEntry("quhundley", "07/18/2015 07:57:42PM", "sort", "ICD-9 Code 196");
        LogEntry l2 = new LogEntry("fzalcala", "10/04/2015 12:17:49PM", "resolve", "message M2964");
        LogEntry l3 = new LogEntry("fzalcala", "07/18/2015 07:57:42PM", "merge", "ICD-9 Code 196");
        LogEntry l4 = new LogEntry("quhundley", "07/18/2015 07:57:42PM", "merge", "ICD-9 Code 196");
        assertTrue(l1.compareTo(l3) > 0);
        assertTrue(l1.compareTo(l2) < 0);
        assertTrue(l3.compareTo(l4) < 0);
    }
    
    @Test
    public void testEquals() {
        LogEntry l1 = new LogEntry("nnbenven", "01/01/2000 12:00:00AM", "action", "resource");
        LogEntry l2 = new LogEntry("abcd", "01/01/2000 12:00:00AM", "action", "resource");
        LogEntry l3 = new LogEntry("nnbenven", "12/01/2000 12:00:00AM", "action", "resource");
        LogEntry l4 = new LogEntry("nnbenven", "01/01/2000 12:00:00AM", "differentaction", "resource");
        LogEntry l5 = new LogEntry("nnbenven", "01/01/2000 12:00:00AM", "action", "differentresource");
        LogEntry l6 = new LogEntry("nnbenven", "01/01/2000 12:00:00AM", "action", "resource");
        assertFalse(l1.equals(l2));
        assertFalse(l1.equals(l3));
        assertFalse(l1.equals(l4));
        assertFalse(l1.equals(l5));
        assertTrue(l1.equals(l6));
    }

}
