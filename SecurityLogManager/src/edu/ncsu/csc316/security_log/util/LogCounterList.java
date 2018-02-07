package edu.ncsu.csc316.security_log.util;

import edu.ncsu.csc316.security_log.data.LogCounter;

/**
 * A list class that stores LogCounters. Used as a wrapper class to
 * obscure underlying data structure from client.
 * @author Noah Benveniste
 */
public class LogCounterList {
    
    /** Underlying data structure for the list */
    private ArrayList<LogCounter> list;
    
    /**
     * Constructs the LogCounterList
     */
    public LogCounterList() {
        this.list = new ArrayList<LogCounter>();
    }
    
    /**
     * Checks if the list contains a specific counter
     * @param action the action of the counter to search for
     * @param resource the resource of the counter to search for
     * @return the index of the counter, or -1 if there isn't one
     */
    public int containsCounter(String action, String resource) {

        for (int i = 0; i < this.list.size(); i++) {
            LogCounter current = this.list.get(i);
            if (current.getAction().equals(action) && current.getResource().equals(resource)) {
                return i;
            }
        }
        return -1;
        //return list.contains(new LogCounter(action, resource));
    }
    
    /**
     * Adds a counter to the list
     * @param action the action for the counter
     * @param resource the resource for the action
     */
    public void addCounter(String action, String resource) {
        list.addSorted(new LogCounter(action, resource));
        LogCounter.incrementTotalLogEntryCount();
    }
    
    /**
     * Gets a log counter at a specified index
     * @param idx the index of the counter to get
     * @return the counter at the index
     */
    public LogCounter getCounter(int idx) {
        return list.get(idx);
    }
    
    /**
     * Gets the number of counters in the list
     * @return the number of counters in the list
     */
    public int size() {
        return list.size();
    }
    
    /**
     * Sorts the counters based on project specifications
     */
    public void sortCounters() {
        this.list.sort();
    }
    
}
