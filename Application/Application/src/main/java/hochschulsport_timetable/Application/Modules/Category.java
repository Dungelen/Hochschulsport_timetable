package hochschulsport_timetable.Application.Modules;

import java.util.List;

public class Category {
    List<Course> courses;
    String name;
    String id;
    String category_url;

    public void addCourse(Course course) {
        courses.add(course);
    }
}
