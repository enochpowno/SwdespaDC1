package Events;

public class StoreSMS extends Event{

    public StoreSMS() {
    }

    public StoreSMS(int eventMonth, int eventDay, int eventYear, String eventName, String eventColor) {
        setEventMonth(eventMonth);
        setEventDay(eventDay);
        setEventYear(eventYear);
        setEventName(eventName);
        setEventColor(eventColor);
    }
}
