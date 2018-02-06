package edu.ncsu.csc316.security_log.data;

/**
 * 
 * @author Noah Benveniste
 */
public class TimeStamp implements Comparable<TimeStamp> {

//    private String month;
//    private String day;
//    private String year;
//    private String hour;
//    private String minute;
//    private String second;
//    private boolean isAM;
    private String originalString;
    private long val;
    
    /**
     * 
     * @param s
     */
    public TimeStamp( String s ) {
        this.originalString = s;
        // s is of the form "mm/dd/yyyy hh:mm:ssXX", XX = AM | PM
        // Parse the string into a 12 digit long int for a single comparison
        long init = 100L;
        
        // Need to compare by year first. Multiply init by the year
        init = init * Integer.parseInt(s.substring(6, 10));
        
        // Add the digits for the month, multiply by 100 to get more digits at the end
        init = init + Integer.parseInt(s.substring(0, 2));
        init = init * 100;
        
        // Add days next
        init = init + Integer.parseInt(s.substring(3, 5));
        init = init * 100;
                
        // Get the hour in 24 hour format. If the string ends with AM and is 12:xx, subtract
        // 12 from the hour. If the string ends with PM and is NOT 12:xx, add 12.
        int hr = Integer.parseInt(s.substring(11, 13));
        String amPm = s.substring(19);
        if (amPm.equals("AM") && hr == 12) {
            hr -= 12;
        } else if (amPm.equals("PM") && hr != 12) {
            hr += 12;
        }
        init = init + hr;
        init = init * 100;
        
        // Add minutes next
        init = init + Integer.parseInt(s.substring(14, 16));
        init = init * 100;
        
        // Add seconds last
        init = init + Integer.parseInt(s.substring(17, 19));
        
        // Set the field
        this.val = init;
        
//        String[] dateTime = s.split("\\s+");
//        String date = dateTime[0];
//        String time = dateTime[1];
//        
//        String[] monthDayYear = date.split("/");
//        this.month = monthDayYear[0];
//        this.day = monthDayYear[1];
//        this.year = monthDayYear[2];
//        
//        String[] hourMinSec = time.split(":");
//        this.hour = hourMinSec[0];
//        this.minute = hourMinSec[1];
//        
//        String temp = hourMinSec[2];
//        StringBuilder sb = new StringBuilder();
//        this.second = sb.append( temp.charAt(0) ).append( temp.charAt(1) ).toString();
//        sb = new StringBuilder();
//        String dayOrNight = sb.append( temp.charAt(2) ).append( temp.charAt(3) ).toString();
//        this.isAM = dayOrNight.equals("AM");
    }
    
    /**
     * 
     * @return
     */
    public long getVal() {
        return this.val;
    }
//    public String getMonth() {
//        return month;
//    }
//
//    public String getDay() {
//        return day;
//    }
//
//    public String getYear() {
//        return year;
//    }
//
//    public String getHour() {
//        return hour;
//    }
//
//    public String getMinute() {
//        return minute;
//    }
//
//    public String getSecond() {
//        return second;
//    }
//
//    public boolean isAM() {
//        return isAM;
//    }
//    
    public String getOriginalString() {
        return this.originalString;
    }

    /**
     * 
     */
    @Override
    public int compareTo( TimeStamp other ) {
        long result =  this.val - other.getVal();
        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        } else {
            return 0;
        }
//        // Sorts in order by year, month, day, AM vs PM, hour, minute, second
//        if (!this.getYear().equals(other.getYear())) {
//            return this.getYear().compareTo(other.getYear());
//        } else if (!this.getMonth().equals(other.getMonth())) {
//            return this.getMonth().compareTo(other.getMonth());
//        } else if (!this.getDay().equals(other.getDay())) {
//            return this.getDay().compareTo(other.getDay());
//        } else if (this.isAM() != other.isAM) {
//            if (this.isAM()) { // Makes this time "greater than" the other time
//                return -1;
//            } else {
//                return 1;
//            }
//        } else if (!this.getHour().equals(other.getHour())) {
//            return this.getHour().compareTo(other.getHour());
//        } else if (!this.getMinute().equals(other.getMinute())) {
//            return this.getMinute().compareTo(other.getMinute());
//        } else if (!this.getSecond().equals(other.getSecond())) {
//            return this.getSecond().compareTo(other.getSecond());
//        } else {
//            return 0; // Equal time stamps
//        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        TimeStamp other = (TimeStamp) obj;
        return (this.val == other.getVal());
//        if (!day.equals(other.day))
//            return false;
//        if (!hour.equals(other.hour))
//            return false;
//        if (isAM != other.isAM)
//            return false;
//        if (!minute.equals(other.minute))
//            return false;
//        if (!month.equals(other.month))
//            return false;
//        if (!originalString.equals(other.originalString))
//            return false;
//        if (!second.equals(other.second))
//            return false;
//        if (!year.equals(other.year))
//            return false;
//        return true;
    }

}
