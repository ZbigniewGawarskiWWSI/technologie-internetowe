package pl.wwsi.gawarski.technologieinternetowe.gui.order;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO;
import pl.wwsi.gawarski.technologieinternetowe.gui.main.MainView;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Dish;
import pl.wwsi.gawarski.technologieinternetowe.model.helper.Basket;
import pl.wwsi.gawarski.technologieinternetowe.service.DishService;

import java.util.HashMap;
import java.util.Map;

@Route(value = "order-view", layout = MainView.class)
@PageTitle("Order")
@CssImport("./styles/views/masterdetail/master-detail-view.css")
public class OrderView extends Div {

    private DishService dishService;

    private Tab tabMenu;
    private Div divMenu;
    private Tab tabBasket;
    private Div divBasket;
    private Tab tabDetails;
    private Div divDetails;


    private Basket basket;
    private Grid<DishDTO> gridMenu = new Grid<>(DishDTO.class, true);
    private Grid<DishDTO> gridBasket = new Grid<>(DishDTO.class, true);

    //Person section
    private TextField textFieldFirstName;
    private TextField textFieldLastName;
    private EmailField emailFieldEmail;
    private TextField textFieldPhone;

    //Address section
    private TextField textFieldCity;
    private TextField textFieldPostCode;
    private TextField textFieldStreet;
    private TextField textFieldPropertyNumber;
    private TextField textFieldLocalNumber;

    //Order details section
    private DateTimePicker dateTimePickerDeliveryDate;
    private TextField occupation;
    private Checkbox important;

    private Button buttonCancel = new Button("Cancel");
    private Button buttonCreateOrder = new Button("Create Order");

    private Dish dish;

    @Autowired
    public OrderView(DishService dishService) {
        this.dishService = dishService;

        createTabs();

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tabMenu, divMenu);
        tabsToPages.put(tabBasket, divBasket);
        tabsToPages.put(tabDetails, divDetails);
        Tabs tabs = new Tabs(tabMenu, tabBasket, tabDetails);
        Div pages = new Div(divMenu, divBasket, divDetails);

        tabs.addSelectedChangeListener(event -> {
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });


        add(tabs, pages);
    }


    private void createTabMenu() {
        tabMenu = new Tab("Menu");
        divMenu = new Div();
    }

    private void createTabBasket() {
        tabBasket = new Tab("Basket");
        divBasket = new Div();
        divBasket.setVisible(false);
        gridBasket.setItems(dishService.getAllInDto());
        gridBasket.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        gridBasket.setHeightByRows(true);
        divBasket.add(gridBasket);
    }

    private void createTabDetails() {
        tabDetails = new Tab("Details");
        divDetails = new Div();
        divDetails.setVisible(false);

        textFieldFirstName = new TextField("First Name");
        textFieldLastName = new TextField("Last Name");
        emailFieldEmail = new EmailField("Email");
        textFieldPhone = new TextField("Phone");
        dateTimePickerDeliveryDate = new DateTimePicker("Delivery Time");

        textFieldCity = new TextField("City");
        textFieldPostCode = new TextField("Post Code");
        textFieldStreet = new TextField("Street");
        textFieldPropertyNumber = new TextField("Property Number");
        textFieldLocalNumber = new TextField("Local Number");

        Component[] components = new Component[]{
                textFieldFirstName,
                textFieldLastName,
                emailFieldEmail,
                textFieldPhone,
                dateTimePickerDeliveryDate,
                textFieldCity,
                textFieldPostCode,
                textFieldStreet,
                textFieldPropertyNumber,
                textFieldLocalNumber,
        };

        for (Component component : components) {
            ((HasStyle) component).addClassName("full-width");
        }

        divDetails.add(components);
    }

    private void createTabs() {
        createTabBasket();
        createTabMenu();
        createTabDetails();
    }

}
