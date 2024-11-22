package hochschulsport_timetable.Application.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hochschulsport_timetable.Application.Services.WebScraperService;

@RestController
public class WebScraperController {

    @Autowired
    private WebScraperService webScraperService;

    @GetMapping("/scrape")
    public String scrapeWebsite(@RequestParam String url) {
        webScraperService.fetchTextFromWebsite(url);
        return "Text extraction completed. Check the logs for output.";
    }

    @GetMapping("/test2")
    public String test_connectin() {
        System.out.println("Test connection successful.");
        return "Hello";
    }
    
}