package web.rent.tufinca.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.rent.tufinca.entities.Property;
import web.rent.tufinca.entities.Status;
import web.rent.tufinca.entities.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentRequestDTO {
    
    private Long idRentRequest;
    private String dateStart;
    private String dateEnd;
    private Integer numPeople;
    private Integer price;
    private Integer approval;
    private Status status;
    private Long ownerId;
    private Long renterId;
    private Long propertyId;
}
