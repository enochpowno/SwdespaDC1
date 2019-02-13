package Data;

import Events.UploadPSV;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CSVReader extends Reader {

    private ArrayList<UploadPSV> uploadPSVList = new ArrayList<>();

    @Override
    public void loadData() {

        File file = new File("Philippine Holidays.csv");
        try {
            Scanner scn = new Scanner(new FileReader(file));
            while (scn.hasNext()) {
                String publicNextLine = scn.nextLine();
                StringTokenizer publicSplit = new StringTokenizer(publicNextLine, "//,");
                while (publicSplit.hasMoreTokens()) {
                    UploadPSV uploadPSVTemp = new UploadPSV();
                    uploadPSVTemp.setEventMonth(Integer.valueOf(publicSplit.nextToken()));
                    uploadPSVTemp.setEventDay(Integer.valueOf(publicSplit.nextToken()));
                    uploadPSVTemp.setEventYear(Integer.valueOf(publicSplit.nextToken()));
                    uploadPSVTemp.setEventName(publicSplit.nextToken());
                    uploadPSVTemp.setEventColor(publicSplit.nextToken());

                    uploadPSVList.add(uploadPSVTemp);
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
    public void printData() {
        System.out.println("-------------Public Holidays-------------");
        for(int i = 0; i< uploadPSVList.size(); i++) {
            System.out.println(uploadPSVList.get(i).getEventMonth());
            System.out.println(uploadPSVList.get(i).getEventDay());
            System.out.println(uploadPSVList.get(i).getEventYear());
            System.out.println(uploadPSVList.get(i).getEventName());
            System.out.println(uploadPSVList.get(i).getEventColor());
        }
    }

    public ArrayList<UploadPSV> getUploadEventList() {
        return uploadPSVList;
    }

    public void setUploadEventList(ArrayList<UploadPSV> uploadPSVList) {
        this.uploadPSVList = uploadPSVList;
    }
}