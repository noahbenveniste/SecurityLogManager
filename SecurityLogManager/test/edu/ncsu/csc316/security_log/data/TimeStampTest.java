package edu.ncsu.csc316.security_log.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Noah Benveniste
 */
public class TimeStampTest {

    private static final String t1 = "04/26/2017 12:33:15PM";
    private static final String t2 = "11/20/2017 11:38:22AM";
    private static final String t3 = "08/04/2016 06:57:34AM";
    private static final String t4 = "04/26/2017 12:33:15PM";
    private static final String t5 = "03/30/2015 12:00:00AM";
    private static final String t6 = "03/30/2015 12:10:01AM";
    
    /**
     * 
     */
    @Test
    public void testTimeStamp() {
        TimeStamp ts1 = new TimeStamp(t1);
        assertEquals(20170426123315L, ts1.getVal());
        TimeStamp t = new TimeStamp("12/13/2016 09:39:33PM");
        //TimeStamp ts5 = new TimeStamp(t5);
//        assertEquals("04", ts1.getMonth());
//        assertEquals("26", ts1.getDay());
//        assertEquals("2017", ts1.getYear());
//        assertEquals("12", ts1.getHour());
//        assertEquals("33", ts1.getMinute());
//        assertEquals("15", ts1.getSecond());
//        assertFalse(ts1.isAM());
    }
    
    /**
     * 
     */
    @Test
    public void testEquals() {
        TimeStamp ts1 = new TimeStamp(t1);
        TimeStamp ts2 = new TimeStamp(t2);
        TimeStamp ts3 = new TimeStamp(t3);
        TimeStamp ts4 = new TimeStamp(t4);
        assertFalse(ts1.equals(ts2));
        assertFalse(ts2.equals(ts3));
        assertFalse(ts3.equals(ts4));
        assertTrue(ts1.equals(ts4));
    }
    
//    /**
//     * 
//     */
//    @Test
//    public void testCompareTo() {
//        
//    }

}
