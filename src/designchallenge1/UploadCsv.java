package designchallenge1;

public class UploadCsv extends Event {

public UploadCsv() {
		
	}

	public UploadCsv(int nMonth, int nDay, int nYear, String eventName, String color ) {
		 
		setnMonth(nMonth);
		setnDay(nDay);
		setnYear(nYear);
		setEventName(eventName);
		setColor(color);
	}
}
