package Stock;

public class Time {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private long millisecond;

    //constructor
    public Time(int year, int month, int day, int hour, int minute, int second, long millisecond) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
    }

    //empty constructor
    public Time() {

    }

    //getters and setters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public long getMillisecond() {
        return millisecond;
    }

    public void setMillisecond(long millisecond) {
        this.millisecond = millisecond;
    }

    public String toString() {
        String year = String.valueOf(getYear());

        String month = new String();
        if (getMonth() < 10) {
            month = "0" + getMonth();
        } else {
            month = String.valueOf(getMonth());
        }

        String day = new String();
        if (getDay() < 10) {
            day = "0" + getDay();
        } else {
            day = String.valueOf(getDay());
        }

        String hour = new String();
        if (getHour() < 10) {
            hour = "0" + getHour();
        } else {
            hour = String.valueOf(getHour());
        }

        String minute = new String();
        if (getMinute() < 10) {
            minute = "0" + getMinute();
        } else {
            minute = String.valueOf(getMinute());
        }

        String second = new String();
        if (getSecond() < 10) {
            second = "0" + getSecond();
        } else {
            second = String.valueOf(getSecond());
        }

        String millisecond = String.valueOf(getMillisecond());
        return "Time: (year " + year + "; month " + month + "; day " + day + "; hour " + hour + "; minute " + minute + "; second " + second + "; millisecond " + millisecond + ")";
    }
}
