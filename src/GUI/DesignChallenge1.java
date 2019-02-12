/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import designchallenge1.PSVReader;
import designchallenge1.CSVReader;

/**
 *
 * @author Arturo III
 */
public class DesignChallenge1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CalendarFrame c1 = new CalendarFrame();
    	CalendarProgram cp = new CalendarProgram(c1);
    	c1.attachProgram(cp);
    	
    	
    	CSVReader csvReader = new CSVReader();
    	csvReader.loadData();
    	PSVReader psvReader = new PSVReader();
    	psvReader.loadData();
        
    }
}
