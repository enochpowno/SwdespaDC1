package designchallenge1;

import java.io.*;
import java.util.ArrayList;

public class WriteEvents extends Event {
	
	private static final String CSV_SEPARATOR_SLASH = "/";
    private static final String CSV_SEPARATOR_COMMA = ",";
    
    public WriteEvents() {
    	
    }
    
    public WriteEvents(int nMonth, int nDay, int nYear, String eventName, String color ) {
    	
    	setnMonth(nMonth);
    	setnDay(nDay);
    	setnYear(nYear);
    	setEventName(eventName);
    	setColor(color);
    
    }
    
    public void writeEvents(ArrayList<WriteEvents> WriteEventsList, int counter) throws IOException {
    	
    	try {
    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("MergedEvents.csv"))); //https://stackoverflow.com/questions/11371154/outputstreamwriter-vs-filewriter
    		StringBuffer writerBuffer = new StringBuffer(); //https://www.geeksforgeeks.org/stringbuffer-class-in-java/
    		
    		writerBuffer.append(WriteEventsList.get(counter).getnMonth());
    		writerBuffer.append(CSV_SEPARATOR_SLASH);
    		writerBuffer.append(WriteEventsList.get(counter).getnDay());
    		writerBuffer.append(CSV_SEPARATOR_SLASH);
    		writerBuffer.append(WriteEventsList.get(counter).getnYear());
    		writerBuffer.append(CSV_SEPARATOR_COMMA);
    		writerBuffer.append(WriteEventsList.get(counter).getEventName());
    		writerBuffer.append(CSV_SEPARATOR_COMMA);
    		writerBuffer.append(WriteEventsList.get(counter).getColor());
    		
    		writer.write(writerBuffer.toString());
    		writer.newLine();
    		writer.flush();
    		writer.close();
    		
    	}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
    
    public void Display() {
    	System.out.println("Added Event Class");
    	System.out.println(getEventName());
    	System.out.println(getnDay());
    	System.out.println(getnMonth());
    	System.out.println(getnYear());
    	System.out.println(getColor());
    	
    }
	
}
