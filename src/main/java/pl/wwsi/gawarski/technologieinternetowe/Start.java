package pl.wwsi.gawarski.technologieinternetowe;

import org.springframework.context.annotation.Configuration;
import pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.*;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.*;
import pl.wwsi.gawarski.technologieinternetowe.service.DishService;
import pl.wwsi.gawarski.technologieinternetowe.service.OrderService;
import pl.wwsi.gawarski.technologieinternetowe.util.UuidFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO.convertDishToDto;

@Configuration
public class Start {
    private DishService dishService;
    private OrderService orderService;
    private DishTypeRepo dishTypeRepo;
    private DishRepo dishRepo;
    private AddressRepo addressRepo;
    private OrderRepo orderRepo;
    private PersonRepo personRepo;

    public Start(DishService dishService, OrderService orderService, DishTypeRepo dishTypeRepo, DishRepo dishRepo, AddressRepo addressRepo, OrderRepo orderRepo, PersonRepo personRepo) {
        this.dishService = dishService;
        this.orderService = orderService;
        this.dishTypeRepo = dishTypeRepo;
        this.dishRepo = dishRepo;
        this.addressRepo = addressRepo;
        this.orderRepo = orderRepo;
        this.personRepo = personRepo;

        DishType dishType = new DishType();
        dishType.setName("MIESO");
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
        dish.setName("Rosoł");
        dish.setPrice(7);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Pomidorowa");
        dish.setPrice(8);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Zurek");
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
        dish.setName("Sok Mandarynkowy");
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
        dish.setName("Ryz");
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
        dish.setName("Dorsz");
        dish.setPrice(40);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Sandacz");
        dish.setPrice(38);
        dishService.addDish(dish);

        dish = new Dish();
        dish.setDishType(dishType);
        dish.setName("Pstrag");
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

        List<DishDTO> dishesdto = new ArrayList<>();
        var dishtemp = dishRepo.findById(1L).get();
        dishesdto.add(convertDishToDto(dishtemp));
        dishtemp = dishRepo.findById(1L).get();
        dishesdto.add(convertDishToDto(dishtemp));
        dishtemp = dishRepo.findById(2L).get();
        dishesdto.add(convertDishToDto(dishtemp));

        orderService.createOrder(dishesdto, 15.4, person, address, LocalDateTime.now(), LocalDateTime.now());
    }

}