package pl.wwsi.gawarski.technologieinternetowe.model.repository;

import org.springframework.data.repository.CrudRepository;
import pl.wwsi.gawarski.technologieinternetowe.model.entity.Address;

public interface AddressRepo extends CrudRepository<Address, Long> {
}
