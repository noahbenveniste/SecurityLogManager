package edu.ncsu.csc316.security_log.util;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.data.TimeStamp;
import edu.ncsu.csc316.security_log.io.SecurityLogIO;
import edu.ncsu.csc316.security_log.util.LinkedListRecursive;

/**
 * Unit tests for LinkedListRecursive
 * @author Brian Wu, Noah Benveniste, Ben Gale
 */
public class LinkedListRecursiveTest {
    
    /** A LinkedListRecursive object reference to be used throughout testing */
    private LinkedListRecursive<String> list;

    /**
     * Initializes objects to be used throughout testing
     */
    @Before
    public void setUp() {
        list = new LinkedListRecursive<String>();
    }

    /**
     * Test for constructor
     */
    @Test
    public void testLinkedList() {
        assertEquals(0, list.size());
    }


    /**
     * Test for add(int, E)
     */
    @Test
    public void testAddIntE() {
        //Add some elements to the list
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        String s6 = "f";
        String s7 = "h";
        String s8 = "i";
        
        assertTrue(list.isEmpty());
        list.add(0, s1);
        assertEquals(1, list.size());
        list.add(1, s2);
        assertEquals(2, list.size());
        list.add(2, s3);
        assertEquals(3, list.size());
        list.add(3, s4);
        assertEquals(4, list.size());
        list.add(4, s5);
        assertEquals(5, list.size());
        
        //Try adding outside the size of the list
        try {
            list.add(-1, "f");
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range", e.getMessage());
            assertEquals(5, list.size());
        }
        
        try {
            list.add(6, "f");
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range", e.getMessage());
            assertEquals(5, list.size());
        }
        
        //Try adding a null element
        try {
            list.add(4, null);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Cannot add null elements", e.getMessage());
            assertEquals(5, list.size());
        }
        
        //Try adding a duplicate element
        try {
            list.add(5, s3);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot add duplicate elements", e.getMessage());
            assertEquals(5, list.size());
        }
        
        //Add to the front of the list
        list.add(0, s6);
        assertEquals(6, list.size());
        assertEquals(s6, list.get(0));
        assertEquals(s1, list.get(1));
        assertEquals(s2, list.get(2));
        assertEquals(s3, list.get(3));
        assertEquals(s4, list.get(4));
        assertEquals(s5, list.get(5));
        
        //Add to the middle of the list
        list.add(3, s7);
        assertEquals(7, list.size());
        assertEquals(s6, list.get(0));
        assertEquals(s1, list.get(1));
        assertEquals(s2, list.get(2));
        assertEquals(s7, list.get(3));
        assertEquals(s3, list.get(4));
        assertEquals(s4, list.get(5));
        assertEquals(s5, list.get(6));
        
        
        //Add to the back of the list
        list.add(7, s8);
        assertEquals(8, list.size());
        assertEquals(s6, list.get(0));
        assertEquals(s1, list.get(1));
        assertEquals(s2, list.get(2));
        assertEquals(s7, list.get(3));
        assertEquals(s3, list.get(4));
        assertEquals(s4, list.get(5));
        assertEquals(s5, list.get(6));
        assertEquals(s8, list.get(7));
    }

    /**
     * Test for remove(int) [calls the method from AbstractSequentialList, used to test inner classes]
     */
    @Test
    public void testRemoveInt() {
        //Add some elements to the list
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        
        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);
        list.add(3, s4);
        list.add(4, s5);
        
        //Try removing outside the size of the list
        try {
            list.remove(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range", e.getMessage());
            assertEquals(5, list.size());
        }
        
        try {
            list.remove(6);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range", e.getMessage());
            assertEquals(5, list.size());
        }
        
        assertEquals(s1, list.get(0));
        assertEquals(s2, list.get(1));
        assertEquals(s3, list.get(2));
        assertEquals(s4, list.get(3));
        assertEquals(s5, list.get(4));
        
        //Remove from the middle
        list.remove(2);
        assertEquals(4, list.size());
        assertEquals(s1, list.get(0));
        assertEquals(s2, list.get(1));
        assertEquals(s4, list.get(2));
        assertEquals(s5, list.get(3));
        
        //Remove from the front
        list.remove(0);
        assertEquals(3, list.size());
        assertEquals(s2, list.get(0));
        assertEquals(s4, list.get(1));
        assertEquals(s5, list.get(2));
        
        //Remove from the back
        list.remove(2);
        assertEquals(2, list.size());
        assertEquals(s2, list.get(0));
        assertEquals(s4, list.get(1));
        
        list.remove(0);
        list.remove(0);
        assertEquals(0, list.size());
        
        //Try removing from an empty list
        try {
            list.remove(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(0, list.size());
        }
    }

    /**
     * Test for set(int, E)
     */
    @Test
    public void testSetIntE() {
        //Add some elements to the list
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        
        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);
        list.add(3, s4);
        list.add(4, s5);
        
        //Try setting outside the size of the list
        try {
            list.set(-1, "f");
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range", e.getMessage());
            assertEquals(5, list.size());
        }
        
        try {
            list.set(6, "f");
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range", e.getMessage());
            assertEquals(5, list.size());
        }
        
        //Try setting a null element
        try {
            list.set(4, null);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Cannot set null elements", e.getMessage());
            assertEquals(5, list.size());
        }
        
        //Try setting a duplicate element
        try {
            list.set(4, s3);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot set duplicate elements", e.getMessage());
            assertEquals(5, list.size());
        }
        
        //Set at the front of the list
        list.set(0, "x");
        assertEquals("x", list.get(0));
        assertEquals(s2, list.get(1));
        assertEquals(s3, list.get(2));
        assertEquals(s4, list.get(3));
        assertEquals(s5, list.get(4));
        assertEquals(5, list.size());
        
        //Set at the middle of the list
        list.set(2, "y");
        assertEquals("x", list.get(0));
        assertEquals(s2, list.get(1));
        assertEquals("y", list.get(2));
        assertEquals(s4, list.get(3));
        assertEquals(s5, list.get(4));
        assertEquals(5, list.size());
        
        //Set at the end of the list
        list.set(4, "z");
        assertEquals("x", list.get(0));
        assertEquals(s2, list.get(1));
        assertEquals("y", list.get(2));
        assertEquals(s4, list.get(3));
        assertEquals("z", list.get(4));
        assertEquals(5, list.size());
    }
    
