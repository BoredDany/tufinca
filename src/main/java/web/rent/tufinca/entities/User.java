package web.rent.tufinca.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE user SET status = 1 WHERE id_user = ?")
@Where(clause = "status = 0")
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
    
    @Enumerated(EnumType.ORDINAL)
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