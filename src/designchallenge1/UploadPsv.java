package designchallenge1;

public class UploadPsv extends Event {

public UploadPsv() {
		
	}

	public UploadPsv(int nMonth, int nDay, int nYear, String eventName, String color ) {
		 
		setnMonth(nMonth);
		setnDay(nDay);
		setnYear(nYear);
		setEventName(eventName);
		setColor(color);
	}
}
