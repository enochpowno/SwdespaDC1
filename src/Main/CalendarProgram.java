/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
/**
 *
 * @author Arturo III
 */
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// this class handles everything, when changes need to be made, the programmer will add a lot in one class.
// imported * which may cause naming conflicts
// bad design for gui since the user needs to double click to add events which disappear if you move months/years
// should we add a list of events for the calendar program so that events will be saved? 
// add button for adding events
public class CalendarProgram{

    /**** Day Components ****/
	public int yearBound, monthBound, dayBound, yearToday, monthToday;

    /**** Swing Components ****/
    public JLabel monthLabel, yearLabel;
	public JButton btnPrev, btnNext;
    public JComboBox cmbYear;
	public JFrame frmMain;
	public Container pane;
	public JScrollPane scrollCalendarTable;
	public JPanel calendarPanel;
    /**** Calendar Table Components ***/
	public JTable calendarTable;
    public DefaultTableModel modelCalendarTable;
    private JButton btnAddEvent;
    private Subject notifier;
    private boolean isEditableCell;
    private JTextPane textPane;
	public void refreshCalendar(int month, int year)
    {
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
		for (i = 1; i <= nod; i++)
		{
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			new TableModifier().writeToCell(calendarTable, notifier.findEvent(year, month + 1, i), i, row, column);
		}
		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer());
		new TableModifier().TableRenderer(calendarTable, notifier, year, month);
		//textPane.setText(" Click Add Button to add an event\n");
    }

	public CalendarProgram()
    {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
		catch (Exception e) {
			e.printStackTrace();
		}


		isEditableCell = true;
		notifier = new Subject();
		frmMain = new JFrame ("Calendar Ni ENZO");
        frmMain.setSize(660, 750);
        frmMain.setLocationRelativeTo(null);
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		monthLabel = new JLabel ("January");
		yearLabel = new JLabel ("Change year:");
		cmbYear = new JComboBox();
		btnPrev = new JButton ("<<");
		btnNext = new JButton (">>");
		modelCalendarTable = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
               if (isEditableCell)
            	   return true;
               return false;
            }
        };


        
        
		calendarTable = new JTable(modelCalendarTable);
        calendarTable.addMouseListener(new MouseAdapter()   
        {  
            public void mouseClicked(MouseEvent evt)
            {  
                int col = calendarTable.getSelectedColumn();  
                int row = calendarTable.getSelectedRow();
            }
        });


		scrollCalendarTable = new JScrollPane(calendarTable);
		btnAddEvent = new JButton("Add Event");
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
		//calendarPanel.add(textPane);
		//calendarPanel.add(messageLabel);
		frmMain.getContentPane().add(btnAddEvent);


        calendarPanel.setBounds(0, 0, 640, 670);
        monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 200, 50);
		yearLabel.setBounds(20, 610, 160, 40);
		cmbYear.setBounds(460, 610, 160, 40);
		btnPrev.setBounds(20, 50, 100, 50);
		btnNext.setBounds(520, 50, 100, 50);
		scrollCalendarTable.setBounds(20, 100, 600, 500);
		btnAddEvent.setBounds(272, 677, 97, 25);

		frmMain.setResizable(false);
		frmMain.setVisible(true);

		GregorianCalendar cal = new GregorianCalendar();
		dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
		monthBound = cal.get(GregorianCalendar.MONTH);
		yearBound = cal.get(GregorianCalendar.YEAR);
		monthToday = monthBound; 
		yearToday = yearBound;
	//	new TableModifier().insertTextToPane(" Click Add Event Button to add an event", textPane);
		btnAddEvent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//textPane.setText(" Please click a day in the calendar app\n");
				//System.out.println(textPane.getText());
				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(new JFrame(), "Please click a day in the calendar app");

				calendarTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						int row = calendarTable.getSelectedRow();
						int col = calendarTable.getSelectedColumn();
						//System.out.println(calendarTable.getValueAt(row, col));

						JFrame eWindow = new EventWindow(monthBound, Integer.parseInt(cmbYear.getSelectedItem().toString()),
														 Integer.parseInt(calendarTable.getValueAt(row, col).toString().split("-")[0].trim()),
								 						 CalendarProgram.this, notifier);
						calendarTable.removeMouseListener(this);
						//textPane.setText(" Enter Event Name and Click Add Button to add the event\n");
					}

				});
			}

		});

		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
		for (int i=0; i<7; i++) {
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
		
		for (int i = yearBound-100; i <= yearBound+100; i++)
        {
			cmbYear.addItem(String.valueOf(i));
		}
		
		refreshCalendar (monthBound, yearBound); //Refresh calendar
	}	

	class btnPrev_Action implements ActionListener
    {
		public void actionPerformed (ActionEvent e)
        {
			if (monthToday == 0)
            {
				monthToday = 11;
				yearToday -= 1;
			}
			else
            {
				monthToday -= 1;
			}
			refreshCalendar(monthToday, yearToday);
		}
	}
	
	class btnNext_Action implements ActionListener
    {
		public void actionPerformed (ActionEvent e)
        {
			if (monthToday == 11)
            {
				monthToday = 0;
				yearToday += 1;
			}
			else
            {
				monthToday += 1;
			}
			refreshCalendar(monthToday, yearToday);
		}
	}
	
	class cmbYear_Action implements ActionListener
    {
		public void actionPerformed (ActionEvent e)
        {
			if (cmbYear.getSelectedItem() != null)
			{
				String b = cmbYear.getSelectedItem().toString();
				yearToday = Integer.parseInt(b);
				refreshCalendar(monthToday, yearToday);
			}
		}
	}
}
