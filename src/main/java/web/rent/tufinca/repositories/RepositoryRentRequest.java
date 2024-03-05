package web.rent.tufinca.repositories;

import org.springframework.data.repository.CrudRepository;

import web.rent.tufinca.entities.RentRequest;

public interface RepositoryRentRequest extends CrudRepository<RentRequest, Long> {

}
