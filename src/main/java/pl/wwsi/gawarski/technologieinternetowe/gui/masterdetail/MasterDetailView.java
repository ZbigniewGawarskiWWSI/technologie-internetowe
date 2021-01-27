package pl.wwsi.gawarski.technologieinternetowe.gui.masterdetail;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO;
import pl.wwsi.gawarski.technologieinternetowe.gui.main.MainView;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Dish;
import pl.wwsi.gawarski.technologieinternetowe.model.helper.Basket;
import pl.wwsi.gawarski.technologieinternetowe.service.DishService;

@Route(value = "master-detail", layout = MainView.class)
@PageTitle("Master-Detail")
@CssImport("./styles/views/masterdetail/master-detail-view.css")
public class MasterDetailView extends Div {

    private DishService dishService;
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

    private BeanValidationBinder<Dish> binder;

    private Dish dish;

    @Autowired
    public MasterDetailView(DishService dishService) {
        this.dishService = dishService;
        setId("master-detail-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createBasketLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        gridMenu.addComponentColumn(dish -> {
            Button button = new Button("Click me!");
            button.addClickListener(click ->
                    Notification.show("Clicked: " + dish.toString()));
            return button;
        });
        gridMenu.setItems(dishService.getAllInDto());
        gridMenu.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        gridMenu.setHeightFull();

        // when a row is selected or deselected, populate form
    }

    private void createBasketLayout(SplitLayout splitLayout) {
        Div orderLayoutDiv = new Div();
        orderLayoutDiv.setId("order-layout");

        Div divDetails = new Div();
        divDetails.setId("details-div");

        Div divBasket = new Div();
        divBasket.setId("basket-div");

        orderLayoutDiv.add(divDetails, divBasket);

        FormLayout layoutDetails = new FormLayout();
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

        //gridBasket.setItems();
        FormLayout layoutBasket = new FormLayout();
        var temp = dishService.getAllInDto();
        gridBasket.setItems(temp);
        gridBasket.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        gridBasket.setWidthFull();
        gridBasket.setHeightFull();


        /*
        occupation = new TextField("Occupation");
        important = new Checkbox("Important");
        important.getStyle().set("padding-top", "var(--lumo-space-m)");*/
        Component[] componentsDetails = new Component[]{
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

        Component[] componentsBasket = new Component[]{
                gridBasket
        };

        for (Component component : componentsDetails) {
            ((HasStyle) component).addClassName("full-width");
        }

        for (Component component : componentsBasket) {
            ((HasStyle) component).addClassName("full-width");
        }
        layoutDetails.add(componentsDetails);
        layoutBasket.add(componentsDetails);
        divDetails.add(layoutDetails);
        divBasket.add(layoutBasket);
        createButtonLayout(orderLayoutDiv);

        splitLayout.addToSecondary(orderLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        buttonCancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonCreateOrder.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(buttonCreateOrder, buttonCancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(gridMenu);
    }

    private void refreshGrid() {
        gridMenu.select(null);
        gridMenu.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Dish value) {
        //this.dish = value;
        //binder.readBean(this.dish);

    }
}
