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
        return list.contains(new LogCounter(action, resource));
    }
    
    /**
     * 
     */
    public String toString() {
        return null;
    }
    
}
