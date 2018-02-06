package edu.ncsu.csc316.security_log.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.util.ArrayList;
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
    public LogEntryList readLogEntriesFromFile( String fileName ) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            ArrayList<LogEntry> list = reader.lines() // add .parallel() to try multi threaded (could be faster)
            .skip(1) // Skips first line
            .map(this::readLogEntry)
            .collect(ArrayList<LogEntry>::new, ArrayList<LogEntry>::add, (a, b) -> {
                for (int i = 0; i < b.size(); i++) {
                    a.add(b.get(i));
                }
            });
            list.sort();
            return new LogEntryList(list);
        } catch (IOException e1) {
            e1.printStackTrace();
            throw new IllegalArgumentException(e1.getMessage());
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException(e2.getMessage());
        }
        // Create scanner object
//        Scanner fileReader = new Scanner(new FileInputStream(fileName));
        
//        // Get header line of file, throw away
//        fileReader.nextLine();
//        while (fileReader.hasNextLine()) {
//            try {
//                // Try to parse a line of the file and generate a LogEntry object
//                LogEntry log = readLogEntry(fileReader.nextLine());
//                // Append the element to the list
//                logs.add(log);
//            } catch (IllegalArgumentException e) {
//                // Skip the line if it can't be read
//            }
//        }
//        // Close scanner
//        fileReader.close();
//        // Sort the arraylist. Uses quick sort for O(nlogn)
//        // logs.sort();
//        // Use the arraylist to construct a log entry list, return that
        
        //logs.sort();
        //return new LogEntryList(this.logs);
    }
    
    /**
     * 
     * @param line
     * @return
     */
    private LogEntry readLogEntry( String line ) {
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
