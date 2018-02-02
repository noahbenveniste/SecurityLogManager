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
    private String originalString;
    
    /**
     * 
     * @param s
     */
    public TimeStamp( String s ) {
        this.originalString = s;
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
        if (!this.getYear().equals(other.getYear())) {
            return this.getYear().compareTo(other.getYear());
        } else if (!this.getMonth().equals(other.getMonth())) {
            return this.getMonth().compareTo(other.getMonth());
        } else if (!this.getDay().equals(other.getDay())) {
            return this.getDay().compareTo(other.getDay());
        } else if (this.isAM() != other.isAM) {
            if (this.isAM()) { // Makes this time "greater than" the other time
                return -1;
            } else {
                return 1;
            }
        } else if (!this.getHour().equals(other.getHour())) {
            return this.getHour().compareTo(other.getHour());
        } else if (!this.getMinute().equals(other.getMinute())) {
            return this.getMinute().compareTo(other.getMinute());
        } else if (!this.getSecond().equals(other.getSecond())) {
            return this.getSecond().compareTo(other.getSecond());
        } else {
            return 0; // Equal time stamps
        }
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((hour == null) ? 0 : hour.hashCode());
        result = prime * result + (isAM ? 1231 : 1237);
        result = prime * result + ((minute == null) ? 0 : minute.hashCode());
        result = prime * result + ((month == null) ? 0 : month.hashCode());
        result = prime * result + ((originalString == null) ? 0 : originalString.hashCode());
        result = prime * result + ((second == null) ? 0 : second.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimeStamp other = (TimeStamp) obj;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        if (hour == null) {
            if (other.hour != null)
                return false;
        } else if (!hour.equals(other.hour))
            return false;
        if (isAM != other.isAM)
            return false;
        if (minute == null) {
            if (other.minute != null)
                return false;
        } else if (!minute.equals(other.minute))
            return false;
        if (month == null) {
            if (other.month != null)
                return false;
        } else if (!month.equals(other.month))
            return false;
        if (originalString == null) {
            if (other.originalString != null)
                return false;
        } else if (!originalString.equals(other.originalString))
            return false;
        if (second == null) {
            if (other.second != null)
                return false;
        } else if (!second.equals(other.second))
            return false;
        if (year == null) {
            if (other.year != null)
                return false;
        } else if (!year.equals(other.year))
            return false;
        return true;
    }

    /**
     * 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //return sb.append(month).append("/").append(day).append("/").append(year).append(" ").append(hour).append(":").append(str)
        return originalString;
    }

}
