package pl.wwsi.gawarski.technologieinternetowe.gui.order;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.SortDirection;
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
    private Basket basket;

    private Tab tabMenu;
    private Div divMenu;
    private Tab tabBasket;
    private Div divBasket;

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


    @Autowired
    public OrderView(DishService dishService, Basket basket) {
        this.dishService = dishService;
        this.basket = new Basket();

        createTabs();

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tabMenu, divMenu);
        tabsToPages.put(tabBasket, divBasket);
        Tabs tabs = new Tabs(tabMenu, tabBasket);
        Div pages = new Div(divMenu, divBasket);

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

        gridMenu.setItems(dishService.getAllInDto());
        gridMenu.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        gridMenu.setHeightByRows(true);
        gridMenu.setSelectionMode(Grid.SelectionMode.NONE);
        gridMenu.addComponentColumn(dish ->
        {
            Button button = new Button("Add");
            button.addClickListener(click -> {
                Dialog dialog = new Dialog();
                IntegerField integerFieldDishNumber = new IntegerField();
                integerFieldDishNumber.setHasControls(true);
                integerFieldDishNumber.setMin(0);
                Button buttonAdd = new Button("Confirm", e -> {
                    int numbers = integerFieldDishNumber.getValue();
                    basket.addDishes(dish, numbers);
                    gridBasket.setItems(basket.getDishes());
                    dialog.close();
                });
                Button buttonCancel = new Button("Cancel", e -> {
                    dialog.close();
                });
                dialog.add(integerFieldDishNumber, buttonAdd, buttonCancel);
                dialog.open();
            });
            return button;
        });
        /*
        gridMenu.addComponentColumn(dish -> {
            IntegerField numberField = new IntegerField();
            numberField.setHasControls(true);
            numberField.setMin(0);
            return numberField;
        });
        gridMenu.addComponentColumn(dish ->
        {
            Button button = new Button("Add");
            button.addClickListener(click -> {
            });
            return button;
        });*/
        divMenu.add(gridMenu);
    }

    private void createTabBasket() {
        tabBasket = new Tab("Basket");
        divBasket = new Div();
        divBasket.setVisible(false);
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();
        gridBasket.setItems(basket.getDishes());
        gridBasket.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        gridBasket.setHeightByRows(true);
        gridBasket.setSelectionMode(Grid.SelectionMode.NONE);
        gridBasket.addComponentColumn(dish -> {
            Button button = new Button("Remove");
            button.addClickListener(click -> {
                basket.removeDish(dish);
                gridBasket.setItems(basket.getDishes());
            });
            return button;
        });
        splitLayout.addToPrimary(gridBasket);

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

        splitLayout.addToSecondary(components);
        divBasket.add(splitLayout);
    }

    private void createTabs() {
        createTabBasket();
        createTabMenu();
    }

    private void refreshGridBasket() {
        gridBasket.setItems(basket.getDishes());
    }

}
