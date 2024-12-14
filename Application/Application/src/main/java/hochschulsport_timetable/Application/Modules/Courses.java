package hochschulsport_timetable.Application.Modules;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Courses {
    public List<Course> courses =  new ArrayList<Course>();
    
    public void addCourse(Course course) {
        courses.add(course);
    }

    public String info(){
        String info = "";
        for (Course course : courses) {
            info += course.info();
        }
        return info;
    }
}
