package hochschulsport_timetable.Application.Modules;

public class TimetableEvent {
    private String name;       // Event name
    private String day;        // Day of the week
    private String startTime;  // Start time (e.g., "07:00")
    private String endTime;    // End time (e.g., "08:30")

    public TimetableEvent(String name, String day, String startTime, String endTime) {
        this.name = name;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
