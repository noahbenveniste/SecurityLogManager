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
    
    /**
     * 
     */
    @Override
    public int compareTo( LogEntry other ) {
        // Sorts in order by time stamp, action and resource
        return 0;
    }

}
