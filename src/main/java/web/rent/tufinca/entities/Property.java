package web.rent.tufinca.entities;

import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "status = 0")
@SQLDelete(sql = "UPDATE company SET status = 1 where id = ?")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProperty;
    private String name;
    private String country;
    private String city;
    private String latitude;
    private String longitude;
    private Integer price;
    private Integer numToilets;
    private Integer numRooms;
    private Integer numKitchens;
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "idUser", unique = false, nullable = false)
    private User user;

    @OneToMany(mappedBy = "property")
    private List<Photo> photos;

    @OneToMany(mappedBy = "property")
    private List<Rent> rents;

    @OneToMany(mappedBy = "property")
    private List<RentRequest> rentRequests;

}
