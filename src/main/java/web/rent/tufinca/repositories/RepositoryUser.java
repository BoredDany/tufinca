package web.rent.tufinca.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import web.rent.tufinca.entities.User;

import java.util.Optional;

public interface RepositoryUser extends CrudRepository<User, Long>{

    @Query(value = "SELECT * FROM user WHERE email=?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

}
