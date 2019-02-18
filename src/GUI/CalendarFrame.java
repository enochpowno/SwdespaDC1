package GUI;

import Data.CSVReader;
import Events.UploadCSV;
import Events.UploadedAddEvent;
import Data.HolidaysAdded;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

public class
CalendarFrame extends JFrame {
	CalendarProgram c1;
	private JPanel contentPane;
	private JPanel colorPane;
	private JTextField namefield, colorfield;
	private JButton Addbtn, ExitBtn;


	private int month;
	private int day;
	private int year;
	private String color;
	private ArrayList<UploadedAddEvent> addEvents;
	private UploadCSV csvWriter;

	public void update() throws IOException {
		System.out.println("Details:");
		System.out.println(getEventName());
		System.out.println(getMonth());
		System.out.println(getDay());
		System.out.println(getYear());
		System.out.println(getColor());
/*
		HolidaysAdded ha = new HolidaysAdded();
		ha.getAddEventList().indexOf();
*/
		UploadedAddEvent addEvent = new UploadedAddEvent();
//		int ctr = 0;
		addEvents.add(new UploadedAddEvent(getMonth(), getDay(), getYear(), namefield.getText(), color));
		addEvent.uploadAddedEvents(addEvents);
//		ctr++;
		c1.refreshCalendar(month, year);
	}

	public JTextField getNamefield() {

		return namefield;
	}

	public void setNamefield(JTextField namefield) {

		this.namefield = namefield;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEventName() {
		return namefield.getText();
	}

	public void setEventName(JTextField namefield) {
		this.namefield = namefield;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public void attachProgram(CalendarProgram c1) {
		this.c1 = c1;
	}

	public CalendarFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
/*
		colorPane = new JPanel();
		colorPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
*/
		namefield = new JTextField();
		namefield.setBounds(124, 78, 241, 20);
		contentPane.add(namefield);
		namefield.setColumns(10);

		colorfield = new JTextField();
		colorfield.setBounds(124, 120, 241, 20);
		contentPane.add(colorfield);
		colorfield.setColumns(10);

		JLabel lblColor = new JLabel("Cell Color: ");
		lblColor.setBounds(44, 120, 89, 14);
		contentPane.add(lblColor);

		JLabel lblEventName = new JLabel("Event Name: ");
		lblEventName.setBounds(44, 81, 89, 14);
		contentPane.add(lblEventName);

		JLabel lblAddEvent = new JLabel("Add Event");
		lblAddEvent.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAddEvent.setBounds(164, 0, 294, 95);
		contentPane.add(lblAddEvent);

		// ADD BUTTON
		JButton Addbtn = new JButton("Add Event");
		Addbtn.setBackground(Color.WHITE);
		Addbtn.setBounds(175, 160, 95, 23);
		//Addbtn.addActionListener(new doActionListener());
		Addbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!namefield.getText().equals("") && !colorfield.getText().equals("")){
					setVisible(false);
					String temp = colorfield.getText();
					setColor(temp);
					try{
						update();
					} catch (IOException a){
						a.printStackTrace();
					}

				}
			}
		});
		contentPane.add(Addbtn);

		// EXIT BUTTON
		JButton Exitbtn = new JButton("Exit");
		Exitbtn.setBackground(Color.WHITE);
		Exitbtn.setBounds(175, 200, 95, 23);
		//Exitbtn.addActionListener(new doActionListener());
		Exitbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(Exitbtn);

/***							INIT 	        								***/
		//ArrayList initialization for storing all events added by the user
		initAddEvents();
/***																			***/

		/*JLabel lblEventColor = new JLabel("Event Color:");
		lblEventColor.setBounds(44, 130, 89, 14);
		contentPane.add(lblEventColor);*/

	}

	private void initAddEvents(){
		HolidaysAdded data = new HolidaysAdded();
		data.loadData();
		addEvents = data.getAddEventList();
	}
/*
	public class doActionListener implements ActionListener{

			public void actionPerformed(ActionEvent action){
				if(action.getSource() == Addbtn){
					namefield.getText();
					colorfield.getText();
					contentPane.setVisible(false);
					try{

						//colorPane.setVisible(true);
						update();

					} catch (IOException e){

					}
				} else if (action.getSource() == ExitBtn);{
					System.exit(0);
				}

			}

	}
*/
}