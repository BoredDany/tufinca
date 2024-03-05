package web.rent.tufinca.repositories;

import org.springframework.data.repository.CrudRepository;
import web.rent.tufinca.entities.User;

public interface RepositoryUser extends CrudRepository<User, Long>{

}
