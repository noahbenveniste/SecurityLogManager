package edu.ncsu.csc316.security_log.data;

/**
 * A class that represents a log entry. Stores the user, time stamp, action
 * and resource associated with the log entry
 * @author Noah Benveniste
 */
public class LogEntry implements Comparable<LogEntry> {

    private String user;
    private String action;
    private String resource;
    private TimeStamp timeStamp;
    
    /**
     * Constructs a LogEntry
     * @param user the user associated with the log entry
     * @param timeStamp the time at which the log entry was made
     * @param action the action the user took
     * @param resource the resource that was affected by the action
     */
    public LogEntry( String user, String timeStamp, String action, String resource ) {
        this.user = user;
        this.timeStamp = new TimeStamp(timeStamp);
        this.action = action;
        this.resource = resource;
    }
    
    /**
     * Gets the user
     * @return the user
     */
    public String getUser() {
        return this.user;
    }
    
    /**
     * Gets the action
     * @return the action
     */
    public String getAction() {
        return this.action;
    }
    
    /**
     * Gets the resource
     * @return the resource
     */
    public String getResource() {
        return this.resource;
    }
    /**
     * Gets the time stamp
     * @return the time stamp
     */
    public TimeStamp getTimeStamp() {
        return this.timeStamp;
    }
    
    /**
     * Compares two log entries
     * @param other the other log entry to compare
     * @return a negative value if this entry is lexicographically greater than
     * the other, a positive value if vice versa, zero if the two are equal
     */
    @Override
    public int compareTo( LogEntry other ) {
        // Sorts in order by time stamp, action and resource
        if (this.timeStamp.compareTo(other.getTimeStamp()) != 0) {
            return this.timeStamp.compareTo(other.getTimeStamp());
        } else if (!this.action.equals(other.getAction())) {
            return this.getAction().compareTo(other.getAction());
        } else if (!this.getResource().equals(other.getResource())) {
            return this.getResource().compareTo(other.getResource());
        } else {
            return this.getUser().compareTo(other.getUser());
        }
        
    }

    /**
     * Checks if two log entries are equivalent
     * @return true if equivalent, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        LogEntry other = (LogEntry) obj;
        if (!action.equals(other.action))
            return false;
        if (!resource.equals(other.resource))
            return false;
        if (!timeStamp.equals(other.timeStamp))
            return false;
        if (!user.equals(other.user))
            return false;
        return true;
    }

}
