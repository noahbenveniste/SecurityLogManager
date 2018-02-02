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
    public LogEntryList(ArrayList<LogEntry> list) {
        this.list = list;
    }
    
    /**
     * 
     * @param l
     * @return
     */
    public boolean add( LogEntry l ) {
        // Should append logs to the end of the list for best performance
        return false;
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
    
    /**
     * 
     * @param l
     * @return
     */
    public int contains( LogEntry l ) {
        return list.contains(l);
    }
    
    /**
     * 
     * @return
     */
    public int size() {
        return list.size();
    }
    
    public String getFullProfile() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            sb.append(list.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}
