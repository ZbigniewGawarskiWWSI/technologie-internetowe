package pl.wwsi.gawarski.technologieinternetowe;

import org.springframework.context.annotation.Configuration;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.*;
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
/*
        dishService.addDishType("Zupa");
        var dishType = dishTypeRepo.findById(1L).orElseThrow(() -> new RuntimeException("nie dziala"));
        for (int i = 0; i < 10; i++) {
            Dish dish = new Dish();
            dish.setDishType(dishType);
            dish.setName("POMIDOROWA" + i);
            dish.setPrice((double) i * 35.49 + 1);
            dishService.addDish(dish);
        }*/
        DishType dishType = new DishType();
        dishType.setName("MIĘSO");
        dishTypeRepo.save(dishType);

        Dish dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Schabowy");
        dish.setPrice(14.50);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Sznycel");
        dish.setPrice(17.50);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Stek");
        dish.setPrice(30);
        dishService.addDish(dish);

        dishType = new DishType();
        dishType.setName("ZUPY");
        dishTypeRepo.save(dishType);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Rosół");
        dish.setPrice(7);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Pomidorowa");
        dish.setPrice(8);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Żurek");
        dish.setPrice(13);
        dishService.addDish(dish);

        dishType = new DishType();
        dishType.setName("DESERY");
        dishTypeRepo.save(dishType);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Szarlotka");
        dish.setPrice(12);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Beza");
        dish.setPrice(11);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Brownie");
        dish.setPrice(9);
        dishService.addDish(dish);

        dishType = new DishType();
        dishType.setName("NAPOJE");
        dishTypeRepo.save(dishType);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Cola");
        dish.setPrice(35);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Sok pomarańczowy");
        dish.setPrice(6);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Woda");
        dish.setPrice(4);
        dishService.addDish(dish);

        dishType = new DishType();
        dishType.setName("DODATKI");
        dishTypeRepo.save(dishType);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Ryż");
        dish.setPrice(8);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Ziemniaki");
        dish.setPrice(6);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Frytki");
        dish.setPrice(10);
        dishService.addDish(dish);

        dishType = new DishType();
        dishType.setName("RYBY");
        dishTypeRepo.save(dishType);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Łupacz");
        dish.setPrice(40);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Sandacz");
        dish.setPrice(38);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Pstrąg");
        dish.setPrice(25);
        dishService.addDish(dish);

        Address address = new Address();
        address.setCity("Warszawa");
        address.setPostCode("00-025");
        address.setStreet("Lewartowskiego");
        address.setPropertyNumber("54");
        address.setLocalNumber("15");

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
        dishes.add(dishRepo.findById(5L).get());
        dishes.add(dishRepo.findById(6L).get());
        dishes.add(dishRepo.findById(5L).get());
        dishes.add(dishRepo.findById(7L).get());

        Order order = new Order(dishes, address, person, 15.45, LocalDateTime.now(), LocalDateTime.now());
        orderRepo.save(order);

        var temp = new DishType();
        var temp2 = new DishType();
        temp.setName("TEST");

        dishTypeRepo.save(temp);
        dishTypeRepo.save(temp2);
    }

}