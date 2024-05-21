package web.rent.tufinca.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import web.rent.tufinca.entities.Rent;

public interface RepositoryRent extends CrudRepository<Rent, Long> {
    List<Rent> findByOwner_IdUser(Long ownerId);
}
