package pl.wwsi.gawarski.technologieinternetowe;

import org.springframework.context.annotation.Configuration;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Address;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Dish;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Order;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Person;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.AddressRepo;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.DishRepo;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.DishTypeRepo;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.OrderRepo;
import pl.wwsi.gawarski.technologieinternetowe.service.DishService;
import pl.wwsi.gawarski.technologieinternetowe.service.OrderService;
import pl.wwsi.gawarski.technologieinternetowe.util.UuidFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Start {
    private DishService dishService;
    private OrderService orderService;
    private DishTypeRepo dishTypeRepo;
    private DishRepo dishRepo;
    private AddressRepo addressRepo;
    private OrderRepo orderRepo;

    public Start(DishService dishService, OrderService orderService, DishTypeRepo dishTypeRepo, DishRepo dishRepo, AddressRepo addressRepo, OrderRepo orderRepo) {
        this.dishService = dishService;
        this.orderService = orderService;
        this.dishTypeRepo = dishTypeRepo;
        this.dishRepo = dishRepo;
        this.addressRepo = addressRepo;
        this.orderRepo = orderRepo;

        dishService.addDishType("Zupa");
        var dishType = dishTypeRepo.findById(1L).orElseThrow(() -> new RuntimeException("nie dziala"));
        for (int i = 0; i < 10; i++) {
            Dish dish = new Dish();
            dish.setDishType(dishType);
            dish.setName("POMIDOROWA" + i);
            dish.setPrice((double) i * 35.49 + 1);
            dishService.addDish(dish);
        }

        Address address = new Address();
        address.setCity("Warszawa");
        address.setPostCode("00-025");
        address.setStreet("Lewartowskiego");
        address.setPropertyNumber("54");
        address.setLocalNumber("15");

        //addressRepo.save(address);
        //address = addressRepo.findById(1L).get();

        Person person = new Person();
        person.setFirstName("first name");
        person.setLastName("last name");
        person.setEMail("test@test.pl");
        person.setPhoneNumber("123456789");

        List<Dish> dishes = new ArrayList<>();
        dishes.add(dishRepo.findById(1L).get());
        dishes.add(dishRepo.findById(2L).get());
        dishes.add(dishRepo.findById(2L).get());
        dishes.add(dishRepo.findById(4L).get());

        Order order = new Order(dishes, address, person, 15.45, LocalDateTime.now(), LocalDateTime.now());
        //order.setCreationDate(LocalDateTime.now());
        //order.setDeliveryDate(LocalDateTime.now());
        //order.setAddress(address);
        //order.setOrderNumber(UuidFactory.generateUUID());
        //order.setPrice(75.54);
        //order.setDishes(dishes);

        orderRepo.save(order);
    }

}