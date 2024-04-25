package web.rent.tufinca.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@SQLDelete(sql = "UPDATE transaction SET status = 1 WHERE id_transaction = ?")
@Where(clause = "status = 0")
@Table(name="transaction", uniqueConstraints={@UniqueConstraint(columnNames={"origin", "destination", "rent"})})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaction;

    @ManyToOne
    @JoinColumn(name = "origin_id", referencedColumnName = "idUser", unique = false, nullable = false)
    private User origin;
    
    @ManyToOne
    @JoinColumn(name = "destination_id", referencedColumnName = "idUser", unique = false, nullable = false)
    private User destination;
    
    @OneToOne
    @JoinColumn(name = "rent_id", referencedColumnName = "idRent", nullable = false)
    private Rent rent;
    
    private Double amount;
    private Status status;
}
