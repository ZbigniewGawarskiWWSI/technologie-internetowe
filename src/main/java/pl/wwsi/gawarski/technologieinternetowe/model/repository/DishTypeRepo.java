package pl.wwsi.gawarski.technologieinternetowe.model.repository;

import org.springframework.data.repository.CrudRepository;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.DishType;

public interface DishTypeRepo extends CrudRepository<DishType, Long> {
}
