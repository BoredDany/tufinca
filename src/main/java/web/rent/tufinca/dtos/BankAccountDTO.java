package web.rent.tufinca.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.rent.tufinca.entities.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {

    private Long idAccount;
    private String bank;
    private String accountNumber;
    private Status status;
    private Long idUser;

}
