package web.rent.tufinca.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class RentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRentRequest;
    private String dateStart;
    private String dateEnd;
    private Integer numPeople;
    private Integer price;
    private Integer approval;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "idUser", unique = false, nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "renter_id", referencedColumnName = "idUser", unique = false, nullable = false)
    private User renter;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "idProperty", unique = false, nullable = false)
    private Property property;



}
