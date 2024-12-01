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
import com.vaadin.flow.dom.Style.BoxSizing;
import com.vaadin.flow.dom.Style.Display;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import hochschulsport_timetable.Application.Modules.TimetableEvent;
import hochschulsport_timetable.Application.Modules.Week;
import lombok.Getter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Time_table")
@Route("time_table")
@Uses(Icon.class)
@Getter
public class Time_tableView extends Composite<VerticalLayout> {

    private Integer startHour = 7;
    private Integer endHour = 23;

    public Time_tableView() {

        

        Div timetable = new Div();
        timetable.addClassName("timetable");
        timetable.setWidth("90%");
        
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
        Div contentBox2 = createContentBox2();
        //timeTableColumnsLayout.add(contentBox2);
        timeTableColumnsLayout.add(contentBox);      


        List<Div> columns = createTimeTableColumns();

        
        for (Div column : columns) {
            String title = column.getTitle().orElse("null");
            for (TimetableEvent timetableEvent : events) {
                
                if (title.equals(timetableEvent.getDay())) {
                    Div eventComponent = createEventComponent(timetableEvent);
                    addEventToContentColumn(column, eventComponent);
                }
            contentBox.add(column);
        }
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

    private void addEventToContentColumn(Div column, Div eventComponent) {

        column.add(eventComponent);
    }

    private Div createContentBox(){
        Div contentBox = new Div();
        contentBox.addClassName("content-box");
        //contentBox.getStyle().set("display", "flex");
        contentBox.getStyle().set("flex-direction", "row");
        contentBox.getStyle().set("flex-wrap", "nowrap");
        contentBox.getStyle().setDisplay(Display.FLEX);
        contentBox.getStyle().setBoxSizing(BoxSizing.BORDER_BOX);
        contentBox.getStyle().setMargin("0");
        contentBox.getStyle().setBorder("1px solid #ccc");
        contentBox.getStyle().set("width", "100%");
        contentBox.getStyle().set("flex-grow", "1");
        contentBox.getStyle().set("flex-shrink", "0 ");

        return contentBox;
    }

    private Div createContentColumn(String day, Integer daynumber) {
        Div column = new Div();
        column.addClassName("content-column");
        column.setTitle(day);
        column.getStyle().set("flex", "1");
        column.getStyle().set("border-right", "1px solid #ccc");
        column.getStyle().setPadding("1%");
        column.getStyle().setWidth("100%");
        return column;
    }

    private List<Div> createTimeTableColumns(){
        Week week = new Week();
        List<Div> columns = new ArrayList<>();
        Integer i = 1;
        for (String day : week.DAYS) {
            Div column = createContentColumn(day, i);
            i++;
            columns.add(column);
        }
        return columns;
    }

    private Div createContentBox2() {
        Div contentBox = new Div();
        contentBox.addClassName("content-box");
        
        // Add 7 columns to the content box
        for (String day : new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}) {
            Div column = new Div();
            column.addClassName("content-column");
            column.setText(day); // Add text for debugging
            contentBox.add(column);
        }
        
        return contentBox;
    }

    private Double getTotalMinutesDay(){
        Double numberOfTimeSlots = (endHour - startHour + 1)*1.0;
        return numberOfTimeSlots * 60.0;
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
        long startMinutes = start.getHour() * 60 + start.getMinute() - startHour * 60;
        long endMinutes = end.getHour() * 60 + end.getMinute() - startHour * 60;
        long durationMinutes = endMinutes - startMinutes;

        // Convert to CSS percentages
        double startPercent = (startMinutes / getTotalMinutesDay()) * 100; // 1440 = total minutes in a day
        double durationPercent = (durationMinutes / getTotalMinutesDay()) * 100;

        // Apply positioning and size
        eventDiv.getStyle().set("position", "relative");
        eventDiv.getStyle().set("top", startPercent + "%");
        eventDiv.getStyle().set("height", durationPercent + "%");
        eventDiv.getStyle().set("width", "100%");
        eventDiv.getStyle().set("background-color", "#dff0d8");
        eventDiv.getStyle().set("border", "1px solid #ccc");
        eventDiv.getStyle().set("border-radius", "4px");
        eventDiv.getStyle().set("text-align", "center");

        return eventDiv;
    }

    private List<String> generateTimeSlots() {
        List<String> times = new ArrayList<>();
        for (int i = startHour; i <= endHour; i++) { // Generate slots from 07:00 to 22:00
            times.add(String.format("%02d:00", i));
        }
        return times;
    }

    private List<TimetableEvent> generateSampleEvents() {
        List<TimetableEvent> events = new ArrayList<>();
        events.add(new TimetableEvent("Morning Yoga", Week.FRIDAY, "07:00", "08:30"));
        events.add(new TimetableEvent("Badminton", Week.MONDAY, "10:15", "11:45"));
        events.add(new TimetableEvent("Fussball", Week.WEDNESDAY, "12:00", "13:30"));
        
        events.add(new TimetableEvent("Fussball", Week.WEDNESDAY, "08:00", "09:30"));
        return events;
    }

}