package Events;

public class StoreFB extends Event {

    public StoreFB() {
    }

    public StoreFB(int eventMonth, int eventDay, int eventYear, String eventName, String eventColor) {
        setEventMonth(eventMonth);
        setEventDay(eventDay);
        setEventYear(eventYear);
        setEventName(eventName);
        setEventColor(eventColor);
    }
}
