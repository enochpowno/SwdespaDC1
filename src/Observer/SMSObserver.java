package Observer;

import Events.StoreSMS;
import GUI.CalendarProgram;

import java.util.ArrayList;
import java.util.Calendar;

public class SMSObserver extends Observer{
	private FBObserver fbObserver;
	private SMSView smsview;
	private ArrayList<StoreSMS> storeSMS = new ArrayList<>();
	
	public SMSObserver (SMSView sms, CalendarProgram cp, FBObserver fbObserver) {
		this.calendar = cp;
		this.smsview = sms;
		this.fbObserver = fbObserver;
	}

	@Override
	public void update() {
		
//		int col = calendar.calendarTable.getSelectedColumn();  
//        int row = calendar.calendarTable.getSelectedRow();
//		int day = Integer.parseInt(calendar.modelCalendarTable.getValueAt(row, col).toString().split(" ")[0]);
        
        Calendar date = Calendar.getInstance();	
        int day = date.get(Calendar.DATE);


		// Added Holiday
		for (int j =0;j<calendar.getHolidaysAdded().getAddEventList().size();j++) {
			if (!storeSMS.contains(calendar.getHolidaysAdded().getAddEventList().get(j).getEventName())) {
				storeSMS.add(new StoreSMS(calendar.getHolidaysAdded().getAddEventList().get(j).getEventMonth(), calendar.getHolidaysAdded().getAddEventList().get(j).getEventDay(),
						calendar.getHolidaysAdded().getAddEventList().get(j).getEventYear(), calendar.getHolidaysAdded().getAddEventList().get(j).getEventName(),
						calendar.getHolidaysAdded().getAddEventList().get(j).getEventColor()));

				date.set(Calendar.YEAR, calendar.getHolidaysAdded().getAddEventList().get(j).getEventYear());
				date.set(Calendar.MONTH, calendar.getHolidaysAdded().getAddEventList().get(j).getEventMonth() - 1);
				date.set(Calendar.DAY_OF_MONTH, calendar.getHolidaysAdded().getAddEventList().get(j).getEventDay());
				SMS tempsms = new SMS(calendar.getHolidaysAdded().getAddEventList().get(j).getEventName(), date, fbObserver.addedConvertColor(j));

				boolean eventToday = calendar.getHolidaysAdded().getAddEventList().get(j).getEventYear() == calendar.getYearToday()
						&& calendar.getHolidaysAdded().getAddEventList().get(j).getEventMonth() == calendar.getMonthToday() + 1
						&& day == calendar.getHolidaysAdded().getAddEventList().get(j).getEventDay();

				if (eventToday)
					smsview.sendSMS(tempsms);
			}
		}
		// Public Holiday
		for (int j =0;j<calendar.getHolidaysPublic().getUploadEventList().size();j++) {
			if (!storeSMS.contains(calendar.getHolidaysPublic().getUploadEventList().get(j).getEventName())) {
				storeSMS.add(new StoreSMS(calendar.getHolidaysPublic().getUploadEventList().get(j).getEventMonth(), calendar.getHolidaysPublic().getUploadEventList().get(j).getEventDay(),
						calendar.getHolidaysPublic().getUploadEventList().get(j).getEventYear(), calendar.getHolidaysPublic().getUploadEventList().get(j).getEventName(),
						calendar.getHolidaysPublic().getUploadEventList().get(j).getEventColor()));

				date.set(Calendar.YEAR, calendar.getHolidaysPublic().getUploadEventList().get(j).getEventYear());
				date.set(Calendar.MONTH, calendar.getHolidaysPublic().getUploadEventList().get(j).getEventMonth() - 1);
				date.set(Calendar.DAY_OF_MONTH, calendar.getHolidaysPublic().getUploadEventList().get(j).getEventDay());
				SMS tempsms = new SMS(calendar.getHolidaysPublic().getUploadEventList().get(j).getEventName(), date, fbObserver.publicConvertColor(j));

				boolean eventToday = calendar.getHolidaysPublic().getUploadEventList().get(j).getEventYear() == calendar.getYearToday()
						&& calendar.getHolidaysPublic().getUploadEventList().get(j).getEventMonth() == calendar.getMonthToday() + 1
						&& day == calendar.getHolidaysPublic().getUploadEventList().get(j).getEventDay();

				if (eventToday)
					smsview.sendSMS(tempsms);

			}
		}
		// School Holiday
		for (int j =0;j<calendar.getHolidaysSchool().getUploadHolidayList().size();j++) {
			if (!storeSMS.contains(calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventName())) {
				storeSMS.add(new StoreSMS(calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventMonth(), calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventDay(),
						calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventYear(), calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventName(),
						calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventColor()));

				date.set(Calendar.YEAR, calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventYear());
				date.set(Calendar.MONTH, calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventMonth() - 1);
				date.set(Calendar.DAY_OF_MONTH, calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventDay());
				SMS tempsms = new SMS(calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventName(), date, fbObserver.schoolConvertColor(j));

				boolean eventToday = calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventYear() == calendar.getYearToday()
						&& calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventMonth() == calendar.getMonthToday() + 1
						&& day == calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventDay();

				if (eventToday)
					smsview.sendSMS(tempsms);
			}
		}
	}
}
