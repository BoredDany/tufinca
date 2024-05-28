package web.rent.tufinca.repositories;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import web.rent.tufinca.entities.Property;

public interface RepositoryProperty extends CrudRepository<Property, Long>{
    List<Property> findByUser_IdUser(Long ownerId);
}
