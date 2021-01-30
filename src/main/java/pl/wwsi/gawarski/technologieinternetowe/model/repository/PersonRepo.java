package pl.wwsi.gawarski.technologieinternetowe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Person;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Long> {
}
