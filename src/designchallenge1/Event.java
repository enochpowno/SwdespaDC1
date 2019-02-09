package designchallenge1;

abstract class Event {
	
	private int nMonth;
	private int nDay;
	private int nYear;
	private String eventName;
	private String color;
	
	
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void setnMonth(int nMonth) {
		this.nMonth = nMonth;
	}

	public void setnDay(int nDay) {
		this.nDay = nDay;
	}

	public void setnYear(int nYear) {
		this.nYear = nYear;
	}

	public void setColor(String color) {
		this.color = color;
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
	
	public String getColor() {
		return color;
	}
	
	
	
}
