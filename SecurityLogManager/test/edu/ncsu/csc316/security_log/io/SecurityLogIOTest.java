package edu.ncsu.csc316.security_log.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.util.LogEntryList;

/**
 * 
 * @author Noah Benveniste
 */
public class SecurityLogIOTest {

    /**
     * 
     */
    @Test
    public void testReadLogEntriesFromFile() {
        String fileName = "input/activityLog_small.txt";
        LogEntryList logs = null;
        try {
            logs = SecurityLogIO.readLogEntriesFromFile(fileName);
        } catch (FileNotFoundException e) {
            e.getMessage();
            fail();
        }
        assertNotNull(logs);
        assertEquals(16, logs.size());
        //System.out.println(logs.getFullProfile());
        //System.out.println(logs.size());
        LogEntry frontLog = new LogEntry("fzalcala", "05/04/2015 02:09:40PM", "sort", "ICD-9 Code 196");
        LogEntry endLog = new LogEntry("fzalcala", "11/20/2017 11:38:22AM", "sort", "ICD-9 Code 196");
        LogEntry midLog = new LogEntry("quhundley", "09/21/2017 08:50:13AM", "import", "office visit OV04312");
        LogEntry invalidLog = new LogEntry("nnbenven", "02/02/2018 04:29:26PM", "import", "message M2964");
        logs.contains(frontLog);
        assertEquals(15, logs.contains(endLog));
        assertEquals(13, logs.contains(midLog));
        assertEquals(-1, logs.contains(invalidLog));
    }

}