    /**
     * Test method for add(E)
     */
    @Test
    public void testAddE() {
        //Add some elements to the list
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        
        assertEquals(0, list.size());
        list.add(s1);
        assertEquals(1, list.size());
        assertEquals(s1, list.get(0));
        list.add(s2);
        assertEquals(2, list.size());
        assertEquals(s1, list.get(0));
        assertEquals(s2, list.get(1));
        list.add(s3);
        assertEquals(3, list.size());
        assertEquals(s1, list.get(0));
        assertEquals(s2, list.get(1));
        assertEquals(s3, list.get(2));
        
        //Try adding a null element
        try {
            list.add(null);
        } catch (NullPointerException e) {
            assertEquals("Cannot add null elements", e.getMessage());
            assertEquals(3, list.size());
            assertEquals(s1, list.get(0));
            assertEquals(s2, list.get(1));
            assertEquals(s3, list.get(2));
        }
        
        //Try adding a duplicate element
        try {
            list.add(s1);
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot add duplicate elements", e.getMessage());
            assertEquals(3, list.size());
            assertEquals(s1, list.get(0));
            assertEquals(s2, list.get(1));
            assertEquals(s3, list.get(2));
        }
    }
    
    /**
     * Test method for remove(E)
     */
    @Test
    public void testRemoveE() {
        //Try removing from an empty list
        assertEquals(0, list.size());
        assertFalse(list.remove("a"));
        
        //Add some elements to the list
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        
        list.add(0, s1);
        
//        //Try removing null
//        try {
//            list.remove(null);
//            fail();
//        } catch (NullPointerException e) {
//            assertEquals("Cannot remove null", e.getMessage());
//            assertEquals(1, list.size());
//        }
        
        //Try removing an element not in the list
        assertFalse(list.remove("x"));
        
        //Remove from a list that contains only one element
        assertTrue(list.remove(s1));
        assertEquals(0, list.size());
        
        //Add some more elements
        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);
        list.add(3, s4);
        list.add(4, s5);
        
        //Remove the element from the middle of the list
        list.remove(s3);
        assertEquals(4, list.size());
        assertEquals(s1, list.get(0));
        assertEquals(s2, list.get(1));
        assertEquals(s4, list.get(2));
        assertEquals(s5, list.get(3));
        
        //Remove the element from the front of the list
        list.remove(s1);
        assertEquals(3, list.size());
        assertEquals(s2, list.get(0));
        assertEquals(s4, list.get(1));
        assertEquals(s5, list.get(2));
        
        //Remove the element from the back of the list
        list.remove(s5);
        assertEquals(2, list.size());
        assertEquals(s2, list.get(0));
        assertEquals(s4, list.get(1));
    }
    
    /**
     * Tests adding elements to the list in sorted order
     */
    @Test
    public void testAddSorted() {
        int[] arr = {5, 9, 10, 32, 1, 7, 12, 11, 100, 59, 0, -3, 1000, -100};
        LinkedListRecursive<Integer> l = new LinkedListRecursive<Integer>();
        for (int i = 0; i < arr.length; i++) {
            l.addSorted(arr[i]);
        }
        int[] expected = {-100, -3, 0, 1, 5, 7, 9, 10, 11, 12, 32, 59, 100, 1000};
        int[] actual = new int[14];
        for (int i = 0; i < l.size(); i++) {
            actual[i] = l.get(i);
            //System.out.println(l.get(i));
        }
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
    
    /**
     * Tests linkedToArray()
     */
    @Test
    public void testLinkedToArray() {
        String fileName = "input/activityLog_small.txt";
        LogEntryList logs = null;
        try {
            logs = SecurityLogIO.readLogEntriesFromFile(fileName);
        } catch (FileNotFoundException e) {
            e.getMessage();
            fail();
        }
        assertNotNull(logs);
        assertEquals(16, logs.size());
        //System.out.println(logs.getFullProfile());
        //System.out.println(logs.size());
        LogEntry frontLog = new LogEntry("fzalcala", "05/04/2015 02:09:40PM", "sort", "ICD-9 Code 196");
        LogEntry endLog = new LogEntry("fzalcala", "11/20/2017 11:38:22AM", "sort", "ICD-9 Code 196");
        LogEntry midLog = new LogEntry("quhundley", "09/21/2017 08:50:13AM", "import", "office visit OV04312");
        LogEntry invalidLog = new LogEntry("nnbenven", "02/02/2018 04:29:26PM", "import", "message M2964");
        logs.contains(frontLog);
        assertEquals(15, logs.contains(endLog));
        assertEquals(13, logs.contains(midLog));
        assertEquals(-1, logs.contains(invalidLog));
    }

}
