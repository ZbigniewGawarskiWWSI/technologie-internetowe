package pl.wwsi.gawarski.technologieinternetowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO;
import pl.wwsi.gawarski.technologieinternetowe.exception.exception.DishNotFoundException;
import pl.wwsi.gawarski.technologieinternetowe.exception.exception.OrderNotFoundException;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Address;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Dish;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Order;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Person;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.AddressRepo;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.DishRepo;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.DishTypeRepo;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.OrderRepo;
import pl.wwsi.gawarski.technologieinternetowe.util.UuidFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {
    private OrderRepo orderRepo;
    private DishRepo dishRepo;
    private AddressRepo addressRepo;
    DishTypeRepo dishTypeRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, DishRepo dishRepo, AddressRepo addressRepo, DishTypeRepo dishTypeRepo) {
        this.orderRepo = orderRepo;
        this.dishRepo = dishRepo;
        this.addressRepo = addressRepo;
        this.dishTypeRepo = dishTypeRepo;
    }

    public Order findByOrderNumber(String orderNumber) {
        return orderRepo.findByOrderNumber(orderNumber).orElseThrow(() -> new OrderNotFoundException(orderNumber));
    }

    public void createOrder(List<DishDTO> dishDTOList, double price, Person person, Address address, LocalDateTime creationTime, LocalDateTime deliveryTime) {
        var dishes = DishDTO.convertDtosToDish(dishDTOList, dishTypeRepo);
        Order order = new Order(dishes, address, person, price, creationTime, deliveryTime);
        orderRepo.save(order);
    }
/*
    public String createOrder(CreateOrderForm createOrderForm) {
        List<Dish> dishes = new ArrayList();
        for (Long id : createOrderForm.getDishesId()) {
            Dish dish = dishRepo.findById(id).orElseThrow(() -> new DishNotFoundException(id));
            dishes.add(dish);
        }
        double price = 0;
        for (Dish dish : dishes) {
            price += dish.getPrice();
        }

        Address address = AddressConverter.dtoToAddress(createOrderForm.getAddressDTO());
        addressRepo.save(address);
        String orderNumber = UuidFactory.generateUUID();
        Order order = new Order(dishes, orderNumber, price, address);
        orderRepo.save(order);
        return orderNumber;
    }*/

    public Iterable<Order> findAll() {
        return orderRepo.findAll();
    }
}
