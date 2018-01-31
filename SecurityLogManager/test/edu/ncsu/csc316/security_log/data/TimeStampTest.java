package edu.ncsu.csc316.security_log.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Noah Benveniste
 */
class TimeStampTest {

    private static final String t1 = "04/26/2017 12:33:15PM";
    private static final String t2 = "11/20/2017 11:38:22AM";
    private static final String t3 = "08/04/2016 06:57:34AM";
    
    /**
     * 
     */
    @Test
    void testTimeStamp() {
        TimeStamp ts1 = new TimeStamp(t1);
        assertEquals("04", ts1.getMonth());
        assertEquals("26", ts1.getDay());
        assertEquals("2017", ts1.getYear());
        assertEquals("12", ts1.getHour());
        assertEquals("33", ts1.getMinute());
        assertEquals("15", ts1.getSecond());
        assertFalse(ts1.isAM());
    }

}
