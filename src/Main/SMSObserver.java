package Main;

import sms.SMS;
import sms.SMSView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;

public class SMSObserver extends Observer {
    private SMS sms;
    private SMSView smsView;
    private JTextPane textPane;
    public SMSObserver(Subject notifier) {
        this.notifier = notifier;
        notifier.attach(this);
        smsView = new SMSView();
        sms = null;
        //textPane = getJTextPane();
//        textPane.setContentType("text/html");
    }

    @Override
    public void update(){
        ArrayList<String> a = notifier.getEventToday();
        String[] e = notifier.getDateToday();
//        for (int i = 0; i < a.size(); i++) {
//            events += a.get(i);
//            if(i + 1 < a.size())
//                events += "\n";
//        }

        Calendar date = Calendar.getInstance();

//        String test =  e[0] + " "+ e[1] +" "+ e[2];
        date.set(Integer.parseInt(e[0]), Integer.parseInt(e[1]) - 1, Integer.parseInt(e[2]));
        String[] b =  a.toArray(new String[a.size()]);

        for (int i = 0; i < a.size(); i++) {
            if (sms != null) {//TODO:COLOR OVERRIDE
                sms.setEventName(a.get(i));
                sms.setDate(date);
                sms.setColor(notifier.getEventColor(Integer.parseInt(e[0]), Integer.parseInt(e[1]), Integer.parseInt(e[2]), b[i]));
            } else
                sms = new SMS(a.get(i), date, notifier.getEventColor(Integer.parseInt(e[0]), Integer.parseInt(e[1]), Integer.parseInt(e[2]), b[i]));
            //smsView.sendSMS(sms);

            smsView.sendSMS(sms);
        }
//            textPane.setText("<html><font color=" + "orange"+">" + sms.getEventName() +"<br>"+ test  + "</br>" +"</html>");
//            for (int i = 0; i < a.size(); i++) {
//                if(i == 0){
//                    String test = "<html><font color=" + b+">" ;
//                }
//                else if(i + 1 < a.size()){
//
//                }
//            }
            //textPane.("<html><font color=" + "black"+">" + "test" + "</html>");
    }

//    public JTextPane getJTextPane(){
//        JTextPane textPane = null;
//        Boolean isNotFound = true;
//        for (int i = 0; i < smsView.getContentPane().getComponents().length && isNotFound; i++) {
//            if(smsView.getContentPane().getComponents()[i] instanceof JScrollPane){
//                for (int j = 0; j < ((JScrollPane) smsView.getContentPane().getComponents()[i]).getComponents().length && isNotFound; j++) {
//                    if(((JScrollPane) smsView.getContentPane().getComponents()[i]).getComponents()[j] instanceof JViewport){
//                        for (int k = 0; k < ((JViewport)((JScrollPane) smsView.getContentPane().getComponents()[i]).getComponents()[j]).getComponents().length && isNotFound; k++) {
//                            if(((JViewport)((JScrollPane) smsView.getContentPane().getComponents()[i]).getComponents()[j]).getComponents()[k] instanceof JTextPane){
//                                textPane = ((JTextPane)((JViewport)((JScrollPane) smsView.getContentPane().getComponents()[i]).getComponents()[j]).getComponents()[k]);
//                                isNotFound = false;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return textPane;
//    }
}
