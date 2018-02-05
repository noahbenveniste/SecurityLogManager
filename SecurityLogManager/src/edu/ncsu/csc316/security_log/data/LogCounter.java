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
        this.freq = 1;
    }
    
    /**
     * 
     */
    public static void incrementTotalLogEntryCount() {
        totalLogEntryCount++;
    }
    
    /**
     * 
     */
    public void incrementFrequency() {
        this.freq++;
        totalLogEntryCount++;
    }
    
    /**
     * 
     */
    public static void reset() {
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
    public int getFrequency() {
        return this.freq;
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
        if (this.freq != other.getFrequency()) {
            return other.getFrequency() - this.freq;
        } else if (!this.action.equals(other.getAction())) {
            return this.action.compareTo(other.getAction());
        } else if (!this.resource.equals(other.getResource())) {
            return this.resource.compareTo(other.getResource());
        } else {
            return 0;
        }
    }
    
    /**
     * 
     * @param other
     * @return
     */
    @Override
    public boolean equals( Object obj ) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        LogCounter other = (LogCounter) obj;
        return this.getAction().equals(other.getAction()) && this.getResource().equals(other.getResource());
    }
    
    /**
     * 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(this.action).append(" ").append(this.resource).append(": frequency: ").append(this.freq).append(", percentage: ").append(String.format("%3.1f", calculatePercentage())).append("%\n").toString();
    }

}
