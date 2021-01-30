package pl.wwsi.gawarski.technologieinternetowe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
