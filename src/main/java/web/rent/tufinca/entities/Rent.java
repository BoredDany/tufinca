package web.rent.tufinca.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
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
@Where(clause = "status = 0")
@SQLDelete(sql = "UPDATE company SET status = 1 where id = ?")
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"dateStart", "dateEnd", "owner_id", "renter_id", "property_id"})})

public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRent;
    private Integer numPeople;
    private Integer price;
    private String dateStart;
    private String dateEnd;
    private Integer payment;
    private Integer ratingOwner;
    private Integer ratingRenter;

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
