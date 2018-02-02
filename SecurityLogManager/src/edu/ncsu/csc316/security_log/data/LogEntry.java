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
        } else if (!this.getUser().equals(other.getUser())) {
            return this.getUser().compareTo(other.getUser());
        } else {
            throw new IllegalArgumentException("Cannot have duplicate log entries."); 
        }
    }
    
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        result = prime * result + ((resource == null) ? 0 : resource.hashCode());
        result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LogEntry other = (LogEntry) obj;
        if (action == null) {
            if (other.action != null)
                return false;
        } else if (!action.equals(other.action))
            return false;
        if (resource == null) {
            if (other.resource != null)
                return false;
        } else if (!resource.equals(other.resource))
            return false;
        if (timeStamp == null) {
            if (other.timeStamp != null)
                return false;
        } else if (!timeStamp.equals(other.timeStamp))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    /**
     * Returns all logs sorted by time stamp, action, resource and then user.
     * @return all log entries as a string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(this.timeStamp.toString()).append(" ").append(this.user).append(" ").append(this.action).append(" ").append(this.resource).toString();
    }

}
