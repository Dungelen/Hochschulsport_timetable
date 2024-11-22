package hochschulsport_timetable.Application.Services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WebScraperService {

    public void fetchTextFromWebsite(String url) {
        try {
            // Fetch the HTML from the given URL
            Document document = Jsoup.connect(url).get();
            System.out.println(document.html());
            Elements paragraphs = document.select("day");
            System.out.println("fetching text from website: " + url);
            // For demonstration, let's say you want to extract all paragraphs
            // Elements paragraphs = document.select("coursedates");

            // Print out the text from all paragraphs
            System.out.println("Text extracted from website: ");
            System.out.println(paragraphs.text());

            // You can also extract text from different HTML elements using CSS-like selectors
            // Elements headings = document.select("h1, h2, h3"); // for headings

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}