package Data;

import Events.UploadedAddEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HolidaysAdded extends Reader {

    public ArrayList<UploadedAddEvent> addEventList = new ArrayList<>();

    @Override
    public void loadData() {
        File file = new File("AddedEvents.csv");
        try {
            Scanner scn = new Scanner(new FileReader(file));
            while (scn.hasNext()) {
                String eventNextLine = scn.nextLine();
                StringTokenizer eventSplit = new StringTokenizer(eventNextLine, "//,");
                while (eventSplit.hasMoreTokens()) {
                    UploadedAddEvent addEventTemp = new UploadedAddEvent();
                    addEventTemp.setEventMonth(Integer.valueOf(eventSplit.nextToken()));
                    addEventTemp.setEventDay(Integer.valueOf(eventSplit.nextToken()));
                    addEventTemp.setEventYear(Integer.valueOf(eventSplit.nextToken()));
                    addEventTemp.setEventName(eventSplit.nextToken());
                    addEventTemp.setEventColor(eventSplit.nextToken());

                    addEventList.add(addEventTemp);
                }
            }
            scn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        printData();
    }

    @Override
    void printData() {
        System.out.println("-------------Added Events-------------");
        for(int i = 0; i <addEventList.size(); i++){
            System.out.println(addEventList.get(i).getEventMonth());
            System.out.println(addEventList.get(i).getEventDay());
            System.out.println(addEventList.get(i).getEventYear());
            System.out.println(addEventList.get(i).getEventName());
            System.out.println(addEventList.get(i).getEventColor());
        }
    }

    public ArrayList<UploadedAddEvent> getAddEventList() {

        return addEventList;
    }

    public void setAddEventList(ArrayList<UploadedAddEvent> addEventList) {
        this.addEventList = addEventList;
    }
}
