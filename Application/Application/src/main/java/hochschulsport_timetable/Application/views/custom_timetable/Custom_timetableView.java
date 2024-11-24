package hochschulsport_timetable.Application.views.custom_timetable;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import hochschulsport_timetable.Application.views.about.AboutView;
import hochschulsport_timetable.Application.views.course.CourseView;
import hochschulsport_timetable.Application.views.filter_by_category.Filter_by_categoryView;
import hochschulsport_timetable.Application.views.time_table.Time_tableView;

import java.nio.file.DirectoryStream.Filter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Custom_timetable")
@Route("custom_timetable")
@Uses(Icon.class)
public class Custom_timetableView extends Composite<VerticalLayout> {

    public Custom_timetableView() {
        H1 h1 = new H1();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        MultiSelectComboBox multiSelectComboBox = new MultiSelectComboBox();
        Grid multiSelectGrid = new Grid();
        Button buttonPrimary = new Button();
        HorizontalLayout layoutRow = new HorizontalLayout();
        RouterLink filterByCategoryRouterLink = new RouterLink("Filter by category", Filter_by_categoryView.class);
        RouterLink aboutRouterLink = new RouterLink("About", AboutView.class);
        RouterLink courseRouterLink = new RouterLink("Course", CourseView.class);
        RouterLink timeTableRouterLink = new RouterLink("Time Table", Time_tableView.class);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h1.setText("Custom Timetable");
        h1.setWidth("max-content");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        multiSelectComboBox.setLabel("Multi-Select Combo Box");
        multiSelectComboBox.setWidth("min-content");
        setMultiSelectComboBoxSampleData(multiSelectComboBox);
        multiSelectGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        multiSelectGrid.setWidth("100%");
        multiSelectGrid.getStyle().set("flex-grow", "0");
        buttonPrimary.setText("Button");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");

        getContent().add(h1);
        getContent().add(layoutColumn2);
        layoutColumn2.add(multiSelectComboBox);
        layoutColumn2.add(multiSelectGrid);
        layoutColumn2.add(buttonPrimary);
        getContent().add(layoutRow);
        layoutRow.add(aboutRouterLink);
        layoutRow.add(courseRouterLink);
        layoutRow.add(filterByCategoryRouterLink);
        layoutRow.add(timeTableRouterLink);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setMultiSelectComboBoxSampleData(MultiSelectComboBox multiSelectComboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        multiSelectComboBox.setItems(sampleItems);
        multiSelectComboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}


