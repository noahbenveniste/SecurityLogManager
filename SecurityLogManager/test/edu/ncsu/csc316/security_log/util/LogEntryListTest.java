package edu.ncsu.csc316.security_log.util;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.security_log.data.TimeStamp;
import edu.ncsu.csc316.security_log.io.SecurityLogIO;

/**
 * 
 * @author Noah Benveniste
 */
public class LogEntryListTest {
    /** */
    private LogEntryList logs;
    
    /**
     * @throws IOException 
     * 
     */
    @Before
    public void setUp() throws IOException {
        SecurityLogIO io = new SecurityLogIO();
        try {
            logs = io.readLogEntriesFromFile("input/activityLog_small.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
    
    /**
     * 
     */
    @Test
    public void testFirstInstanceOf() {
        /** activityLog_small.txt sorted
        05/04/2015 02:09:40PM fzalcala sort ICD-9 Code 196
        06/09/2015 06:26:04AM fzalcala sort ICD-9 Code 196
        07/18/2015 07:57:42PM quhundley sort ICD-9 Code 196
        10/04/2015 12:17:49PM fzalcala sort ICD-9 Code 196
        02/04/2016 08:49:22AM quhundley sort ICD-9 Code 196
        08/04/2016 06:57:34AM fzalcala resolve message M2964
        10/07/2016 07:08:47AM fzalcala sort ICD-9 Code 196
        11/20/2016 02:07:54PM quhundley sort ICD-9 Code 196
        04/26/2017 12:33:15PM fzalcala sort ICD-9 Code 196
        07/03/2017 12:36:05AM fzalcala sort ICD-9 Code 196
        08/04/2017 11:01:45AM quhundley sort ICD-9 Code 196
        08/10/2017 05:10:54AM fzalcala sort ICD-9 Code 196
        08/26/2017 08:15:06AM fzalcala sort ICD-9 Code 196
        09/21/2017 08:50:13AM quhundley import office visit OV04312
        10/24/2017 11:38:02AM fzalcala sort ICD-9 Code 196
        11/20/2017 11:38:22AM fzalcala sort ICD-9 Code 196
        */
        assertEquals(1, logs.firstInstanceOf(new TimeStamp("05/23/2015 11:52:33AM")));
        assertEquals(0, logs.firstInstanceOf(new TimeStamp("05/01/2015 11:52:33AM")));
        assertEquals(0, logs.firstInstanceOf(new TimeStamp("05/01/1995 11:52:33AM")));
        assertEquals(0, logs.firstInstanceOf(new TimeStamp("05/04/2015 02:09:39PM")));
        assertEquals(0, logs.firstInstanceOf(new TimeStamp("05/04/2015 02:09:40PM")));
        assertEquals(1, logs.firstInstanceOf(new TimeStamp("05/04/2015 02:09:41PM")));
    }

    /**
     * 
     */
    @Test
    public void testLastInstanceOf() {
        /** activityLog_small.txt sorted
        05/04/2015 02:09:40PM fzalcala sort ICD-9 Code 196
        06/09/2015 06:26:04AM fzalcala sort ICD-9 Code 196
        07/18/2015 07:57:42PM quhundley sort ICD-9 Code 196
        10/04/2015 12:17:49PM fzalcala sort ICD-9 Code 196
        02/04/2016 08:49:22AM quhundley sort ICD-9 Code 196
        08/04/2016 06:57:34AM fzalcala resolve message M2964
        10/07/2016 07:08:47AM fzalcala sort ICD-9 Code 196
        11/20/2016 02:07:54PM quhundley sort ICD-9 Code 196
        04/26/2017 12:33:15PM fzalcala sort ICD-9 Code 196
        07/03/2017 12:36:05AM fzalcala sort ICD-9 Code 196
        08/04/2017 11:01:45AM quhundley sort ICD-9 Code 196
        08/10/2017 05:10:54AM fzalcala sort ICD-9 Code 196
        08/26/2017 08:15:06AM fzalcala sort ICD-9 Code 196
        09/21/2017 08:50:13AM quhundley import office visit OV04312
        10/24/2017 11:38:02AM fzalcala sort ICD-9 Code 196
        11/20/2017 11:38:22AM fzalcala sort ICD-9 Code 196
        */
        
        assertEquals(15, logs.lastInstanceOf(new TimeStamp("11/20/2017 11:38:23AM")));
        assertEquals(15, logs.lastInstanceOf(new TimeStamp("11/20/2017 11:38:22AM")));
        assertEquals(14, logs.lastInstanceOf(new TimeStamp("11/20/2017 11:38:21AM")));
    }
}
