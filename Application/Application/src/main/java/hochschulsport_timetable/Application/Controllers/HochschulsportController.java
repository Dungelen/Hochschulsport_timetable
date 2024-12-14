package hochschulsport_timetable.Application.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hochschulsport_timetable.Application.Modules.API_Course;
import hochschulsport_timetable.Application.Modules.Course;
import hochschulsport_timetable.Application.Modules.Courses;
import hochschulsport_timetable.Application.Modules.Hochschulsport_API;
import hochschulsport_timetable.Application.Services.HochschulsportAPIFetcher;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HochschulsportController {

    @Autowired
    private HochschulsportAPIFetcher apiFetcher;

    private String badminton_api = "https://api.hochschulsport-koeln.de/json/v2/course/84/dates";
    private String indoor_fussball_soccer_center_kostenlos_api = "https://api.hochschulsport-koeln.de/json/v2/course/191/dates";

    @GetMapping("/fetch-and-print-schedule")
    public String fetchAndPrintSchedule() {
        apiFetcher.fetchAndPrintData(badminton_api);
        apiFetcher.fetchAndPrintData(indoor_fussball_soccer_center_kostenlos_api);
        return "Schedule data fetched and printed. Check logs for output.";
    }

    @GetMapping("fetch-schedule")
    public String fetchSchedule() {
        Hochschulsport_API api = new Hochschulsport_API();
        List<API_Course> apiCourses = api.getApiCourses();
        Courses courses = new Courses();
        for (API_Course course : apiCourses) {
            Course newCourse = apiFetcher.fetchAndParseData(course.getUrl());
            newCourse.setName(course.getName());
            courses.addCourse(newCourse);
        }
        System.out.println(courses.info());

        return "Schedule data fetched.";
    }

    @GetMapping("create-API-mapping")
    public String createAPIMapping() {
        
        return "API mapping created.";
    }
    
}
