package pl.wwsi.gawarski.technologieinternetowe.model.repository;

import org.springframework.data.jpa.repository.Query;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.DishType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DishTypeRepo extends JpaRepository<DishType, Long> {
    Optional<DishType> findByName(String name);

    //@Query(value = "SELECT * FROM DISH_TYPES d WHERE d.NAME = ?1", nativeQuery = true)
    //Optional<DishType> findByName(String name);
}
