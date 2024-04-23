package web.rent.tufinca.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.rent.tufinca.entities.Status;

import java.util.List;

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
    private String password;
    private Integer money;

    private List<Long> propertyIds;
    private List<Long> reservationIds;
    private List<Long> rentIds;
    private List<Long> rentRequestIds;
    private List<Long> reservationRequestIds;

}
