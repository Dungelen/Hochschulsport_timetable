package hochschulsport_timetable.Application.views.time_table;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import hochschulsport_timetable.Application.Modules.TimetableEvent;
import hochschulsport_timetable.Application.Modules.Week;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Time_table")
@Route("time_table")
@Uses(Icon.class)
public class Time_tableView extends Composite<VerticalLayout> {

    public Time_tableView() {

        

        Div timetable = new Div();
        timetable.addClassName("timetable");
        
        //timetable.addClassName("timetable-container");
        getContent().add(timetable);

        H1 h1 = new H1();
        VerticalLayout layoutColumn2 = new VerticalLayout();

        
        List<TimetableEvent> events = generateSampleEvents();

        HorizontalLayout timeTableColumnsLayout = new HorizontalLayout();
        timetable.add(timeTableColumnsLayout);

        // =================Time slots=================
        List<String> timeSlots = generateTimeSlots();
        Div timeSlotColumn = createTimeSlotColumn(timeSlots);

        timeTableColumnsLayout.add(timeSlotColumn);

        
        // =================content columns=================
        Div contentBox = createContentBox();
                
        timeTableColumnsLayout.add(contentBox);

        List<Div> columns = createTimeTableColumns();
        for (Div column : columns) {
            contentBox.add(column);
        }


        for (TimetableEvent timetableEvent : events) {
            timetable.add(createEventComponent(timetableEvent));
        }

        TabSheet tabSheet = new TabSheet();
        Grid basicGrid = new Grid();
        HorizontalLayout layoutRow = new HorizontalLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h1.setText("Heading");
        h1.setWidth("max-content");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        tabSheet.setWidth("100%");
        setTabSheetSampleData(tabSheet);
        basicGrid.setWidth("100%");
        basicGrid.getStyle().set("flex-grow", "0");
        //setGridSampleData(basicGrid);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        getContent().add(h1);
        getContent().add(layoutColumn2);
        layoutColumn2.add(tabSheet);
        layoutColumn2.add(basicGrid);
        getContent().add(layoutRow);
    }

    private void setTabSheetSampleData(TabSheet tabSheet) {
        tabSheet.add("Dashboard", new Div(new Text("This is the Dashboard tab content")));
        tabSheet.add("Payment", new Div(new Text("This is the Payment tab content")));
        tabSheet.add("Shipping", new Div(new Text("This is the Shipping tab content")));
    }

    private Div createTimeSlotColumn(List<String> timeSlots){
        Div column = new Div();
        column.addClassName("time-slot-column");
        column.getStyle().set("border-right", "1px solid #ccc");
        for (String timeSlot : timeSlots) {
            column.add(createTimeSlotRow(timeSlot));
        }
        return column;
    }
    
    private Div createTimeSlotRow(String time) {
        Div row = new Div();
        row.addClassName("time-slot");
        row.setText(time);
        row.getStyle().set("position", "relative");
        row.getStyle().set("border-bottom", "1px solid #ccc");
        row.getStyle().set("height", "60px"); // Height for each time slot (e.g., 1 hour)
        return row;
    }

    private void addEventToTimetable(Div timetable, TimetableEvent event) {
        Div eventDiv = new Div();
        eventDiv.addClassName("event");
        eventDiv.setText(event.getName());
        timetable.add(eventDiv);
    }

    private Div createContentBox(){
        Div contentBox = new Div();
        contentBox.addClassName("content-box");
        contentBox.getStyle().set("border", "1px solid #ccc");
        contentBox.getStyle().set("background-color", "#f5f5f5");
        contentBox.getStyle().set("padding", "10px");
        return contentBox;
    }

    private Div createContentColumn(String day) {
        Div column = new Div();
        column.addClassName("content-column");
        column.getStyle().set("text", day);
        column.setTitle(day);
        column.setText(day);
        column.getStyle().set("position", "relative");
        column.getStyle().set("border-right", "1px solid #ccc");
        return column;
    }

    private List<Div> createTimeTableColumns(){
        Week week = new Week();
        List<Div> columns = new ArrayList<>();
        for (String day : week.DAYS) {
            Div column = createContentColumn(day);
            columns.add(column);
        }
        return columns;
    }

    private Div createEventComponent(TimetableEvent event) {
        Div eventDiv = new Div();
        eventDiv.addClassName("event");
        eventDiv.setText(event.getName());

        // Calculate position and height
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start = LocalTime.parse(event.getStartTime(), formatter);
        LocalTime end = LocalTime.parse(event.getEndTime(), formatter);

        // Time calculations
        long startMinutes = start.getHour() * 60 + start.getMinute();
        long endMinutes = end.getHour() * 60 + end.getMinute();
        long durationMinutes = endMinutes - startMinutes;

        // Convert to CSS percentages
        double startPercent = (startMinutes / 1440.0) * 100; // 1440 = total minutes in a day
        double durationPercent = (durationMinutes / 1440.0) * 100;

        // Apply positioning and size
        eventDiv.getStyle().set("position", "absolute");
        eventDiv.getStyle().set("top", startPercent + "%");
        eventDiv.getStyle().set("height", durationPercent + "%");
        eventDiv.getStyle().set("left", "20%"); // Offset for the day column
        eventDiv.getStyle().set("width", "10%");
        eventDiv.getStyle().set("background-color", "#dff0d8");
        eventDiv.getStyle().set("border", "1px solid #ccc");
        eventDiv.getStyle().set("border-radius", "4px");
        eventDiv.getStyle().set("text-align", "center");

        return eventDiv;
    }

    private List<String> generateTimeSlots() {
        List<String> times = new ArrayList<>();
        for (int i = 7; i <= 22; i++) { // Generate slots from 07:00 to 22:00
            times.add(String.format("%02d:00", i));
        }
        return times;
    }

    private List<TimetableEvent> generateSampleEvents() {
        List<TimetableEvent> events = new ArrayList<>();
        events.add(new TimetableEvent("Morning Yoga", "Monday", "07:00", "08:30"));
        events.add(new TimetableEvent("Badminton", "Tuesday", "10:15", "11:45"));
        events.add(new TimetableEvent("Fussball", "Wendsday", "12:00", "13:30"));
        return events;
    }

}