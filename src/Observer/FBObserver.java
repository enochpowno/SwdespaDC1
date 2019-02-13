package Observer;

import Events.StoreFB;
import GUI.CalendarProgram;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

public class FBObserver extends Observer{
	private FBView fbview;
	private ArrayList<StoreFB> storeFBS = new ArrayList<>();
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
		//int day = Integer.parseInt(calendar.modelCalendarTable.getValueAt(row, col).toString().split(" ")[0]);
		int day = today.get(Calendar.DATE);

		//Added Holidays
		for (int j =0;j<calendar.getHolidaysAdded().getAddEventList().size(); j ++){
			if (!storeFBS.contains(calendar.getHolidaysAdded().getAddEventList().get(j))) {
				storeFBS.add(new StoreFB(calendar.getHolidaysAdded().getAddEventList().get(j).getEventMonth(), calendar.getHolidaysAdded().getAddEventList().get(j).getEventDay(),
						calendar.getHolidaysAdded().getAddEventList().get(j).getEventYear(), calendar.getHolidaysAdded().getAddEventList().get(j).getEventName(),
						calendar.getHolidaysAdded().getAddEventList().get(j).getEventColor()));

			//if the event is today
			boolean eventToday = calendar.getHolidaysAdded().getAddEventList().get(j).getEventYear() == calendar.getYearToday()
					&& calendar.getHolidaysAdded().getAddEventList().get(j).getEventMonth() == calendar.getMonthToday()+1
					&& day == calendar.getHolidaysAdded().getAddEventList().get(j).getEventDay();

			if (eventToday)
				fbview.showNewEvent(calendar.getHolidaysAdded().getAddEventList().get(j).getEventName(), calendar.getMonthToday()+1, day,
					calendar.getYearToday(), addedConvertColor(j));
		    }
	    }

	    //Public Holiday
		for (int j =0;j<calendar.getHolidaysPublic().getUploadEventList().size(); j ++){
			if (!storeFBS.contains(calendar.getHolidaysAdded().getAddEventList().get(j))) {
				storeFBS.add(new StoreFB(calendar.getHolidaysPublic().getUploadEventList().get(j).getEventMonth(), calendar.getHolidaysPublic().getUploadEventList().get(j).getEventDay(),
						calendar.getHolidaysPublic().getUploadEventList().get(j).getEventYear(), calendar.getHolidaysPublic().getUploadEventList().get(j).getEventName(),
						calendar.getHolidaysPublic().getUploadEventList().get(j).getEventColor()));

				//if the event is today
				boolean eventToday = calendar.getHolidaysPublic().getUploadEventList().get(j).getEventYear() == calendar.getYearToday()
						&& calendar.getHolidaysPublic().getUploadEventList().get(j).getEventMonth() == calendar.getMonthToday()+1
						&& day == calendar.getHolidaysPublic().getUploadEventList().get(j).getEventDay();

				if (eventToday)
					fbview.showNewEvent(calendar.getHolidaysPublic().getUploadEventList().get(j).getEventName(), calendar.getMonthToday()+1, day,
							calendar.getYearToday(), publicConvertColor(j));
			}
		}

		//School Holiday
		for (int j =0;j<calendar.getHolidaysSchool().getUploadHolidayList().size(); j ++){
			if (!storeFBS.contains(calendar.getHolidaysSchool().getUploadHolidayList().get(j))) {
				storeFBS.add(new StoreFB(calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventMonth(), calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventDay(),
						calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventYear(), calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventName(),
						calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventColor()));

				//if the event is today
				boolean eventToday = calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventYear() == calendar.getYearToday()
						&& calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventMonth() == calendar.getMonthToday()+1
						&& day == calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventDay();

				if (eventToday)
					fbview.showNewEvent(calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventName(), calendar.getMonthToday()+1, day,
							calendar.getYearToday(), schoolConvertColor(j));
			}
		}

	}

	public Color addedConvertColor(int j){
		Color color = Color.GREEN;
		switch(calendar.getHolidaysAdded().getAddEventList().get(j).getEventColor()) {
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
		switch(calendar.getHolidaysPublic().getUploadEventList().get(j).getEventColor()) {
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
		switch(calendar.getHolidaysSchool().getUploadHolidayList().get(j).getEventColor()) {
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


	

