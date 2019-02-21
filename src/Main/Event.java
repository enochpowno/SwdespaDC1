package Main;

public class Event {
	private int month;
	private int day;
	private int year; 
	private String name;
	private String color;
	private String fileType;
	
	public Event(int month, int day, int year, String name, String color, String fileType) {
		if (!name.equals(""))
			this.name = name;
		else
			this.name = "default";
		this.month = month;
		this.day = day;
		this.year = year;
		this.color = color;
		this.fileType = fileType;
	}

	public int getMonth() {
		return month + 1;
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
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}

	public String getFileType(){ 
		return fileType; 
	}
}
