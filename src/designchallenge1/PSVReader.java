package designchallenge1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer; //https://docs.oracle.com/javase/8/docs/api/index.html?java/util/StringTokenizer.html

public class PSVReader extends Reader {

	public ArrayList<UploadPsv> UploadPsvList = new ArrayList<>();
	
	@Override
	public void loadData() {
		// TODO Auto-generated method stub
		File file = new File("//DLSU Unicalendar.psv");
		try {
			//@SuppressWarnings("resource") 
			Scanner scan = new Scanner(new FileReader(file));
			while (scan.hasNext()) {
				String fileNextLine = scan.nextLine();
				StringTokenizer PSVSplit = new StringTokenizer(fileNextLine, "/|,");
				while (PSVSplit.hasMoreTokens()) {
					UploadPsv uploadFromPsv = new UploadPsv();
					
					uploadFromPsv.setEventName(PSVSplit.nextToken());
					uploadFromPsv.setnMonth(Integer.valueOf(PSVSplit.nextToken()));
					uploadFromPsv.setnDay(Integer.valueOf(PSVSplit.nextToken()));
					uploadFromPsv.setnYear(Integer.valueOf(PSVSplit.nextToken()));
					uploadFromPsv.setColor(PSVSplit.nextToken());
					
					UploadPsvList.add(uploadFromPsv);
				}
			}
			scan.close();
		} catch (FileNotFoundException  e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e){
            e.printStackTrace();
        }
		printData();
}

	@Override
	public void printData() {
		// TODO Auto-generated method stub
		System.out.println("-------------FROM PSV-------------");
        for(int i = 0; i<UploadPsvList.size(); i++) {
            System.out.println(UploadPsvList.get(i).getEventName());
            System.out.println(UploadPsvList.get(i).getnDay());
            System.out.println(UploadPsvList.get(i).getnMonth());
            System.out.println(UploadPsvList.get(i).getnYear());
            System.out.println(UploadPsvList.get(i).getColor());
        }
		
	}

	public ArrayList<UploadPsv> getUploadPsvList(){
		return UploadPsvList;
	}
	
	public void setUploadPsvList(ArrayList<UploadPsv> UploadPsvList) {
		this.UploadPsvList = UploadPsvList;
	}
		
}
