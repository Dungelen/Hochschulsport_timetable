package hochschulsport_timetable.Application.views.course;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Course")
@Route("course")
public class CourseView extends Composite<VerticalLayout> {

    public CourseView() {
        H1 h1 = new H1();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Paragraph textMedium = new Paragraph();
        HorizontalLayout layoutRow = new HorizontalLayout();
        RouterLink routerLink = new RouterLink();
        RouterLink routerLink2 = new RouterLink();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h1.setText("Heading");
        h1.setWidth("max-content");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        textMedium.setText(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        textMedium.setWidth("100%");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        routerLink.setText("Custom View");
        routerLink.setRoute(CourseView.class);
        routerLink2.setText("Custom View");
        routerLink2.setRoute(CourseView.class);
        getContent().add(h1);
        getContent().add(layoutColumn2);
        layoutColumn2.add(textMedium);
        getContent().add(layoutRow);
        layoutRow.add(routerLink);
        layoutRow.add(routerLink2);
    }
}
