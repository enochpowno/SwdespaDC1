package Main;

import facebook.FBView;

import java.util.ArrayList;

public class FBObserver extends Observer {
    private FBView fbView;
    public FBObserver(Subject notifier) {
        this.notifier = notifier;
        notifier.attach(this);
        fbView = new FBView();
    }

    @Override
    public void update() {
        ArrayList<String> a = notifier.getEventToday();
        String[] g = notifier.getDateToday();

        String[] b =  a.toArray(new String[a.size()]);

        for (int i = 0; i < a.size(); i++) {
            fbView.showNewEvent(a.get(i),Integer.parseInt(g[1]), Integer.parseInt(g[2]), Integer.parseInt(g[0]),
                    notifier.getEventColor(Integer.parseInt(g[0]),Integer.parseInt(g[1]),Integer.parseInt(g[2]), b[i]));
        }

//        System.out.println(a.size());
    }

}
