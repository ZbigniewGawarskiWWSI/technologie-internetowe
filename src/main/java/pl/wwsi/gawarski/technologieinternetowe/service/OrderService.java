package pl.wwsi.gawarski.technologieinternetowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wwsi.gawarski.technologieinternetowe.dto.DishDTO;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Address;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Order;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Person;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.AddressRepo;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.DishRepo;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.DishTypeRepo;
import pl.wwsi.gawarski.technologieinternetowe.model.repository.OrderRepo;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderService {
    private OrderRepo orderRepo;
    DishTypeRepo dishTypeRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, DishRepo dishRepo, AddressRepo addressRepo, DishTypeRepo dishTypeRepo) {
        this.orderRepo = orderRepo;
        this.dishTypeRepo = dishTypeRepo;
    }

    public void createOrder(List<DishDTO> dishDTOList, double price, Person person, Address address, LocalDateTime creationTime, LocalDateTime deliveryTime) {
        var dishes = DishDTO.convertDtosToDish(dishDTOList, dishTypeRepo);
        Order order = new Order(dishes, address, person, price, creationTime, deliveryTime);
        orderRepo.save(order);
    }
}
