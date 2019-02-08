package designchallenge1;

public class Event {
	
	public int nMonth, nDay, nYear;
	public String event;
	public String color;
	
	public Event(int nMonth, int nDay, int nYear, String event, String color) {
		this.nMonth = nMonth;
		this.nDay = nDay;
		this.nYear = nYear;
		this.event = event;
		this.color = color;
		
	}
	
	public boolean checkYearMonth() {
		//check if hindi legit yung date
		return false;
		
	}
	
	public boolean checkSameDate() {
		//if same day, attach lang yung event
		return false;
	}
	
	public int getnMonth() {
		return nMonth;
	}
	public int getnDay() {
		return nDay;
	}
	public int getnYear() {
		return nYear;
	}
	public String getEvent() {
		return event;
	}
	public String getColor() {
		return color;
	}
	
	
	
}
