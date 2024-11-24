package hochschulsport_timetable.Application.views.helloworld;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import hochschulsport_timetable.Application.views.about.AboutView;
import hochschulsport_timetable.Application.views.course.CourseView;
import hochschulsport_timetable.Application.views.custom_timetable.Custom_timetableView;
import hochschulsport_timetable.Application.views.filter_by_category.Filter_by_categoryView;
import hochschulsport_timetable.Application.views.time_table.Time_tableView;

@PageTitle("Hello World")
@Route("")
public class HelloWorldView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public HelloWorldView() {
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        VerticalLayout verticalLayout = new VerticalLayout();
        H1 h1 = new H1();
        h1.setText("Hello World");
        verticalLayout.add(h1);
        verticalLayout.add(name);
        verticalLayout.add(sayHello);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        RouterLink customTimeTableRouterLink = new RouterLink("Custom Time Table", Custom_timetableView.class);
        RouterLink filterByCategoryRouterLink = new RouterLink("Filter by category", Filter_by_categoryView.class);
        RouterLink aboutRouterLink = new RouterLink("About", AboutView.class);
        RouterLink courseRouterLink = new RouterLink("Course", CourseView.class);
        RouterLink timeTableRouterLink = new RouterLink("Time Table", Time_tableView.class);
        horizontalLayout.add(timeTableRouterLink);
        horizontalLayout.add(filterByCategoryRouterLink);
        horizontalLayout.add(customTimeTableRouterLink);
        horizontalLayout.add(courseRouterLink);
        horizontalLayout.add(aboutRouterLink);
        verticalLayout.add(horizontalLayout);
        
        add(verticalLayout);
    }

}
