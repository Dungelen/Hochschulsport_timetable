package hochschulsport_timetable.Application.Modules;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DayInfo {
    
    String day;
    String start;
    String end;
    String time;
    String place;
    String place_url;
    String registration_url;

    public String info() {
        return "Day: " + this.day + "\n" + "Start: " + this.start + "\n" + "End: " + this.end + "\n" + "Time: " + this.time + "\n" + "Place: " + this.place + "\n" + "Place URL: " + this.place_url + "\n" + "Registration URL: " + this.registration_url;
    }
}
