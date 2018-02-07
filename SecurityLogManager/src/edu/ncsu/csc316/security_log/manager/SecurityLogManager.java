package edu.ncsu.csc316.security_log.manager;

import java.io.IOException;

import edu.ncsu.csc316.security_log.data.LogCounter;
import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.data.TimeStamp;
import edu.ncsu.csc316.security_log.io.SecurityLogIO;
import edu.ncsu.csc316.security_log.util.LogCounterList;
import edu.ncsu.csc316.security_log.util.LogEntryList;

/**
 * Manager class that contains methods for primary operations that program performs.
 * @author Noah Benveniste
 */
public class SecurityLogManager {
    
    /** Keeps track of log entries in file */
    private LogEntryList logEntryList;
    /** Counts the number of log entries with unique action/resource */
    private LogCounterList logCounterList;
    /** Used to read in log entries from file */
    private SecurityLogIO io;
    
    /**
     * Constructs the SecurityLogManager
     * @param fileName the name of the file to read
     * @throws IOException if the file is not formatted properly
     */
    public SecurityLogManager(String fileName) {
        io = new SecurityLogIO();
        try {
            this.logEntryList = io.readLogEntriesFromFile(fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    /**
     * Generates the operational profile given a start time and end time
     * @param startTime the start time
     * @param endTime the end time
     * 
     * @return the operational profile as a single string
     */
    public String generateOperationalProfile( String startTime, String endTime ) {
        // Initialize counter objects and list
        LogCounter.reset();
        logCounterList = new LogCounterList();
        
        // Initialize string builder and output string components
        StringBuilder sb = new StringBuilder();
        String startStr = "OperationalProfile[\n";
        String endStr = "]";
        sb.append(startStr);
        // Get indices for start time and end time
        int startIdx = logEntryList.firstInstanceOf(new TimeStamp(startTime));
        int endIdx = logEntryList.lastInstanceOf(new TimeStamp(endTime));
        
        // If either index is -1, the time isn't in range
        if (startIdx == -1 || endIdx == -1) {
            return sb.append("   No activity was recorded.\n").append(endStr).toString();
        }
        
        // Loop through the log entries creating/incrementing log counters when necessary
        for (int i = startIdx; i <= endIdx; i++) {
            LogEntry current = logEntryList.getLogEntry(i);
            int counterIdx = logCounterList.containsCounter(current.getAction(), current.getResource());
            if (counterIdx == -1) {
                logCounterList.addCounter(current.getAction(), current.getResource());
            } else {
                logCounterList.getCounter(counterIdx).incrementFrequency();
            }
        }
        logCounterList.sortCounters();
        // Go through the log counter list, get the strings, build the output string
        
        for (int i = 0; i < logCounterList.size(); i++) {
            sb.append("   ").append(logCounterList.getCounter(i).toString());
        }
        sb.append(endStr);
        return sb.toString();
    }
    
    /**
     * Generates the user report
     * @param userID the user name of the user to generate the report for
     * 
     * @return the user report as a single string
     */
    public String getUserReport( String userID ) {
        StringBuilder sb = new StringBuilder("Activity Report for ");
        sb.append(userID).append("[\n");
        String endStr = "]";
        int count = 0;
        for (int i = 0; i < logEntryList.size(); i++) {
            LogEntry current = logEntryList.getLogEntry(i);
            if (current.getUser().equals(userID)) {
                sb.append("   ").append(current.getTimeStamp().getOriginalString()).append(" - ").append(current.getAction()).append(" ").append(current.getResource()).append("\n");
                count++;
            }
        }
        if (count == 0) {
            sb.append("   No activity was recorded.\n");
        }
        sb.append(endStr);
        return sb.toString();
    }
    
}
