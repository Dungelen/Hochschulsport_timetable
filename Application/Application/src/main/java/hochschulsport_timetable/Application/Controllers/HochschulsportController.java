package hochschulsport_timetable.Application.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hochschulsport_timetable.Application.Modules.Course;
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
        Course badminon_course = apiFetcher.fetchAndParseData(badminton_api);
        Course indoor_fussball_course = apiFetcher.fetchAndParseData(indoor_fussball_soccer_center_kostenlos_api);
        badminon_course.setName("badminton");
        System.out.println(badminon_course.info());

        indoor_fussball_course.setName("indoor_fussball_soccer_center_kostenlos");
        System.out.println(indoor_fussball_course.info());


        return "Schedule data fetched.";
    }
    
}
