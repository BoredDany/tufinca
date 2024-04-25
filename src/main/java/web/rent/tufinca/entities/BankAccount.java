package web.rent.tufinca.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "bank_account", uniqueConstraints={@UniqueConstraint(columnNames={"id_user", "bank", "accountNumber"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE bank_account SET status = 1 WHERE id_account = ?")
@Where(clause = "status = 0")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccount;

    private String bank;
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "idUser",  nullable = false)
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

}