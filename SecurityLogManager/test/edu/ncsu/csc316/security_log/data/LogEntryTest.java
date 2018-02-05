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

}
