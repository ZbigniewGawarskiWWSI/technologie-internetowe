package pl.wwsi.gawarski.technologieinternetowe.gui.order;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO;
import pl.wwsi.gawarski.technologieinternetowe.gui.main.MainView;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Address;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Person;
import pl.wwsi.gawarski.technologieinternetowe.model.helper.Basket;
import pl.wwsi.gawarski.technologieinternetowe.service.DishService;
import pl.wwsi.gawarski.technologieinternetowe.service.OrderService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Route(value = "order-view", layout = MainView.class)
@PageTitle("Order")
@CssImport("./styles/views/masterdetail/master-detail-view.css")
public class OrderView extends Div {

    private OrderService orderService;
    private DishService dishService;
    private Basket basket;


    private Tab tabMenu;
    private Div divMenu;
    private Tab tabBasket;
    private Div divBasket;

    private Grid<DishDTO> gridMenu = new Grid<>(DishDTO.class, true);
    private Grid<DishDTO> gridBasket = new Grid<>(DishDTO.class, true);

    //Order details section
    private TextField textFieldFirstName;
    private TextField textFieldLastName;
    private EmailField emailFieldEmail;
    private TextField textFieldPhone;
    private TextField textFieldCity;
    private TextField textFieldPostCode;
    private TextField textFieldStreet;
    private TextField textFieldPropertyNumber;
    private TextField textFieldLocalNumber;
    private DateTimePicker dateTimePickerDeliveryDate;

    private HorizontalLayout layoutButton;
    private HorizontalLayout layoutTotalPrice;
    private Button buttonCreateOrder;
    private Label labelTotalPrice;


    @Autowired
    public OrderView(DishService dishService, OrderService orderService, Basket basket) {
        this.dishService = dishService;
        this.orderService = orderService;
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
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            button.addClickListener(click -> {
                Dialog dialog = new Dialog();
                IntegerField integerFieldDishNumber = new IntegerField();
                integerFieldDishNumber.setHasControls(true);
                integerFieldDishNumber.setMin(0);
                Button buttonConfirm = new Button("Confirm", e -> {
                    if (integerFieldDishNumber.getValue() != null) {
                        int numbers = integerFieldDishNumber.getValue();
                        basket.addDishes(dish, numbers);
                        refreshGridBasket();
                        dialog.close();
                    }
                });
                Button buttonCancel = new Button("Cancel", e -> {
                    dialog.close();
                });
                dialog.add(integerFieldDishNumber, buttonConfirm, buttonCancel);
                dialog.open();
            });
            return button;
        });
        divMenu.add(gridMenu);
    }

    private void createTabBasket() {
        tabBasket = new Tab("Basket");
        divBasket = new Div();
        divBasket.setVisible(false);
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();
        refreshGridBasket();
        gridBasket.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        gridBasket.setHeightByRows(true);
        gridBasket.setSelectionMode(Grid.SelectionMode.NONE);
        gridBasket.addComponentColumn(dishDTO -> {
            IntegerField integerFieldNumberOfDishes = new IntegerField();
            integerFieldNumberOfDishes.setEnabled(false);
            integerFieldNumberOfDishes.setValue(basket.getDishMap().get(dishDTO));
            return integerFieldNumberOfDishes;
        }).setHeader("Number of Dish");
        gridBasket.addComponentColumn(dish -> {
            Button button = new Button("Remove");
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            button.addClickListener(click -> {
                basket.removeDish(dish);
                refreshGridBasket();
            });
            return button;
        });

        this.layoutTotalPrice = new HorizontalLayout();
        this.layoutTotalPrice.setId("button-layout");
        this.layoutTotalPrice.setWidthFull();
        this.layoutTotalPrice.setSpacing(true);
        this.labelTotalPrice = new Label();
        this.labelTotalPrice.setText("" + basket.getPrice());
        Label labelDescription = new Label("Total Price:");
        this.layoutTotalPrice.add(this.labelTotalPrice);
        splitLayout.addToPrimary(gridBasket, labelDescription, layoutTotalPrice);

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

        createButtonCreateOrder();

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
                layoutButton
        };

        for (Component component : components) {
            ((HasStyle) component).addClassName("full-width");
        }

        splitLayout.addToSecondary(components);
        divBasket.add(splitLayout);
    }

    private void createButtonCreateOrder() {
        this.buttonCreateOrder = new Button("Create Order");
        this.buttonCreateOrder.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonCreateOrder.addClickListener(e -> {
            String firstName = this.textFieldFirstName.getValue();
            String lastName = this.textFieldLastName.getValue();
            String email = this.emailFieldEmail.getValue();
            String phone = this.textFieldPhone.getValue();
            LocalDateTime deliveryTime = this.dateTimePickerDeliveryDate.getValue();
            String city = this.textFieldCity.getValue();
            String postCode = this.textFieldPostCode.getValue();
            String street = this.textFieldStreet.getValue();
            String propertyNumber = this.textFieldPropertyNumber.getValue();
            String localNumber = this.textFieldLocalNumber.getValue();
            var dishDtoList = basket.getDishList();
            double price = basket.getPrice();
            Person person = new Person(firstName, lastName, phone, email);
            Address address = new Address(city, postCode, street, propertyNumber, localNumber);
            orderService.createOrder(dishDtoList, price, person, address, LocalDateTime.now(), deliveryTime);
            this.basket = new Basket();
            refreshGridBasket();
        });

        this.layoutButton = new HorizontalLayout();
        this.layoutButton.setId("button-layout");
        this.layoutButton.setWidthFull();
        this.layoutButton.setSpacing(true);
        this.layoutButton.add(this.buttonCreateOrder);
    }

    private void createTabs() {
        createTabBasket();
        createTabMenu();
    }

    private void refreshGridBasket() {
        try {
            this.gridBasket.setItems(this.basket.getDishMap().keySet());
            this.labelTotalPrice.setText("" + this.basket.getPrice());
        } catch (Exception e) {

        }

    }


}
