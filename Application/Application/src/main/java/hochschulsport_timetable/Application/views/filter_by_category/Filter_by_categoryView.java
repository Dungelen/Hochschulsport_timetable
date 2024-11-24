package hochschulsport_timetable.Application.views.filter_by_category;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import hochschulsport_timetable.Application.views.about.AboutView;
import hochschulsport_timetable.Application.views.custom_timetable.Custom_timetableView;

import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Filter_by_category")
@Route("filter_by_category")
@Uses(Icon.class)
public class Filter_by_categoryView extends Composite<VerticalLayout> {

    public Filter_by_categoryView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Button buttonSecondary = new Button();
        Button buttonPrimary = new Button();
        TextField textField = new TextField();
        MenuBar menuBar = new MenuBar();
        Grid basicGrid = new Grid();
        TabSheet tabSheet = new TabSheet();
        HorizontalLayout layoutRow = new HorizontalLayout();
        RouterLink customTimeTableRouterLink = new RouterLink("Custom Time Table", Custom_timetableView.class);
        RouterLink aboutRouterLink = new RouterLink("About", AboutView.class);
        RouterLink courseRouterLink = new RouterLink("Course", Custom_timetableView.class);
        RouterLink timeTableRouterLink = new RouterLink("Time Table", Custom_timetableView.class);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        buttonSecondary.setText("Button");
        buttonSecondary.setWidth("min-content");
        buttonPrimary.setText("Button");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        textField.setLabel("Text field");
        textField.setWidth("min-content");
        menuBar.setWidth("min-content");
        setMenuBarSampleData(menuBar);
        basicGrid.setWidth("100%");
        basicGrid.getStyle().set("flex-grow", "0");
        //setGridSampleData(basicGrid);
        tabSheet.setWidth("100%");
        setTabSheetSampleData(tabSheet);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        getContent().add(layoutColumn2);
        layoutColumn2.add(buttonSecondary);
        layoutColumn2.add(buttonPrimary);
        layoutColumn2.add(textField);
        layoutColumn2.add(menuBar);
        layoutColumn2.add(basicGrid);
        layoutColumn2.add(tabSheet);
        getContent().add(layoutRow);
        layoutRow.add(aboutRouterLink);
        layoutRow.add(courseRouterLink);
        layoutRow.add(customTimeTableRouterLink);
        layoutRow.add(timeTableRouterLink);
    }

    private void setMenuBarSampleData(MenuBar menuBar) {
        menuBar.addItem("View");
        menuBar.addItem("Edit");
        menuBar.addItem("Share");
        menuBar.addItem("Move");
    }

    private void setTabSheetSampleData(TabSheet tabSheet) {
        tabSheet.add("Dashboard", new Div(new Text("This is the Dashboard tab content")));
        tabSheet.add("Payment", new Div(new Text("This is the Payment tab content")));
        tabSheet.add("Shipping", new Div(new Text("This is the Shipping tab content")));
    }
}
