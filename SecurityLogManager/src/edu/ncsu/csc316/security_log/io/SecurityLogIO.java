package edu.ncsu.csc316.security_log.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc316.security_log.data.LogEntry;
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
        Scanner fileReader = null;

        fileReader = new Scanner(new FileInputStream(fileName));

        LogEntryList logs = new LogEntryList();
        while (fileReader.hasNextLine()) {
            try {
                LogEntry log = readLogEntry(fileReader.nextLine());
                logs.add(log);
            } catch (IllegalArgumentException e) {
                // skip the line
            }
        }

        fileReader.close();
        return logs;
    }
    
    /**
     * 
     * @param line
     * @return
     */
    public static LogEntry readLogEntry( String line ) {
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
