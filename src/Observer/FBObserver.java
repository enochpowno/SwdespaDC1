package Observer;

import GUI.CalendarProgram;
import designchallenge1.NotifFb;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

public class FBObserver extends Observer{
	private FBView fbview;
	private ArrayList<NotifFb> notifFb = new ArrayList<>();
	private Calendar today;
	
	public FBObserver (FBView fb, CalendarProgram cp) {
		this.calendar = cp;
		this.fbview = fb;
		
	}

	@Override
	public void update() {
		today = Calendar.getInstance();
		//System.out.println("Today: " + today.get(Calendar.DATE));
		
		int col = calendar.getCalendarTable().getSelectedColumn();
        int row = calendar.getCalendarTable().getSelectedRow();
		int day = today.get(Calendar.DATE);

		//Added Holidays
		for (int j =0;j<calendar.getHolidaysAdded().getWriteEventsList().size(); j ++){
			if (!notifFb.contains(calendar.getHolidaysAdded().getWriteEventsList().get(j))) {
				notifFb.add(new NotifFb(calendar.getHolidaysAdded().getWriteEventsList().get(j).getnMonth(), calendar.getHolidaysAdded().getWriteEventsList().get(j).getnDay(),
						calendar.getHolidaysAdded().getWriteEventsList().get(j).getnYear(), calendar.getHolidaysAdded().getWriteEventsList().get(j).getEventName(),
						calendar.getHolidaysAdded().getWriteEventsList().get(j).getColor()));

			//if the event is today
			boolean eventToday = calendar.getHolidaysAdded().getWriteEventsList().get(j).getnYear() == calendar.getYearToday()
					&& calendar.getHolidaysAdded().getWriteEventsList().get(j).getnMonth() == calendar.getMonthToday()+1
					&& day == calendar.getHolidaysAdded().getWriteEventsList().get(j).getnDay();

			if (eventToday)
				fbview.showNewEvent(calendar.getHolidaysAdded().getWriteEventsList().get(j).getEventName(), calendar.getMonthToday()+1, day,
					calendar.getYearToday(), addedConvertColor(j));
		    }
	    }

	    //Public Holiday
		for (int j =0;j<calendar.getHolidaysPublic().getUploadCsvList().size(); j ++){
			if (!notifFb.contains(calendar.getHolidaysAdded().getWriteEventsList().get(j))) {
				notifFb.add(new NotifFb(calendar.getHolidaysPublic().getUploadCsvList().get(j).getnMonth(), calendar.getHolidaysPublic().getUploadCsvList().get(j).getnDay(),
						calendar.getHolidaysPublic().getUploadCsvList().get(j).getnYear(), calendar.getHolidaysPublic().getUploadCsvList().get(j).getEventName(),
						calendar.getHolidaysPublic().getUploadCsvList().get(j).getColor()));

				//if the event is today
				boolean eventToday = calendar.getHolidaysPublic().getUploadCsvList().get(j).getnYear() == calendar.getYearToday()
						&& calendar.getHolidaysPublic().getUploadCsvList().get(j).getnMonth() == calendar.getMonthToday()+1
						&& day == calendar.getHolidaysPublic().getUploadCsvList().get(j).getnDay();

				if (eventToday)
					fbview.showNewEvent(calendar.getHolidaysPublic().getUploadCsvList().get(j).getEventName(), calendar.getMonthToday()+1, day,
							calendar.getYearToday(), publicConvertColor(j));
			}
		}

		//School Holiday
		for (int j =0;j<calendar.getHolidaysSchool().getUploadPsvList().size(); j ++){
			if (!notifFb.contains(calendar.getHolidaysSchool().getUploadPsvList().get(j))) {
				notifFb.add(new NotifFb(calendar.getHolidaysSchool().getUploadPsvList().get(j).getnMonth(), calendar.getHolidaysSchool().getUploadPsvList().get(j).getnDay(),
						calendar.getHolidaysSchool().getUploadPsvList().get(j).getnYear(), calendar.getHolidaysSchool().getUploadPsvList().get(j).getEventName(),
						calendar.getHolidaysSchool().getUploadPsvList().get(j).getColor()));

				//if the event is today
				boolean eventToday = calendar.getHolidaysSchool().getUploadPsvList().get(j).getnYear() == calendar.getYearToday()
						&& calendar.getHolidaysSchool().getUploadPsvList().get(j).getnMonth() == calendar.getMonthToday()+1
						&& day == calendar.getHolidaysSchool().getUploadPsvList().get(j).getnDay();

				if (eventToday)
					fbview.showNewEvent(calendar.getHolidaysSchool().getUploadPsvList().get(j).getEventName(), calendar.getMonthToday()+1, day,
							calendar.getYearToday(), schoolConvertColor(j));
			}
		}

	}

	public Color addedConvertColor(int j){
		Color color = Color.GREEN;
		switch(calendar.getHolidaysAdded().getWriteEventsList().get(j).getColor()) {
			case "blue":
				color = Color.BLUE;
				break;
			case "red":
				color = Color.RED;
				break;
			case "green":
				color = Color.GREEN;
				break;
		}
		return color;
	}

	public Color publicConvertColor(int j){
		Color color = Color.GREEN;
		switch(calendar.getHolidaysPublic().getUploadCsvList().get(j).getColor()) {
			case "blue":
				color = Color.BLUE;
				break;
			case "red":
				color = Color.RED;
				break;
			case "green":
				color = Color.GREEN;
				break;
		}
		return color;
	}

	public Color schoolConvertColor(int j){
		Color color = Color.GREEN;
		switch(calendar.getHolidaysSchool().getUploadPsvList().get(j).getColor()) {
			case "blue":
				color = Color.BLUE;
				break;
			case "red":
				color = Color.RED;
				break;
			case "green":
				color = Color.GREEN;
				break;
		}
		return color;
	}
}
