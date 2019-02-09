package designchallenge1;

public class NotifSms extends Event {
	
	public NotifSms() {
		
	}

	public NotifSms(int nMonth, int nDay, int nYear, String eventName, String color ) {
		 
		setnMonth(nMonth);
		setnDay(nDay);
		setnYear(nYear);
		setEventName(eventName);
		setColor(color);
		
	}
}
