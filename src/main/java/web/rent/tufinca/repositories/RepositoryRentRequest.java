package web.rent.tufinca.repositories;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import web.rent.tufinca.entities.RentRequest;

public interface RepositoryRentRequest extends CrudRepository<RentRequest, Long> {
    List<RentRequest> findByOwner_IdUser(Long ownerId);
    List<RentRequest> findByRenter_IdUser(Long renterId);
}
