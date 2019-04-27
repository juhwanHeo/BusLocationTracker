package Scheduler;

public class TimeTable {
	private int hour;
    private int minute;
    private char course;

    public TimeTable(int hour, int minute){
        this(hour, minute,'A');
    }

    public TimeTable(int hour, int minute, char course) {
        this.hour = hour;
        this.minute = minute;
        this.course = course;
    }
    
    public boolean checkTimeTable(TimeTable time) {
    	if(hour == time.hour) {
    		
    	}
    	
    	return true;
    }
    

	public void setHour(int hour){ this.hour=hour; }
    public int getHour() { return hour; }
    public void setMinute(int minute) { this.minute = minute; }
    public int getMinute(){ return minute; }
    public void setCourse(char course){ this.course = course; }
    public char getCourse(){ return course; }

}
