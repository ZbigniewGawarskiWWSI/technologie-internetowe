package pl.wwsi.gawarski.technologieinternetowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Address;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Order;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Person;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.*;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderService {
    private OrderRepo orderRepo;
    private DishTypeRepo dishTypeRepo;
    private AddressRepo addressRepo;
    private PersonRepo personRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, DishTypeRepo dishTypeRepo, AddressRepo addressRepo, PersonRepo personRepo) {
        this.orderRepo = orderRepo;
        this.dishTypeRepo = dishTypeRepo;
        this.addressRepo = addressRepo;
        this.personRepo = personRepo;
    }

    public void createOrder(List<DishDTO> dishDTOList, double price, Person person, Address address, LocalDateTime creationTime, LocalDateTime deliveryTime) {
        var dishes = DishDTO.convertDtosToDish(dishDTOList, dishTypeRepo);
        Order order = new Order(dishes, address, person, price, creationTime, deliveryTime);
        orderRepo.save(order);
    }
}
