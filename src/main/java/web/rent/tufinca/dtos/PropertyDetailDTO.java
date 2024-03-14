package web.rent.tufinca.dtos;

import java.util.List;
import web.rent.tufinca.entities.Photo;
import web.rent.tufinca.entities.Property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDetailDTO {
    private Long idPropertyDetail;
    private Integer numToilets;
    private Integer numRooms;
    private Integer numKitchens;
    private Integer numLevel;
    private String description;
    private List<Photo> photos;
    private Property property;
}
