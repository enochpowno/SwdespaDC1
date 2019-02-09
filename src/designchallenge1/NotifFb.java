package designchallenge1;

public class NotifFb extends Event {
	
	public NotifFb() {
		
	}

	public NotifFb(int nMonth, int nDay, int nYear, String eventName, String color ) {
		 
		setnMonth(nMonth);
		setnDay(nDay);
		setnYear(nYear);
		setEventName(eventName);
		setColor(color);
	}
	

}
