package edu.ncsu.csc316.security_log.data;

/**
 * An object used to keep track of the number of log entries with unique
 * action/resource pairs when generating the operational profile.
 * 
 * @author Noah Benveniste
 */
public class LogCounter implements Comparable<LogCounter> {

    /** Total count of log entries over a given subset of the list */
    private static int totalLogEntryCount = 0;
    
    /** Count of a specific type of log entry with a given action and resource */
    private int freq;
    /** The action associated with the log */
    private String action;
    /** The resource associated with the log */
    private String resource;
    
    /**
     * Constructs a log counter given a unique action/resource pair
     * @param action the action associated with the log
     * @param resource the resource associated with the log
     */
    public LogCounter( String action, String resource ) {
        this.action = action;
        this.resource = resource;
        this.freq = 1;
    }
    
    /**
     * Increments the total number of logs for a given section of the list
     * when the operational profile is generated
     */
    public static void incrementTotalLogEntryCount() {
        totalLogEntryCount++;
    }
    
    /**
     * Used to increment the frequency of a specific action/resource pair encountered
     * in the list of log entries
     */
    public void incrementFrequency() {
        this.freq++;
        totalLogEntryCount++;
    }
    
    /**
     * Resets the total number of log entries viewed. Used when a new operational
     * profile is generated
     */
    public static void reset() {
        totalLogEntryCount = 0;
    }
    
    /**
     * Gets the action assigned to the counter
     * @return the action
     */
    public String getAction() {
        return this.action;
    }
    
    /**
     * Gets the resource assigned to the counter
     * @return the resource
     */
    public String getResource() {
        return this.resource;
    }
    
    /**
     * Gets the frequency of the action/resource pair
     * @return the frequency
     */
    public int getFrequency() {
        return this.freq;
    }
    
    /**
     * Calculates the percentage of logs in the specified section of the
     * list that have this action and resource
     * @return the percentage
     */
    public double calculatePercentage() {
        if ( totalLogEntryCount != 0 ) {
            return (double) this.freq / totalLogEntryCount * 100;
        } else {
            return -1;
        }
    }
    
    /**
     * Compares a log counter with another first on frequency, action
     * and resource
     * @param other the other LogCounter to compare
     * @return a negative value if this counter lexicographically precedes other,
     * vice versa a positive value, or zero if the counters are equivalent 
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
     * Checks if two counters are equal across all fields
     * @param obj the other counter to check
     * @return true if this and other are equivalent, false otherwise
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
     * Generates a string representation of the counter
     * @return the string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(this.action).append(" ").append(this.resource).append(": frequency: ").append(this.freq).append(", percentage: ").append(String.format("%3.1f", calculatePercentage())).append("%\n").toString();
    }

}
