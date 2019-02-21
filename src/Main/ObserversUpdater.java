package Main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ObserversUpdater implements Runnable {
	private String sDate;
	private String[] evDate;
	Subject notifier;
	
	public ObserversUpdater(ArrayList<Observer> obs, Subject notifier) {
		sDate = updateDate();
		this.notifier = notifier;
	}

	@Override
	public void run() {
		//checks the date today if there is an event and notify all observers else sleep
		while(true){
			evDate = notifier.getDateToday();
			if(!updateDate().equalsIgnoreCase(sDate)) {
				notifier.setAlertToday(false);
				sDate = updateDate();
			}

			if(sDate.equals(evDate[0] + "/" + evDate[1] + "/" +evDate[2]) && !notifier.getAlertToday()) {
				notifier.notifyAllObservers(notifier.getAlertToday());
				notifier.setAlertToday(true);
			}
			try{
				Thread.sleep(1000);

			}catch (Exception c){

			}
		}
	}

	public String updateDate(){
		DateFormat dateToday = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return dateToday.format(date);
	}
}