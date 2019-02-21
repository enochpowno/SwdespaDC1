package Main;

import javax.swing.JFrame;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class EventWindow extends JFrame {
    private JTextField eventNameField;
    private JLabel lblEventName;
    private JLabel lblDate;
    private JLabel lblColor;
    private JComboBox colorComboBox;
    private JButton btnAdd;
    private Subject notifier;
    private JTextField dateField;
    private JCheckBox holidayCheckBox;

    public EventWindow(int m, int y, int d, CalendarProgram prog, Subject notifier) {
        setTitle("Add Event");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setSize(324, 189);
        this.setResizable(false);
        this.notifier = notifier;
        eventNameField = new JTextField();
        eventNameField.setBounds(110, 12, 192, 22);
        getContentPane().add(eventNameField);
        eventNameField.setColumns(10);

        lblEventName = new JLabel("Event Name");
        lblEventName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEventName.setBounds(12, 13, 86, 19);
        getContentPane().add(lblEventName);

        lblColor = new JLabel("Color");
        lblColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblColor.setBounds(149, 50, 44, 16);
        getContentPane().add(lblColor);

        colorComboBox = new JComboBox();
        colorComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        colorComboBox.setModel(new DefaultComboBoxModel(new String[]{"Black", "Blue", "Green", "Red"}));
        colorComboBox.setBounds(205, 47, 97, 22);
        getContentPane().add(colorComboBox);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(113, 116, 97, 25);
        getContentPane().add(btnAdd);

        dateField = new JTextField();
        dateField.setBounds(57, 47, 74, 22);
        getContentPane().add(dateField);
        dateField.setColumns(10);
        dateField.setEditable(false);
        dateField.setText((m + 1) + "/" + d + "/" + y);

        lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDate.setBounds(12, 49, 44, 19);
        getContentPane().add(lblDate);

        holidayCheckBox = new JCheckBox("Holiday");
        holidayCheckBox.setBounds(12, 88, 86, 25);
        getContentPane().add(holidayCheckBox);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    if (holidayCheckBox.isSelected()) {
                        Event ev = new Event(m, d, y, eventNameField.getText(), colorComboBox.getSelectedItem().toString(), "csv");
                        notifier.getAnImporter("csv").writeData(ev);
                        if (ev.getYear() == Integer.parseInt(notifier.getDateToday()[0]) && ev.getMonth() == Integer.parseInt(notifier.getDateToday()[1])
                                && Integer.parseInt(notifier.getDateToday()[2]) == ev.getDay())
                            notifier.setAlertToday(false);
                    } else {
                        Event ev = new Event(m, d, y, eventNameField.getText(), colorComboBox.getSelectedItem().toString(), "psv");
                        notifier.getAnImporter("psv").writeData(ev);
                        if (ev.getYear() == Integer.parseInt(notifier.getDateToday()[0]) && ev.getMonth() == Integer.parseInt(notifier.getDateToday()[1])
                                && Integer.parseInt(notifier.getDateToday()[2]) == ev.getDay())
                            notifier.setAlertToday(false);
                    }
                    prog.refreshCalendar(m, y);
                    dispose();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Event name is empty!");
                }
            }
        });
        this.setVisible(true);
    }
}