package pl.wwsi.gawarski.technologieinternetowe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Order;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(String orderNumber);
}
