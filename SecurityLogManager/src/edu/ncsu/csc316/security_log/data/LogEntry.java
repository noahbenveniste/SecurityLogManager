package edu.ncsu.csc316.security_log.data;

/**
 * 
 * @author Noah Benveniste
 */
public class LogEntry implements Comparable<LogEntry> {

    private String user;
    private String action;
    private String resource;
    private TimeStamp timeStamp;
    
    /**
     * 
     * @param action
     * @param resource
     * @param t
     */
    public LogEntry( String user, String timeStamp, String action, String resource ) {
        this.user = user;
        this.timeStamp = new TimeStamp(timeStamp);
        this.action = action;
        this.resource = resource;
    }
    
    /**
     * 
     * @return
     */
    public String getUser() {
        return this.user;
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
    
    public TimeStamp getTimeStamp() {
        return this.timeStamp;
    }
    
    /**
     * 
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
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("   ").append(this.getTimeStamp().getOriginalString()).append(" - ").append(this.getAction()).append(" ").append(this.getResource()).append("\n").toString();
    }

}
