package GUI;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arturo III
 */

import javax.swing.*;
import javax.swing.table.*;

import Data.CSVReader;
import Data.HolidaysAdded;
import Data.PSVReader;
import Observer.FBView;
import Observer.SMSView;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class CalendarProgram{
	
    /**** Day Components ****/
	private int yearBound, monthBound, dayBound, yearToday, monthToday;

    /**** Swing Components ****/
    private JLabel monthLabel, yearLabel;
    private JButton btnPrev, btnNext;
    private JComboBox cmbYear;
    private JFrame frmMain;
    private Container pane;
    private JScrollPane scrollCalendarTable;
    private JPanel calendarPanel;

    /**** Calendar Table Components ***/
    private JTable calendarTable;
    private DefaultTableModel modelCalendarTable;

    /**** Newly Added ***/
    PSVReader PSVReader = new PSVReader();
    CSVReader CSVReader = new CSVReader();
    HolidaysAdded holidaysAdded = new HolidaysAdded();
	int clickedMonth = 0;
	int clickedYear = 0;

    /**** Newly Added - Notifications ***/
    private FBView f1 = new FBView();
    private SMSView s1 = new SMSView();
    CalendarFrame e1;
        
    public void refreshCalendar(int month, int year) {
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som, i, j;
			
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);

		if (month == 0 && year <= yearBound-10)
		    btnPrev.setEnabled(false);
		if (month == 11 && year >= yearBound+100)
		    btnNext.setEnabled(false);
                
		monthLabel.setText(months[month]);
		monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 360, 50);
                
		cmbYear.setSelectedItem(""+year);
		
		for (i = 0; i < 6; i++)
			for (j = 0; j < 7; j++)
				modelCalendarTable.setValueAt(null, i, j);
		
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		// Days in the calendar
		for (i = 1; i <= nod; i++) {
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			modelCalendarTable.setValueAt(i, row, column);
		}

       // CalendarFrame e1 = new CalendarFrame();

		for(i = 0; i<6; i++){
			for(j = 0; j<7; j++) {
				if (modelCalendarTable.getValueAt(i, j) != null) {
					String[] parts = modelCalendarTable.getValueAt(i, j).toString().split(" ");
					//Public Holidays
					for (int z = 0; z < CSVReader.getUploadEventList().size(); z++) {
						if (Integer.valueOf(parts[0]) == CSVReader.getUploadEventList().get(z).getEventDay() && month + 1 == CSVReader.getUploadEventList().get(z).getEventMonth())
							modelCalendarTable.setValueAt(modelCalendarTable.getValueAt(i, j) + " " + CSVReader.getUploadEventList().get(z).getEventName(), i, j);
					}
					//School Holidays
					for (int z = 0; z < PSVReader.getUploadHolidayList().size(); z++) {
						if (Integer.valueOf(parts[0]) == PSVReader.getUploadHolidayList().get(z).getEventDay() && month + 1 == PSVReader.getUploadHolidayList().get(z).getEventMonth()
								&& year == PSVReader.getUploadHolidayList().get(z).getEventYear())
							modelCalendarTable.setValueAt(modelCalendarTable.getValueAt(i, j) + " " + PSVReader.getUploadHolidayList().get(z).getEventName(), i, j);
					}
					//Added Events
					for (int z = 0; z < holidaysAdded.getAddEventList().size(); z++) {
						if (Integer.valueOf(parts[0]) == holidaysAdded.getAddEventList().get(z).getEventDay() && month + 1 == holidaysAdded.getAddEventList().get(z).getEventMonth()
								&& year == holidaysAdded.getAddEventList().get(z).getEventYear())
							modelCalendarTable.setValueAt(modelCalendarTable.getValueAt(i, j) + " " + holidaysAdded.getAddEventList().get(z).getEventName(), i, j);
					}
					//Recently Added
					System.out.println("Month: " + e1.getMonth());
					System.out.println("Month 1: " + month);
					System.out.println("Year: " + e1.getYear());
					if(e1.getDay() == Integer.valueOf(parts[0]) && clickedMonth == month + 1 && clickedYear == e1.getYear()){
						modelCalendarTable.setValueAt(modelCalendarTable.getValueAt(i, j) + " " + e1.getEventName(), i, j);
					}
				}
			}
		}

		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer());
	}
        
	public CalendarProgram(CalendarFrame e1) {
		
		this.e1 = e1;
		
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}
                
		frmMain = new JFrame ("Calendar Application");
		frmMain.setSize(660, 750);
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		monthLabel = new JLabel ("January");
		yearLabel = new JLabel ("Change year:");
		cmbYear = new JComboBox();
		btnPrev = new JButton ("<<");
		btnNext = new JButton (">>");
		modelCalendarTable = new DefaultTableModel() {
		    public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return true;
            }
		};
                
		calendarTable = new JTable(modelCalendarTable);
		calendarTable.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        int col = calendarTable.getSelectedColumn();
		        int row = calendarTable.getSelectedRow();
				/********/
				//CalendarFrame e1 = new CalendarFrame();
				e1.setVisible(true);
                e1.setDay((int)(calendarTable.getValueAt(row,col)));
                e1.setMonth(Month.valueOf(monthLabel.getText().toUpperCase()).getValue());
                clickedMonth = Month.valueOf(monthLabel.getText().toUpperCase()).getValue();
                e1.setYear(Integer.parseInt(cmbYear.getSelectedItem().toString()));
                clickedYear = Integer.parseInt(cmbYear.getSelectedItem().toString());

                //modelCalendarTable.setValueAt(e1.getEventName(), calendarTable.getSelectedRow(), calendarTable.getSelectedColumn());
				//System.out.println(calendarTable.getValueAt(row,col));
				//System.out.println(monthLabel.getText());
				//System.out.println(cmbYear.getSelectedItem().toString());
				//System.out.println(e1.getDay());
				//System.out.println(e1.getColor());
		        /********/
		    }
		    });
		scrollCalendarTable = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);

		calendarPanel.setBorder(BorderFactory.createTitledBorder("Calendar"));
		
		btnPrev.addActionListener(new btnPrev_Action());
		btnNext.addActionListener(new btnNext_Action());
		cmbYear.addActionListener(new cmbYear_Action());
		
		pane.add(calendarPanel);
		calendarPanel.add(monthLabel);
		calendarPanel.add(yearLabel);
		calendarPanel.add(cmbYear);
		calendarPanel.add(btnPrev);
		calendarPanel.add(btnNext);
		calendarPanel.add(scrollCalendarTable);
		
		calendarPanel.setBounds(0, 0, 640, 670);
		monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 200, 50);
		yearLabel.setBounds(20, 610, 160, 40);
		cmbYear.setBounds(460, 610, 160, 40);
		btnPrev.setBounds(20, 50, 100, 50);
		btnNext.setBounds(520, 50, 100, 50);
		scrollCalendarTable.setBounds(20, 100, 600, 500);
                
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		
		GregorianCalendar cal = new GregorianCalendar();
		dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
		monthBound = cal.get(GregorianCalendar.MONTH);
		yearBound = cal.get(GregorianCalendar.YEAR);
		monthToday = monthBound; 
		yearToday = yearBound;
		
		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
		for (int i=0; i<7; i++){
			modelCalendarTable.addColumn(headers[i]);
		}
		
		calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background

		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);

		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		calendarTable.setRowHeight(76);
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(6);
		
		for (int i = yearBound-100; i <= yearBound+100; i++) {
			cmbYear.addItem(String.valueOf(i));
		}
        /****/
        int month = 0;
        int year = 0;

        // Load School Holidays
        PSVReader.loadData();
        for(int i = 0; i < PSVReader.getUploadHolidayList().size(); i++){
            month = PSVReader.uploadCSVList.get(i).getEventMonth();
            year = PSVReader.uploadCSVList.get(i).getEventYear();


            monthBound = month - 1;
            yearBound = year;
        }
        // Load Public Holidays
        CSVReader.loadData();
        for(int i = 0; i < CSVReader.getUploadEventList().size(); i++){
            month = CSVReader.getUploadEventList().get(i).getEventMonth();
            year = CSVReader.getUploadEventList().get(i).getEventYear();


            monthBound = month;
            yearBound = year;
        }
        // Load Added Events
		holidaysAdded.loadData();
        for(int i = 0; i < holidaysAdded.getAddEventList().size(); i++){
            month = holidaysAdded.getAddEventList().get(i).getEventMonth();
            year = holidaysAdded.getAddEventList().get(i).getEventYear();


            monthBound = month;
            yearBound = year;
        }

        yearBound = LocalDateTime.now().getYear();
        monthBound = LocalDateTime.now().getMonthValue() - 1;
		System.out.println("yeytae " + monthBound);
		/****/
		refreshCalendar (monthBound, yearBound); //Refresh calendar
		refreshCalendar (monthBound, yearBound); //Refresh calendar
	}

	class btnPrev_Action implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (monthToday == 0) {
				monthToday = 11;
				yearToday -= 1;
			}
			else {
				monthToday -= 1;
			}
			refreshCalendar(monthToday, yearToday);
		}
	}

	class btnNext_Action implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (monthToday == 11) {
				monthToday = 0;
				yearToday += 1;
			}
			else {
				monthToday += 1;
			}
			refreshCalendar(monthToday, yearToday);
		}
	}

	class cmbYear_Action implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (cmbYear.getSelectedItem() != null) {
				String b = cmbYear.getSelectedItem().toString();
				yearToday = Integer.parseInt(b);
				refreshCalendar(monthToday, yearToday);
			}
		}
	}

    public PSVReader getHolidaysSchool() {
        return PSVReader;
    }

    public void setHolidaysSchool(PSVReader PSVReader) {
        this.PSVReader = PSVReader;
    }

    public CSVReader getHolidaysPublic() {
        return CSVReader;
    }

    public void setHolidaysPublic(CSVReader CSVReader) {
        this.CSVReader = CSVReader;
    }

    public HolidaysAdded getHolidaysAdded() {
        return holidaysAdded;
    }

    public void setHolidaysAdded(HolidaysAdded holidaysAdded) {
        this.holidaysAdded = holidaysAdded;
    }

    public JTable getCalendarTable() {
        return calendarTable;
    }

    public void setCalendarTable(JTable calendarTable) {
        this.calendarTable = calendarTable;
    }

    public int getYearBound() {
        return yearBound;
    }

    public void setYearBound(int yearBound) {
        this.yearBound = yearBound;
    }

    public int getMonthBound() {
        return monthBound;
    }

    public void setMonthBound(int monthBound) {
        this.monthBound = monthBound;
    }

    public int getDayBound() {
        return dayBound;
    }

    public void setDayBound(int dayBound) {
        this.dayBound = dayBound;
    }

    public int getYearToday() {
        return yearToday;
    }

    public void setYearToday(int yearToday) {
        this.yearToday = yearToday;
    }

    public int getMonthToday() {
        return monthToday;
    }

    public void setMonthToday(int monthToday) {
        this.monthToday = monthToday;
    }
    
    public void attachEventWindow(CalendarFrame e1) {
    	this.e1 = e1;
    }
}
