package Main;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class FileImport {
	
	protected String fileName;
	protected List<Event> events;
	
	public abstract void readData();
	public abstract void writeData(Event event);
	
	public String getFileName() {
		return fileName;
	}
	
	public List<Event> getEvents(){
		return events;
	}

	public abstract void setFileName(String fileName);

	public ArrayList<ArrayList<String>> findEvent(int year, int month, int day) {
		//String sEvent = "";
		while(!events.isEmpty())
			events.remove(0);
		readData();
		ArrayList<ArrayList<String>> sEvents = new ArrayList<>();
		ArrayList<String> sEvent = new ArrayList<>();
		ArrayList<String> sHolidays = new ArrayList<>();
		for(int i = 0; i < events.size(); i++) {
			if(fileName.toLowerCase().contains("holiday") && events.get(i).getDay() == day && events.get(i).getMonth() == month)
				sHolidays.add(events.get(i).getName());
			else if(events.get(i).getDay() == day && events.get(i).getMonth() == month && events.get(i).getYear() == year)
				sEvent.add(events.get(i).getName());
		}
		sEvents.add(sEvent);
		sEvents.add(sHolidays);
		return sEvents;
	}

	public String getColor(int year, int month, int day, String sEvent2){
		String color = null;
		for (int j = 0; j < events.size(); j++) {
			if(fileName.toLowerCase().contains("holiday") && events.get(j).getMonth() == month && events.get(j).getDay() == day
					&& events.get(j).getName().equalsIgnoreCase(sEvent2)) {
				color =  events.get(j).getColor();
			}else if (events.get(j).getYear() == year && events.get(j).getMonth() == month && events.get(j).getDay() == day
					&& events.get(j).getName().equalsIgnoreCase(sEvent2)){
				color = events.get(j).getColor();
			}
		}
		return color;
	}

	public Color findColor(int year, int month, int day, String sEvent2){
		Color color = null;

		for (int j = 0; j < events.size(); j++) {
			if(fileName.toLowerCase().contains("holiday") && events.get(j).getMonth() == month && events.get(j).getDay() == day
					&& events.get(j).getName().equalsIgnoreCase(sEvent2)) {
				color = getColor(j, color);
			}else if (events.get(j).getYear() == year &&events.get(j).getMonth() == month && events.get(j).getDay() == day
					&& events.get(j).getName().equalsIgnoreCase(sEvent2)){
				color = getColor(j, color);
			}
		}
		return color;
	}


	private Color getColor (int j, Color color){
		try {

			Field field = Class.forName("java.awt.Color").getField(events.get(j).getColor().toLowerCase().trim());
			color = (Color) field.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return color;
	}

}
