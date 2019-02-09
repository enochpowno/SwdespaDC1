package designchallenge1;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class CSVReader extends Reader {

	/*String csvFile = "C:\\Users\\Enoch\\Desktop\\DLSU\\SWDESPA\\DC 1\\Sample Files ";
	String line = "";
	String csvSplitBy = ",";
	
	//ArrayList
	
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
    	br.readLine(); 
    	while ((line = br.readLine()) != null) {
    		
    	}
    }*/
	private ArrayList<UploadCsv> UploadCsvList = new ArrayList<>();
	
	@Override
	void loadData() {
		// TODO Auto-generated method stub
		File file = new File("Philippine Holidays.csv");
		try {
			Scanner scan = new Scanner(new FileReader(file));
			while (scan.hasNext()) {
				String csvNextLine = scan.nextLine();
				StringTokenizer CSVSplit = new StringTokenizer(csvNextLine, "//,");
				while(CSVSplit.hasMoreTokens()) {
					UploadCsv uploadfromCsv = new UploadCsv();
					
					uploadfromCsv.setnDay(Integer.valueOf(CSVSplit.nextToken()));
					uploadfromCsv.setnMonth(Integer.valueOf(CSVSplit.nextToken()));
					uploadfromCsv.setnYear(Integer.valueOf(CSVSplit.nextToken()));
					uploadfromCsv.setEventName(CSVSplit.nextToken());
					uploadfromCsv.setColor(CSVSplit.nextToken());
					
					UploadCsvList.add(uploadfromCsv);
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		printData();
	}

	@Override
	void printData() {
		// TODO Auto-generated method stub
		System.out.println("-------------FROM CSV-------------");
        for(int i = 0; i<UploadCsvList.size(); i++) {
            System.out.println(UploadCsvList.get(i).getEventName());
            System.out.println(UploadCsvList.get(i).getnDay());
            System.out.println(UploadCsvList.get(i).getnMonth());
            System.out.println(UploadCsvList.get(i).getnYear());
            System.out.println(UploadCsvList.get(i).getColor());
        }
	}
	
	public ArrayList<UploadCsv> getUploadCsvList(){
		return UploadCsvList;
	}
	
	public void setUploadCsvList(ArrayList<UploadCsv> UploadCsvList) {
		this.UploadCsvList = UploadCsvList;
	}
	
}
