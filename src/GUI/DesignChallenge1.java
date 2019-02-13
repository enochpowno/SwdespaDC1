package GUI;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Data.CSVReader;
import Data.PSVReader;

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
        CalendarFrame e1 = new CalendarFrame();
    	CalendarProgram cp = new CalendarProgram(e1);
    	e1.attachProgram(cp);
    	
    	CSVReader CSVReader = new CSVReader();
        CSVReader.loadData();
        PSVReader PSVReader = new PSVReader();
        PSVReader.loadData();

    }
}
