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
     * @param index
     * @return
     */
    public LogEntry getLogEntry(int index) {
        return list.get(index);
    }
    
    /**
     * 
     * @param t
     * @return
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
     * 
     * @param t
     * @return
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
    
    /**
     * 
     * @return
     */
    public String getFullProfile() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            sb.append(list.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
    
}
