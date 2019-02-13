package Events;

public class UploadCSV extends Event {

    public UploadCSV() {
    }

    public UploadCSV(int eventMonth, int eventDay, int eventYear, String eventName, String eventColor) {
        setEventMonth(eventMonth);
        setEventDay(eventDay);
        setEventYear(eventYear);
        setEventName(eventName);
        setEventColor(eventColor);
    }
}
