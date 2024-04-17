package web.rent.tufinca.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.rent.tufinca.entities.Rent;
import web.rent.tufinca.entities.RentRequest;
import web.rent.tufinca.entities.Status;
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
    private String description;
    private Integer rooms;
    private Integer bathrooms;
    private Integer parking;
    private Integer kitchens;
    private Integer floors;
    private Status status;
    private Long ownerId;
    private List<Long> rentIds;
    private List<Long> rentRequestIds;
    private List<Long> photoIds;
    
}
