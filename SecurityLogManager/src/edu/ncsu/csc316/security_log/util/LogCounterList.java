package edu.ncsu.csc316.security_log.util;

import edu.ncsu.csc316.security_log.data.LogCounter;

/**
 * 
 * @author Noah Benveniste
 */
public class LogCounterList {
    
    private ArrayList<LogCounter> list;
    
    /**
     * 
     */
    public LogCounterList() {
        this.list = new ArrayList<LogCounter>();
    }
    
    /**
     * 
     * @param action
     * @param resource
     * @return
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
     * 
     * @param l
     */
    public void addCounter(String action, String resource) {
        list.addSorted(new LogCounter(action, resource));
        LogCounter.incrementTotalLogEntryCount();
    }
    
    /**
     * 
     * @param idx
     * @return
     */
    public LogCounter getCounter(int idx) {
        return list.get(idx);
    }
    
    /**
     * 
     * @return
     */
    public int size() {
        return list.size();
    }
    
    public void sortCounters() {
        this.list.sort();
    }
    
}
