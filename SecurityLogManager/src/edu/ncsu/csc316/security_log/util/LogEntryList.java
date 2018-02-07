package edu.ncsu.csc316.security_log.util;

import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.data.TimeStamp;

/**
 * A list of LogEntry objects. Acts as a wrapper class to limit/obscure client
 * access to underlying data structure.
 * 
 * @author Noah Benveniste
 */
public class LogEntryList {
    
    /** Underlying list */
    private ArrayList<LogEntry> list;
    
    /**
     * Constructs the LogEntryList given an input array-based list
     * @param list an ArrayList of LogEntries. Note that this is only called
     * by SecurityLogIO, not for client use
     */
    public LogEntryList(ArrayList<LogEntry> list) {
        this.list = list;
    }
    
    /**
     * Gets a log entry at a specified index
     * @param index the index to get the log entry
     * @return the log entry at the specified index
     */
    public LogEntry getLogEntry(int index) {
        return list.get(index);
    }
    
    /**
     * Searches for the first instance of a specified time stamp in the list
     * @param t the time stamp object
     * @return -1 if the time stamp is out of the range of the list, or the index
     * of the log entry containing the closest time stamp if it is in range
     */
    public int firstInstanceOf( TimeStamp t ) {
        // Special case where the first time stamp is closest to the input time
        if (this.list.get(0).getTimeStamp().equals(t) || this.list.get(0).getTimeStamp().compareTo(t) > 0) {
            return 0;
        }
        for (int i = 1; i < this.list.size(); i++) {
            if (this.list.get(i).getTimeStamp().equals(t)) {
                // Check if the time at the index is equal
                return i;
                /* Must consider if the exact time isn't in the list. Check if the time at the index to
                 * the left is less than t and the index itself is greater than t. This will be
                 * the closest time to t.
                 */
            } else if (this.list.get(i - 1).getTimeStamp().compareTo(t) < 0 && this.list.get(i).getTimeStamp().compareTo(t) > 0) {
                return i;
            }
        }
        // Time stamp wasn't found
        return -1;
    }
    
    /**
     * Searches for the last instance of a specified time stamp in the list
     * @param t the time stamp object
     * @return -1 if the time stamp is out of the range of the list, or the index
     * of the log entry containing the closest time stamp if it is in range
     */
    public int lastInstanceOf( TimeStamp t ) {
        // Special case where the last time stamp is closest to the input time
        if (this.list.get(list.size() - 1).getTimeStamp().equals(t) || this.list.get(list.size() - 1).getTimeStamp().compareTo(t) < 0) {
            return list.size() - 1;
        }
        for (int i = this.list.size() - 2; i >= 0; i--) {
            if (this.list.get(i).getTimeStamp().equals(t)) {
                return i;
            } else if (this.list.get(i + 1).getTimeStamp().compareTo(t) > 0 && this.list.get(i).getTimeStamp().compareTo(t) < 0) {
                return i;
            }
        }
        // Time stamp wasn't found
        return -1;
    }
    
    /**
     * Checks if the list contains a specific log entry
     * @param l the log entry to check for
     * @return -1 if the log entry is not found, or its index if it is found
     */
    public int contains( LogEntry l ) {
        return list.contains(l);
    }
    
    /**
     * Gets the number of log entries in the list
     * @return the number of log entries
     */
    public int size() {
        return list.size();
    }
    
}
