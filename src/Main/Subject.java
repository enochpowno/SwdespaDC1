package Main;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Subject {
    private ArrayList<Observer> observers;
    private Thread tri;
    private ArrayList<FileImport> importer;
    private boolean alertToday;
    public Subject() {
        this.observers = new ArrayList<>();
        alertToday = false;
        importer = new ArrayList<>();
        importer.add(new CSVReader());
        importer.add(new PSVReader());
        new FBObserver(this);
        new SMSObserver(this);
        tri = new Thread(new ObserversUpdater(observers, this));
        tri.start();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public ArrayList<String> getEventToday(){
        String[] sDate = getDateToday();
        ArrayList<String> sEvent = new ArrayList<>();
//        ArrayList<String> main
        //get Date today and check if there is event use CSVReader and PSVReader for this method
        //ArrayList<String> sEvent = csvImport.findEvent(Integer.parseInt(sDate[0]), Integer.parseInt(sDate[1]) , Integer.parseInt(sDate[2]));
        for (int i = 0; i < importer.size(); i++) {
            ArrayList<ArrayList<String>>  temp = importer.get(i).findEvent(Integer.parseInt(sDate[0]), Integer.parseInt(sDate[1]) , Integer.parseInt(sDate[2]));
            for (int j = 0; j < temp.size(); j++)
                for (int k = 0; k < temp.get(j).size(); k++)
                    sEvent.add(temp.get(j).get(k));
        }
        return sEvent;
    }

    public String[] getDateToday(){
        DateFormat dateToday = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String[] sDate = dateToday.format(date).split("/"); // [0] yyyy [1] MM [2] dd
        return sDate;
    }

    public Color getEventColor(int year, int month, int day, String sEvent){
        Color a = null;
        int i = 0;
        boolean found = false;
        while (i < importer.size() && !found) {
        	a = importer.get(i).findColor(year, month, day, sEvent);
        	if (a != null)
        		found = true;
        	i++;
        }
        if (a == null)
        	a = Color.black;
        return a;
    }

    public String getColor(int year, int month, int day, String sEvent){
        String color = null;
        boolean found = false;
        for (int i = 0;i < importer.size() && !found; i++) {
            color = importer.get(i).getColor(year, month, day, sEvent);
            if (color != null)
                found = true;
        }
        if (color == null)
            color = "black";

        return color;
    }


    public ArrayList<String> findEvent(int year, int month, int day){
        ArrayList<String> sEvents = new ArrayList<>();
        for (int i = 0; i < importer.size(); i++) {
            ArrayList<ArrayList<String>> tempEvents = importer.get(i).findEvent(year, month, day);
            for (int j = 0; j < tempEvents.size(); j++) {
                for (int k = 0; k < tempEvents.get(j).size(); k++) {
                    sEvents.add(tempEvents.get(j).get(k));
                }
            }
        }
        return sEvents;
    }

    public void passData(Event event){
        for (int i = 0; i < importer.size(); i++) {
            if (event.getFileType().equals("csv") && importer.get(i) instanceof CSVReader) {
                importer.get(i).writeData(event);
                break;
            }
            else if (event.getFileType().equals("psv") && importer.get(i) instanceof PSVReader) {
                importer.get(i).writeData(event);
                break;
            }
        }
    }

    public void notifyAllObservers(boolean sentToday){
        for(Observer o: observers){
            if(sentToday)
                break;
            o.update();
        }
    }

    public FileImport getAnImporter(String sName) {
        FileImport a = null;

        for (int i = 0; i < importer.size() && a == null; i++) {

            if(sName.equalsIgnoreCase("csv") && importer.get(i) instanceof CSVReader)
                a = importer.get(i);
            else if(sName.equalsIgnoreCase("psv") && importer.get(i) instanceof PSVReader)
                a = importer.get(i);
        }

        return a;
    }

   public void setAlertToday(boolean alertToday){
        this.alertToday = alertToday;
   }

   public boolean getAlertToday(){

        return alertToday;
   }
}
