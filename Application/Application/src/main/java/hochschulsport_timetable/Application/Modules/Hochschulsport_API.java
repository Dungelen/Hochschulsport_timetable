package hochschulsport_timetable.Application.Modules;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Hochschulsport_API {

    List<API_Course> apiCourses = new ArrayList<API_Course>();

    public API_Course createAPICourse(String courseName, String courseUrl) {
        API_Course apiCourse = new API_Course();
        apiCourse.Name = courseName;
        apiCourse.Url = courseUrl;
        return apiCourse;
    }

    public void addAPICourse(API_Course apiCourse) {
        this.apiCourses.add(apiCourse);
    }

    public void removeAPICourse(API_Course apiCourse) {
        this.apiCourses.remove(apiCourse);
    }

    private void fillAPICourses(){
        addAPICourse(createAPICourse("Badminton", "https://api.hochschulsport-koeln.de/json/v2/course/84/dates"));
        addAPICourse(createAPICourse("Indoor Fussball (Soccer Center) - kostenlos!", "https://api.hochschulsport-koeln.de/json/v2/course/191/dates"));
     }

    public Hochschulsport_API() {
        fillAPICourses();
    }

    public API_Course getAPIByName(String name) {
        for (API_Course apiCourse : this.apiCourses) {
            if (apiCourse.Name.equals(name)) {
                return apiCourse;
            }
        }
        return null;
    }

}
