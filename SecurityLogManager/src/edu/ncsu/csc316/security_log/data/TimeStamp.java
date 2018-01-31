package edu.ncsu.csc316.security_log.data;

/**
 * 
 * @author Noah Benveniste
 */
public class TimeStamp implements Comparable<TimeStamp> {

    private String month;
    private String day;
    private String year;
    private String hour;
    private String minute;
    private String second;
    private boolean isAM;
    
    /**
     * 
     * @param s
     */
    public TimeStamp( String s ) {
        // s is of the form "month/day/year hour:minute:second(AM or PM)"
        String[] dateTime = s.split("\\s+");
        String date = dateTime[0];
        String time = dateTime[1];
        
        String[] monthDayYear = date.split("/");
        this.month = monthDayYear[0];
        this.day = monthDayYear[1];
        this.year = monthDayYear[2];
        
        String[] hourMinSec = time.split(":");
        this.hour = hourMinSec[0];
        this.minute = hourMinSec[1];
        
        String temp = hourMinSec[2];
        StringBuilder sb = new StringBuilder();
        this.second = sb.append( temp.charAt(0) ).append( temp.charAt(1) ).toString();
        sb = new StringBuilder();
        String dayOrNight = sb.append( temp.charAt(2) ).append( temp.charAt(3) ).toString();
        this.isAM = dayOrNight.equals("AM");
    }
    
    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getYear() {
        return year;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public String getSecond() {
        return second;
    }

    public boolean isAM() {
        return isAM;
    }

    /**
     * 
     */
    @Override
    public int compareTo( TimeStamp other ) {
        // Sorts in order by year, month, day, AM vs PM, hour, minute, second
        return 0;
    }

}
