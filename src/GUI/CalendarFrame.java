package GUI;

import Events.UploadedAddEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
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
	private JTextField namefield;

	private int month;
	private int day;
	private int year;
	private String color;


	public void update() throws IOException {
		System.out.println("Details:");
		System.out.println(getEventName());
		System.out.println(getMonth());
		System.out.println(getDay());
		System.out.println(getYear());
		System.out.println(getColor());

		ArrayList<UploadedAddEvent> addEvents = new ArrayList<>();
		UploadedAddEvent addEvent = new UploadedAddEvent();
		int ctr = 0;
		addEvents.add(new UploadedAddEvent(month, day, year, namefield.getText(), color));
		addEvent.uploadAddedEvents(addEvents, ctr);
		ctr++;

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

		namefield = new JTextField();
		namefield.setBounds(124, 78, 241, 20);
		contentPane.add(namefield);
		namefield.setColumns(10);

		JLabel lblEventName = new JLabel("Event Name: ");
		lblEventName.setBounds(44, 81, 89, 14);
		contentPane.add(lblEventName);

		// RED BUTTON
		JButton btnRed = new JButton("Red");
		btnRed.setBackground(Color.RED);
		btnRed.setBounds(124, 160, 89, 23);
		btnRed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!namefield.getText().equals("")) {
					setVisible(false);
					setColor("red");
//					Color.RED;
					try {
						update();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Red Button Clicked");
				//c1.refreshCalendar(month, year);
			}
		});
		contentPane.add(btnRed);

		// BLUE BUTTON
		JButton btnBlue = new JButton("Blue");
		btnBlue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!namefield.getText().equals("")) {
					setVisible(false);
					setColor("blue");
					try {
						update();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Blue Button Clicked");
				//	c1.refreshCalendar(month, year);
			}

		});
		btnBlue.setBackground(Color.BLUE);
		btnBlue.setBounds(124, 194, 89, 23);
		contentPane.add(btnBlue);

		// GREEN BUTTON
		JButton btnGreen = new JButton("Green");
		btnGreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!namefield.getText().equals("")) {
					setVisible(false);
					setColor("green");
					try {
						update();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Green Button Clicked");
				System.out.println(getNamefield().getText());

			}
		});
		btnGreen.setBackground(Color.GREEN);
		btnGreen.setForeground(Color.BLACK);
		btnGreen.setBounds(124, 126, 89, 23);
		contentPane.add(btnGreen);

		JLabel lblEventColor = new JLabel("Event Color:");
		lblEventColor.setBounds(44, 130, 89, 14);
		contentPane.add(lblEventColor);

		JLabel lblAddEvent = new JLabel("Add Event");
		lblAddEvent.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAddEvent.setBounds(164, 0, 294, 95);
		contentPane.add(lblAddEvent);
	}
}
