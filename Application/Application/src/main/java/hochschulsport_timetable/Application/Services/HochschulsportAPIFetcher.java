package hochschulsport_timetable.Application.Services;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import hochschulsport_timetable.Application.Modules.Course;
import hochschulsport_timetable.Application.Modules.DayInfo;

@Service
public class HochschulsportAPIFetcher {

    public void fetchAndPrintData(String apiUrl) {
        
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Set request headers
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:131.0) Gecko/20100101 Firefox/131.0");
            connection.setRequestProperty("Accept", "application/json, text/plain, */*");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            connection.setRequestProperty("Origin", "https://www.hochschulsport-koeln.de");
            connection.setRequestProperty("Referer", "https://www.hochschulsport-koeln.de/");
            connection.setRequestProperty("DNT", "1");

            // Check response code
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                parseJSONResponse(response.toString());
            } else {
                System.err.println("Error: Received HTTP response code " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Course fetchAndParseData(String apiUrl) {
        
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Set request headers
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:131.0) Gecko/20100101 Firefox/131.0");
            connection.setRequestProperty("Accept", "application/json, text/plain, */*");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            connection.setRequestProperty("Origin", "https://www.hochschulsport-koeln.de");
            connection.setRequestProperty("Referer", "https://www.hochschulsport-koeln.de/");
            connection.setRequestProperty("DNT", "1");

            Course course = new Course();

            // Check response code
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                course = parseJsonResponseCourse(response.toString());
                
            } else {
                System.err.println("Error: Received HTTP response code " + responseCode);
                
            }
            connection.disconnect();
            return course;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void parseJSONResponse(String jsonResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            for (JsonNode course : rootNode) {
                System.out.println("Day: " + course.get("day").asText());
                System.out.println("Time: " + course.get("start").asText() + " - " + course.get("end").asText());
                System.out.println("Place: " + course.get("place").asText());
                System.out.println("Registration: " + course.get("registration").asText());
                System.out.println("----------------------");
            }
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
    }

    private Course parseJsonResponseCourse(String jsonResponse) {
        Course course = new Course();
        course.setDays(new ArrayList<>());
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            for (JsonNode courseNode : rootNode) {
                DayInfo dayinfo = new DayInfo();
                dayinfo.setDay(courseNode.get("day").asText());
                dayinfo.setStart(courseNode.get("start").asText());
                dayinfo.setEnd(courseNode.get("end").asText());
                dayinfo.setTime(courseNode.get("start").asText() + " - " + courseNode.get("end").asText());
                dayinfo.setPlace(courseNode.get("place").asText());
                //dayinfo.setPlace_url(courseNode.get("place_url").asText());
                dayinfo.setRegistration_url(courseNode.get("registration").asText());
                course.addDayInfo(dayinfo);
            }
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
        return course;
    }
}
