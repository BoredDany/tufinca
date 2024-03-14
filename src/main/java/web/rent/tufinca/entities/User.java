package web.rent.tufinca.entities;

import java.util.UUID;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "status = 0")
@SQLDelete(sql = "UPDATE company SET status = 1 where id = ?")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String name;
    private String email;
    private String password;
    private Integer phone;
    private Integer money;
    private String photo;
    private Status status;

    @OneToMany(mappedBy = "user")
    private List<Property> properties;

    @OneToMany(mappedBy = "renter")
    private List<Rent> reservations;

    @OneToMany(mappedBy = "owner")
    private List<Rent> rents;

    @OneToMany(mappedBy = "owner")
    private List<RentRequest> rentRequests;

    @OneToMany(mappedBy = "renter")
    private List<RentRequest> reservationRequests;


    
}