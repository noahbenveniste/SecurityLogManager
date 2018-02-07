package edu.ncsu.csc316.security_log.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.util.ArrayList;
import edu.ncsu.csc316.security_log.util.LogEntryList;

/**
 * Used to read a file of log entries and generate a list of LogEntry objects
 * 
 * @author Noah Benveniste
 */
public class SecurityLogIO {
    
    /**
     * Parses a file of log entries and generates an unsorted list of
     * LogEntry objects
     * 
     * @param fileName the name of the file containing log entries
     * @return a LogEntryList containing LogEntry objects that correspond to each log
     * entry in the file. The list is unsorted i.e. in the same order as the file
     * @throws IOException if a line is not formatted properly
     */
    public LogEntryList readLogEntriesFromFile( String fileName ) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        ArrayList<LogEntry> list = reader.lines() // add .parallel() to try multi threaded (could be faster)
        .skip(1) // Skips first line
        .map(this::readLogEntry)
        .collect(ArrayList<LogEntry>::new, ArrayList<LogEntry>::add, ArrayList<LogEntry>::addAll);
        reader.close();
        list.sort();
        return new LogEntryList(list);
    }
    
    /**
     * Parses a single string and generates a LogEntry object
     * 
     * @param line the line to parse
     * @return a LogEntry with the data contained in the line
     */
    private LogEntry readLogEntry( String line ) {
        String user = null;
        String timeStamp = null;
        String action = null;
        String resource = null;
        
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        
        LogEntry log = null;
        
        try {
            
            user = tokenizer.nextToken();
            timeStamp = tokenizer.nextToken();
            action = tokenizer.nextToken();
            resource = tokenizer.nextToken();

            // timeStamp, action and resource all have an extra leading whitespace. Manually truncate it.
            log = new LogEntry(user, timeStamp.substring(1), action.substring(1), resource.substring(1));
            
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException();
        }
        return log;
    }
    
}
