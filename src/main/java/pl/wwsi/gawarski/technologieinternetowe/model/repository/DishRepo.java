package pl.wwsi.gawarski.technologieinternetowe.model.repository;

import pl.wwsi.gawarski.technologieinternetowe.model.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepo extends JpaRepository<Dish, Long> {
}
