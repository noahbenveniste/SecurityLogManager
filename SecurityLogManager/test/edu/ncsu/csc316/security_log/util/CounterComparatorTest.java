package edu.ncsu.csc316.security_log.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.security_log.data.LogCounter;

/**
 * 
 * @author Noah Benveniste
 */
public class CounterComparatorTest {

    /**
     * 
     */
    @Test
    public void testCompare() {
        LogCounter c1 = new LogCounter("action", "resource");
        LogCounter c2 = new LogCounter("differentaction", "resource");
        LogCounter c3 = new LogCounter("action", "differentresource");
        LogCounter c4 = new LogCounter("different", "different");
        LogCounter c5 = new LogCounter("action", "resource");
        c5.incrementFrequency();
        LogCounter c6 = new LogCounter("action", "resource");
        
        CounterComparator<LogCounter> c = new CounterComparator<LogCounter>();
        
        assertTrue(c.compare(c1, c2) < 0);
        assertTrue(c.compare(c1, c3) > 0);
        assertTrue(c.compare(c1, c4) < 0);
        assertTrue(c.compare(c1, c5) > 0);
        assertTrue(c.compare(c1, c6) == 0);
    }

}
