package Main;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class TableModifier {
    public void writeToCell(JTable calendarProgram, ArrayList<String> a, int i, int row, int column){
        calendarProgram.getModel().setValueAt(i, row, column);
        for (int j = 0; j < a.size(); j++) {
            if (calendarProgram.getModel().getValueAt(row, column).toString().length() <= 2)
                calendarProgram.getModel().setValueAt(calendarProgram.getModel().getValueAt(row, column) +
                        " -" + a.get(j), row, column);
            else
                calendarProgram.getModel().setValueAt(calendarProgram.getModel().getValueAt(row, column) +
                        "," + a.get(j), row, column);
        }
    }

    public void TableRenderer(JTable calendarTable, Subject notifier, int year, int month){
        calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column){
                super.getTableCellRendererComponent(table, value, selected, focused, row, column);
                String[] sEvent;
                int nDay;
                String cColor;
                String text = "";
                if(this.getText().contains("-")) {
                    sEvent = getText().split(" -");
                    nDay = Integer.parseInt(sEvent[0]);
                    sEvent = sEvent[1].split(",");
                    for (int i = 0; i < sEvent.length; i++) {
                        cColor = notifier.getColor(year, month + 1, nDay, sEvent[i]);
//                        System.out.println(cColor);
                        if(i == 0 )
                            text += "<html>"+nDay + " -" +"<font color=" + cColor+">" + sEvent[i];
                        else text += "<br>"+"<font color ="+cColor +">"+sEvent[i]+"</br>";
                        if(i + 1 >= sEvent.length)
                            text += "</html>";
                    }
                    setValue(text);

                }

                if (column == 0 || column == 6)
                    setBackground(new Color(220,220,255));
                else
                    setBackground(Color.WHITE);
                setBorder(null);
                setForeground(Color.black);
//                if(cColor !=null)
//                    setForeground(cColor);
                return this;
            }
        });
    }

    public void insertTextToPane(String text, JTextPane textPane){
        try {
            textPane.getStyledDocument().insertString(0,text,null);
        }catch (Exception e){

        }
    }
}
