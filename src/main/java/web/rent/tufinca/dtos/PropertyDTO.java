package web.rent.tufinca.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.rent.tufinca.entities.PropertyDetail;
import web.rent.tufinca.entities.Rent;
import web.rent.tufinca.entities.RentRequest;
import web.rent.tufinca.entities.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {
    
    private Long idProperty;
    private String name;
    private String country;
    private String city;
    private String latitude;
    private String longitude;
    private Integer price;
    private Integer area;
    private User user;
    private PropertyDetail propertyDetail;
    private List<Rent> rents;
    private List<RentRequest> rentRequests;
    
}
