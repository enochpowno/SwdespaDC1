package Data;

import Events.UploadCSV;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PSVReader extends Reader {

    public ArrayList<UploadCSV> uploadCSVList = new ArrayList<>();

    @Override
    public void loadData() {
        File file = new File("DLSU Unicalendar.psv");
        try {
            Scanner scn = new Scanner(new FileReader(file));
            while (scn.hasNext()) {
                String holidayNextLine = scn.nextLine();
                StringTokenizer holidaySplit = new StringTokenizer(holidayNextLine, "/|,");
                while (holidaySplit.hasMoreTokens()) {
                    UploadCSV uploadCSVTemp = new UploadCSV();

                    uploadCSVTemp.setEventName(holidaySplit.nextToken());
                    uploadCSVTemp.setEventMonth(Integer.valueOf(holidaySplit.nextToken()));
                    uploadCSVTemp.setEventDay(Integer.valueOf(holidaySplit.nextToken()));
                    uploadCSVTemp.setEventYear(Integer.valueOf(holidaySplit.nextToken()));
                    uploadCSVTemp.setEventColor(holidaySplit.nextToken());

                    uploadCSVList.add(uploadCSVTemp);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        printData();
    }

    public void printData() {

        System.out.println("-------------School Holidays-------------");
        for(int i = 0; i< uploadCSVList.size(); i++){
            System.out.println(uploadCSVList.get(i).getEventName());
            System.out.println(uploadCSVList.get(i).getEventMonth());
            System.out.println(uploadCSVList.get(i).getEventDay());
            System.out.println(uploadCSVList.get(i).getEventYear());
            System.out.println(uploadCSVList.get(i).getEventColor());
        }
    }

    public ArrayList<UploadCSV> getUploadHolidayList() {
        return uploadCSVList;
    }

    public void setUploadHolidayList(ArrayList<UploadCSV> uploadCSVList) {
        this.uploadCSVList = uploadCSVList;
    }
}