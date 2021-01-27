package pl.wwsi.gawarski.technologieinternetowe.model.repository;

import org.springframework.data.repository.CrudRepository;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Dish;

public interface DishRepo extends CrudRepository<Dish, Long> {
}
