package hochschulsport_timetable.Application.Modules;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Course {

    List<DayInfo> days;
    String name;
    String description;
    String category;
    String id;
    String api_url;
    String course_url;


    public void addDayInfo(DayInfo dayInfo) {
        this.days.add(dayInfo);
    }

    public String info() {
        return "=======================================================================\n" + "Course: " + this.name + "\n" + "Description: " + this.description + "\n" + "Category: " + this.category + "\n" + "ID: " + this.id + "\n" + "API URL: " + this.api_url + "\n" + "Course URL: " + this.course_url + "\n" + "Days: \n" + this.daysInfo() + "=======================================================================\n";
    }

    public String daysInfo() {
        String info = "";
        for (DayInfo day : this.days) {
            info+= "=======================================================================\n";
            info += day.info() + "\n";
            info+= "=======================================================================\n";
        }
        return info;
    }
}

