package designchallenge1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReadFromWriteEvents extends Reader{
	
	public ArrayList<WriteEvents> WriteEventsList = new ArrayList<>();

	@Override
	void loadData() {
		// TODO Auto-generated method stub
		File file = new File("MergedEvents.csv");
				try {
					Scanner scn = new Scanner(new FileReader(file));
					while (scn.hasNext()) {
						String nextLine = scn.nextLine();
						StringTokenizer split = new StringTokenizer(nextLine);
						while (split.hasMoreTokens()) {
							WriteEvents WriteEventsTemp = new WriteEvents();
							WriteEventsTemp.setnMonth(Integer.valueOf(split.nextToken()));
							WriteEventsTemp.setnDay(Integer.valueOf(split.nextToken()));
							WriteEventsTemp.setnYear(Integer.valueOf(split.nextToken()));
							WriteEventsTemp.setEventName(split.nextToken());
							WriteEventsTemp.setColor(split.nextToken());
							
							WriteEventsList.add(WriteEventsTemp);
						}
						scn.close();
					}
					}catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				printData();
	}

	@Override
	void printData() {
		// TODO Auto-generated method stub
		System.out.println("Added Events");
		for(int i = 0; i < WriteEventsList.size(); i++) {
			System.out.println(WriteEventsList.get(i).getnMonth());
			System.out.println(WriteEventsList.get(i).getnDay());
			System.out.println(WriteEventsList.get(i).getnYear());
			System.out.println(WriteEventsList.get(i).getEventName());
			System.out.println(WriteEventsList.get(i).getColor());
		}
		
	}
	
	public ArrayList<WriteEvents> getWriteEventsList(){
		return WriteEventsList;
	}
	
	public void setWriteEventsList(ArrayList<WriteEvents> WriteEventsList) {
		this.WriteEventsList = WriteEventsList; 
	}
	
}
