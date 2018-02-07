package edu.ncsu.csc316.security_log.data;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.security_log.util.LogCounterList;

/**
 * 
 * @author Noah Benveniste
 */
public class LogCounterTest {

    /**
     * 
     */
    @Test
    public void testEquals() {
        String action = "sort";
        String resource = "ICD-9 Code 196";
        LogCounter c1 = new LogCounter(action, resource);
        LogCounter c2 = new LogCounter(action, resource);
        assertTrue(c1.equals(c2));
        LogCounterList counters = new LogCounterList();
        //assertEquals(-1, counters.containsCounter(action, resource));
        counters.addCounter(action, resource);
        assertTrue(c1.equals(counters.getCounter(0)));
        assertEquals(c1, counters.getCounter(0));
        //counters.containsCounter(action, resource);
    }
    
    /**
     * 
     */
    @Test
    public void testCompareTo() {
        String action = "sort";
        String resource = "ICD-9 Code 196";
        LogCounter c1 = new LogCounter(action, resource);
        LogCounter c2 = new LogCounter(action, resource);
        assertEquals(0, c1.compareTo(c2));
    }

}
