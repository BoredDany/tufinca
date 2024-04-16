package web.rent.tufinca.dtos;

import web.rent.tufinca.entities.Property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO {
    private Long idPhoto;
    private String url;
    private String description;
    private Property property;
}
