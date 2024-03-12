package web.rent.tufinca.entities;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class PropertyDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPropertyDetail;

    private Integer numToilets;
    private Integer numRooms;
    private Integer numKitchens;
    private Integer numLevel;
    private String description;


    @OneToMany(mappedBy = "propertyDetail")
    private List<Photo> photos;

    @OneToOne
    @JoinColumn(name = "idProperty", unique = true, nullable = false)
    private Property property;

}
