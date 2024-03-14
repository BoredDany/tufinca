package web.rent.tufinca.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.rent.tufinca.entities.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    
    private Long idUser;
    private String name;
    private String email;
    private Integer phone;
    private String photo;
    private Status status;

}
