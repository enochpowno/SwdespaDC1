package Main;
import java.awt.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSVReader extends FileImport {
	 
	public CSVReader() {
		this.fileName = "Philippine Holidays.csv";
		events = new ArrayList<>();
		readData();
	}

	@Override
	public void readData() {
		// TODO read data from csv file
		try {
			FileReader r = new FileReader(fileName);
			BufferedReader bf = new BufferedReader(r);
			
			while (bf.ready()) {
				String line;
				line = bf.readLine();
				String[] event = line.split(",");
				String[] date = event[0].split("/");
				Event e = new Event(Integer.parseInt(date[0]) - 1, Integer.parseInt(date[1]),
									Integer.parseInt(date[2]), event[1], event[2], "csv");
				events.add(e);
			}
			bf.close();
			r.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//@Override
	public void writeData(Event event) {
		if(!event.getName().equalsIgnoreCase("default"))
			events.add(event);
		try {
			FileWriter w = new FileWriter(fileName);
			PrintWriter pw = new PrintWriter(w);
			for(int i = 0; i < events.size(); i++) {
				StringBuilder write = new StringBuilder();
				Event e = events.get(i);
				write.append(e.getMonth());
				write.append("/");
				write.append(e.getDay());
				write.append("/");
				write.append(e.getYear());
				write.append(",");
				write.append(e.getName());
				write.append(",");
				write.append(e.getColor());
				pw.println(write.toString());
			}
			pw.close();
			w.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	@Override
	public Color findColor(int year, int month, int day, String sEvent) {
		return super.findColor(year, month, day, sEvent);
	}

	@Override
	public String getFileName(){
		return super.getFileName();
	}
}
