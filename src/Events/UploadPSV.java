package Events;

public class UploadPSV extends Event{

    public UploadPSV() {
    }

    public UploadPSV(int eventMonth, int eventDay, int eventYear, String eventName, String eventColor) {
        setEventMonth(eventMonth);
        setEventDay(eventDay);
        setEventYear(eventYear);
        setEventName(eventName);
        setEventColor(eventColor);
    }
}
