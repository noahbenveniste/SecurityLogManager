package edu.ncsu.csc316.security_log.data;

/**
 * @author Noah Benveniste
 *
 */
public class LogCounter implements Comparable<LogCounter> {

    /** Total count of log entries over a given subset of the list */
    private static int totalLogEntryCount = 0;
    
    /** Count of a specific type of log entry with a given action and resource */
    private int freq;
    /** */
    private String action;
    /** */
    private String resource;
    
    /**
     * 
     * @param action
     * @param resource
     */
    public LogCounter( String action, String resource ) {
        this.action = action;
        this.resource = resource;
    }
    
    /**
     * 
     */
    public void incrementFrequency() {
        this.freq++;
    }
    
    /**
     * 
     */
    public void reset() {
        totalLogEntryCount = 0;
    }
    
    /**
     * 
     * @return
     */
    public String getAction() {
        return this.action;
    }
    
    /**
     * 
     * @return
     */
    public String getResource() {
        return this.resource;
    }
    
    /**
     * 
     * @return
     */
    public double calculatePercentage() {
        if ( totalLogEntryCount != 0 ) {
            return (double) this.freq / totalLogEntryCount * 100;
        } else {
            return -1;
        }
    }
    
    /**
     * 
     */
    @Override
    public int compareTo( LogCounter other ) {
        // Sorts first by frequency (high to low), then action, then resource
        return 0;
    }
    
    /**
     * 
     * @param other
     * @return
     */
    public boolean equals( LogCounter other ) {
        return this.getAction().equals(other.getAction()) && this.getResource().equals(other.getResource());
    }

}
