package pl.wwsi.gawarski.technologieinternetowe.model.repository;

import org.springframework.data.repository.CrudRepository;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Order;

import java.util.Optional;

public interface OrderRepo extends CrudRepository<Order, Long> {
    Optional<Order> findByOrderNumber(String orderNumber);
}
