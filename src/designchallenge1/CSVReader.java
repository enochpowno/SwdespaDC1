package designchallenge1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class CSVReader {
	
	String csvFile = "C:\\Users\\Enoch\\Desktop\\DLSU\\SWDESPA\\DC 1\\Sample Files ";
	String line = "";
	String csvSplitBy = ",";
	
	//ArrayList
	
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
    	br.readLine(); 
    	while ((line = br.readLine()) != null) {
    		
    	}
    }
}
