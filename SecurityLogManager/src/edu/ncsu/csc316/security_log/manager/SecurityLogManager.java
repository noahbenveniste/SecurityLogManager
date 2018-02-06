package edu.ncsu.csc316.security_log.manager;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc316.security_log.data.LogCounter;
import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.data.TimeStamp;
import edu.ncsu.csc316.security_log.io.SecurityLogIO;
import edu.ncsu.csc316.security_log.util.LogCounterList;
import edu.ncsu.csc316.security_log.util.LogEntryList;

/**
 * 
 * @author Noah Benveniste
 */
public class SecurityLogManager {
    
    private LogEntryList logEntryList;
    private LogCounterList logCounterList;
    private SecurityLogIO io;
    
    /**
     * 
     * @param fileName
     * @throws IOException 
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
     * 
     * @param startTime
     * @param endTime
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
        System.out.println(sb.toString());
        return sb.toString();
    }
    
    /**
     * 
     * @param userID
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
        sb.append(endStr);
        if (count == 0) {
            sb.append("   No activity was recorded.\n");
        }
        return sb.toString();
    }
    
}
