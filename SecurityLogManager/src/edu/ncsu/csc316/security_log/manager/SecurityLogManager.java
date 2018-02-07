package edu.ncsu.csc316.security_log.manager;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc316.security_log.data.LogCounter;
import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.data.TimeStamp;
import edu.ncsu.csc316.security_log.io.SecurityLogIO;
import edu.ncsu.csc316.security_log.util.ARComparator;
import edu.ncsu.csc316.security_log.util.LogCounterList;
import edu.ncsu.csc316.security_log.util.LogEntryList;
import edu.ncsu.csc316.security_log.util.UserComparator;

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
    public String generateOperationalProfile( String startStr, String endStr ) {
        // Initialize counter objects and list
        LogCounter.reset();
        logCounterList = new LogCounterList();
        
        // Create time stamp objects corresponding to the start and end time
        TimeStamp startTime = new TimeStamp(startStr);
        TimeStamp endTime = new TimeStamp(endStr);
        
        // Initialize string builder and output string components
        StringBuilder sb = new StringBuilder();
        String headerStart = "OperationalProfile[\n";
        String headerEnd = "]";
        sb.append(headerStart);
        
        // quick sort the list using comparator for action, resource, timestamp, username
        this.logEntryList.sortLogs(new ARComparator<LogEntry>());
        
        // Loop through the list. If either action or resource are new,
        // i.e. logEntryList[i].getAction() != currentAction || logEntryList[i].getResource() != currentResource
        // create a new counter object, add it to the list. Else, check the timestamp. If
        // its value is between the integer value for startTime and endTime, increment the most
        // recently created counter in the counter list. 
        String prevAction = "";
        String prevResource = "";
        for (int i = 0; i < logEntryList.size(); i++) {
            LogEntry currEntry = logEntryList.getLogEntry(i);
            String currAction = currEntry.getAction();
            String currResource = currEntry.getResource();
            long currTime = currEntry.getTimeStamp().getVal();
            if (currTime >= startTime.getVal() && currTime <= endTime.getVal()) {
                if (!prevAction.equals(currAction) || !prevResource.equals(currResource)) {
                    prevAction = currAction;
                    prevResource = currResource;
                    logCounterList.addCounter(prevAction, prevResource);
                } else {
                    logCounterList.getCounter(this.logCounterList.size() - 1).incrementFrequency();
                }
            }
        }
        
        // quick sort the counter list
        logCounterList.sortCounters();
              
//        // Get indices for start time and end time
//        int startIdx = logEntryList.firstInstanceOf(new TimeStamp(startTime));
//        int endIdx = logEntryList.lastInstanceOf(new TimeStamp(endTime));
//        
//        // If either index is -1, the time isn't in range
//        if (startIdx == -1 || endIdx == -1) {
//            return sb.append("   No activity was recorded.\n").append(headerEnd).toString();
//        }
//        
//        // Loop through the log entries creating/incrementing log counters when necessary
//        for (int i = startIdx; i <= endIdx; i++) {
//            LogEntry current = logEntryList.getLogEntry(i);
//            int counterIdx = logCounterList.containsCounter(current.getAction(), current.getResource());
//            if (counterIdx == -1) {
//                logCounterList.addCounter(current.getAction(), current.getResource());
//            } else {
//                logCounterList.getCounter(counterIdx).incrementFrequency();
//            }
//        }
//        logCounterList.sortCounters();
//        // Go through the log counter list, get the strings, build the output string
        
        // loop through counters, concatenate return string
        
        if (logCounterList.size() == 0) {
            return sb.append("   No activity was recorded.\n").append(headerEnd).toString();
        }
                
        for (int i = 0; i < logCounterList.size(); i++) {
            sb.append("   ").append(logCounterList.getCounter(i).toString());
        }
        sb.append(headerEnd);
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
//        int count = 0;
//        for (int i = 0; i < logEntryList.size(); i++) {
//            LogEntry current = logEntryList.getLogEntry(i);
//            if (current.getUser().equals(userID)) {
//                sb.append("   ").append(current.getTimeStamp().getOriginalString()).append(" - ").append(current.getAction()).append(" ").append(current.getResource()).append("\n");
//                count++;
//            }
//        }
        // Sort the log entry list based on name
        this.logEntryList.sortLogs(new UserComparator<LogEntry>());
//        for (int i = 0; i < logEntryList.size(); i++) {
//            System.out.println(logEntryList.getLogEntry(i).toString());
//        }
        
        // Binary search for the user name
        int idx = logEntryList.containsUser(userID);
        if (idx == -1) {
            sb.append("   No activity was recorded.\n");
        }
        
        // Index up and down from the returned index to get all logs associated with that name
        StringBuilder oldSB = new StringBuilder(logEntryList.getLogEntry(idx).toString());
        int i = idx - 1;
        // Search up
        while (i >= 0 && logEntryList.getLogEntry(i).getUser().equals(userID)) {
            StringBuilder newSB = new StringBuilder(logEntryList.getLogEntry(i).toString());
            newSB.append(oldSB.toString());
            oldSB = newSB;
            i--;
        }
        // Search down
        i = idx + 1;
        while (i < this.logEntryList.size() && logEntryList.getLogEntry(i).getUser().equals(userID)) {
            oldSB.append(logEntryList.getLogEntry(i).toString());
            i++;
        }
        sb.append(oldSB);
        sb.append(endStr);
        return sb.toString();
    }
    
}
