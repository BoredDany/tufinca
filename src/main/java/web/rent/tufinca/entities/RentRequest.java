package web.rent.tufinca.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@SQLDelete(sql = "UPDATE rent_request SET status = 1 WHERE id_rent_request = ?")
@Where(clause = "status = 0")
@Table(name = "rent_request", uniqueConstraints={@UniqueConstraint(columnNames={"dateStart", "dateEnd", "owner_id", "renter_id", "property_id"})})
public class RentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRentRequest;
    private String dateStart;
    private String dateEnd;
    private Integer numPeople;
    private Integer price;
    private Integer approval;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

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
