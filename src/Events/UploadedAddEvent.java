package Events;

import java.io.*;
import java.util.ArrayList;

public class UploadedAddEvent extends Event {
    private static final String CSV_SEPARATOR_SLASH = "/";
    private static final String CSV_SEPARATOR_COMMA = ",";

    public UploadedAddEvent() {
    }

    public UploadedAddEvent(int eventMonth, int eventDay, int eventYear, String eventName, String eventColor) {
        setEventMonth(eventMonth);
        setEventDay(eventDay);
        setEventYear(eventYear);
        setEventName(eventName);
        setEventColor(eventColor);
    }

    public void uploadAddedEvents(ArrayList<UploadedAddEvent> addEventArrayList) throws IOException {

        try{
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("AddedEvents.csv")));


                for (int i = 0; i < addEventArrayList.size(); i++) {
                    StringBuffer writerBuffer = new StringBuffer();
                    //Month
                    writerBuffer.append(addEventArrayList.get(i).getEventMonth());
                    writerBuffer.append(CSV_SEPARATOR_SLASH);

                    //Day
                    writerBuffer.append(addEventArrayList.get(i).getEventDay());
                    writerBuffer.append(CSV_SEPARATOR_SLASH);

                    //Year
                    writerBuffer.append(addEventArrayList.get(i).getEventYear());
                    writerBuffer.append(CSV_SEPARATOR_COMMA);

                    //Name
                    writerBuffer.append(addEventArrayList.get(i).getEventName());
                    writerBuffer.append(CSV_SEPARATOR_COMMA);

                    //Color
                    writerBuffer.append(addEventArrayList.get(i).getEventColor());

                    writer.write(writerBuffer.toString());
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            }
            catch (UnsupportedEncodingException e) {}
            catch (FileNotFoundException e){}
            catch (IOException e){}
        }

	public void Dsplay(){
		System.out.println("Details: Add designchallenge1.Event Class");
		System.out.println(getEventName());
		System.out.println(getEventMonth());
		System.out.println(getEventColor());
		System.out.println(getEventYear());
		System.out.println(getEventColor());
	}


}
