package edu.ncsu.csc316.security_log.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.util.ArrayList;
import edu.ncsu.csc316.security_log.util.LinkedListRecursive;
import edu.ncsu.csc316.security_log.util.LogEntryList;

/**
 * 
 * Note: This code is based on CourseRecordIO by Dustin Hollar (from CSC216 lab group).
 * 
 * @author Noah Benveniste
 */
public class SecurityLogIO {

    /**
     * 
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static LogEntryList readLogEntriesFromFile( String fileName ) throws FileNotFoundException {
        // Create scanner object
        Scanner fileReader = new Scanner(new FileInputStream(fileName));
        ArrayList<LogEntry> logs = new ArrayList<LogEntry>();
        // Get header line of file, throw away
        fileReader.nextLine();
        while (fileReader.hasNextLine()) {
            try {
                // Try to parse a line of the file and generate a LogEntry object
                LogEntry log = readLogEntry(fileReader.nextLine());
                // Append the element to the list
                logs.add(log);
            } catch (IllegalArgumentException e) {
                // Skip the line if it can't be read
            }
        }
        // Close scanner
        fileReader.close();
        // Sort the arraylist. Uses quick sort for O(nlogn)
        logs.sort();
        // Use the arraylist to construct a log entry list, return that
        return new LogEntryList(logs);
    }
    
    /**
     * 
     * @param line
     * @return
     */
    private static LogEntry readLogEntry( String line ) {
        Scanner s = new Scanner(line);
        s.useDelimiter(",");
        
        String user = null;
        String timeStamp = null;
        String action = null;
        String resource = null;
        
        LogEntry log = null;
        
        try {
            
            user = s.next().trim();
            timeStamp = s.next().trim();
            action = s.next().trim();
            resource = s.next().trim();
            
            log = new LogEntry( user, timeStamp, action, resource );
            
        } catch (NoSuchElementException e) {
            s.close();
            throw new IllegalArgumentException();
        }
        s.close();
        return log;
    }
    
}
