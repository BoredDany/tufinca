package web.rent.tufinca.dtos;

import web.rent.tufinca.entities.Property;
import web.rent.tufinca.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentDTO {

    private Long idRent;
    private Integer numPeople;
    private Integer price;
    private String dateStart;
    private String dateEnd;
    private Integer payment;
    private Integer ratingOwner;
    private Integer ratingRenter;
    private User owner;
    private User renter;
    private Property property;

}
