package edu.ncsu.csc316.security_log.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A modified version of a test file for LinkedAbstractList from CSC216
 * to test LinkedList for SecurityLogManager, which itself uses the 
 * LinkedAbstractList implementation.
 * 
 * @author Bwu98, Dustin Hollar, Hubert Ngo, Noah Benveniste
 */
public class LinkedListTest {

    /**
     * Tests the add method for the linked list
     */
    @Test
    public void testAdd() {
        LinkedList<String> l = new LinkedList<String>(7);
        l.add(0, "one");
        l.add(1, "three");
        l.add(2, "four");
        assertEquals(l.size(), 3);
        assertEquals(l.get(0), "one");
        assertEquals(l.get(1), "three");
        assertEquals(l.get(2), "four");
        
        // Tests adding at beginning
        l.add(0, "zero");
        assertEquals(l.size(), 4);
        assertEquals(l.get(0), "zero");
        assertEquals(l.get(1), "one");
        assertEquals(l.get(2), "three");
        assertEquals(l.get(3), "four");
        
        // Tests adding at the end
        l.add(4, "five");
        assertEquals(l.size(), 5);
        assertEquals(l.get(0), "zero");
        assertEquals(l.get(1), "one");
        assertEquals(l.get(2), "three");
        assertEquals(l.get(3), "four");
        assertEquals(l.get(4), "five");
        
        // Tests adding at middle
        l.add(2, "two");
        assertEquals(l.size(), 6);
        assertEquals(l.get(0), "zero");
        assertEquals(l.get(1), "one");
        assertEquals(l.get(2), "two");
        assertEquals(l.get(3), "three");
        assertEquals(l.get(4), "four");
        assertEquals(l.get(5), "five");
    
        // Test for duplicates
        try {
            l.add(6, "one");
            fail("Shouldn't be able to add duplicates.");
        } catch (IllegalArgumentException e) {
            assertEquals(l.size(), 6);
            assertEquals(l.get(0), "zero");
            assertEquals(l.get(1), "one");
            assertEquals(l.get(2), "two");
            assertEquals(l.get(3), "three");
            assertEquals(l.get(4), "four");
            assertEquals(l.get(5), "five");
        }
        
        try {
            l.add(0, "one");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(6, l.size());
        }
        
        try {
            l.add(1, "one");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(6, l.size());
        }
        
        try {
            l.add(2, "one");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(6, l.size());
        }
        
        try {
            l.add(3, "one");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(6, l.size());
        }
        
        try {
            l.add(4, "one");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(6, l.size());
        }
        
        try {
            l.add(5, "one");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(6, l.size());
        }
        
        // Test for illegal index
        try {
            l.add(7, "asdfasd");
            fail("Shouldn't be able to add outside bounds.");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(l.size(), 6);
            assertEquals(l.get(0), "zero");
            assertEquals(l.get(1), "one");
            assertEquals(l.get(2), "two");
            assertEquals(l.get(3), "three");
            assertEquals(l.get(4), "four");
            assertEquals(l.get(5), "five");
        }
        
        // Test for illegal index
        try {
            l.add(-1, "asdfasd");
            fail("Shouldn't be able to add outside bounds.");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(l.size(), 6);
            assertEquals(l.get(0), "zero");
            assertEquals(l.get(1), "one");
            assertEquals(l.get(2), "two");
            assertEquals(l.get(3), "three");
            assertEquals(l.get(4), "four");
            assertEquals(l.get(5), "five");
        }
        
        // Test for illegal element
        try {
            l.add(1, null);
            fail("Shouldn't be able to add null.");
        } catch (NullPointerException e) {
            assertEquals(l.size(), 6);
            assertEquals(l.get(0), "zero");
            assertEquals(l.get(1), "one");
            assertEquals(l.get(2), "two");
            assertEquals(l.get(3), "three");
            assertEquals(l.get(4), "four");
            assertEquals(l.get(5), "five");
        }
    }
    
    /**
     * Tests the remove method for the linked list
     */
    @Test
    public void testRemove() {
        LinkedList<String> l = new LinkedList<String>(4);
        l.add(0, "zero");
        l.add(1, "one");
        l.add(2, "two");
        l.add(3, "three");
        
        // Test remove at beginning
        l.remove(0);
        assertEquals(l.size(), 3);
        assertEquals(l.get(0), "one");
        assertEquals(l.get(1), "two");
        assertEquals(l.get(2), "three");
        
        // Test remove at the middle
        l.remove(1);
        assertEquals(l.size(), 2);
        assertEquals(l.get(0), "one");
        assertEquals(l.get(1), "three");
        
        // Test remove at the end
        l.remove(1);
        assertEquals(l.size(), 1);
        assertEquals(l.get(0), "one");
        
        
        // Test illegal index
        try {
            l.remove(12);
            fail("Shouldn't be able to remove with illegal index.");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(l.size(), 1);
            assertEquals(l.get(0), "one");
        }
        
        
        // Test illegal index
        try {
            l.remove(-12);
            fail("Shouldn't be able to remove with illegal index.");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(l.size(), 1);
            assertEquals(l.get(0), "one");
        }
    }
    
    /**
     * Tests the set method for the linked list
     */
    @Test
    public void testSet() {
        LinkedList<String> l = new LinkedList<String>(3);
        l.add(0, "zero");
        l.add(1, "one");
        l.add(2, "two");
        
        // Test set at the beginning
        l.set(0, "ZERO");
        assertEquals(l.get(0), "ZERO");
        
        // Test set null
        try {
            l.set(0, null);
            fail("Can't set to null");
        } catch (NullPointerException e) {
            assertEquals(l.get(0), "ZERO");
        }
    }
    
    /**
     * Tests the get method for the linked list
     */
    @Test
    public void testGet() {
        LinkedList<String> l = new LinkedList<String>(3);
        l.add(0, "zero");
        l.add(1, "one");
        l.add(2, "two");
        
        // Test illegal index
        try {
            l.get(-1);
            fail("Can't get from negative index.");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(l.size(), 3);
            assertEquals(l.get(0), "zero");
            assertEquals(l.get(1), "one");
            assertEquals(l.get(2), "two");
        }
    }
    
    /**
     * Tests the setCapacity method for the linked list
     */
    @Test
    public void testSetCapacity() {
        LinkedList<String> l = new LinkedList<String>(10);
        
        //Setting negative capacity
        try {
            l.setCapacity(-1);
            fail("Can't set capacity smaller than zero");
        } catch (IllegalArgumentException e) { 
            assertEquals(l.size(), 0);
        } 
        
        l.add(0, "zero");
        l.add(1, "one");
        l.add(2, "two");

        try {
            l.setCapacity(2);
            fail("Can't set capacity smaller than size");
        } catch (IllegalArgumentException e) {
            assertEquals(l.size(), 3);
        }
    }
    
}
