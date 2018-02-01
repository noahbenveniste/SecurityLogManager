package edu.ncsu.csc316.security_log.util;

import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.data.TimeStamp;

/**
 * 
 * @author Noah Benveniste
 */
public class LogEntryList {
    
    /** */
    private ArrayList<LogEntry> list;
    
    /**
     * 
     */
    public LogEntryList() {
        
    }
    
    /**
     * 
     * @param l
     * @return
     */
    public boolean add( LogEntry l ) {
        return false;
    }
    
    /**
     * 
     */
    public void sort() {
        // Use merge sort
    }
    
    /**
     * 
     * @param index
     * @return
     */
    public LogEntry getLogEntry( int index ) {
        return null;
    }
    
    /**
     * 
     * @param t
     * @return
     */
    public int firstInstanceOf( TimeStamp t ) {
        // Index from the start of the list until a log entry with the specified time is found
        return 0;
    }
    
    /**
     * 
     * @param t
     * @return
     */
    public int lastInstanceOf( TimeStamp t ) {
        // Index from the end of the list until a log entry with the specified time is found
        return 0;
    }
    
}
