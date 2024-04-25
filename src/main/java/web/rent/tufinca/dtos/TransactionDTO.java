package web.rent.tufinca.dtos;

import web.rent.tufinca.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long idTransaction;

    //map
    private Long idUserOrigin;
    
    //map
    private Long idUserDestination;
    
    //map
    private Long idRent;
    
    private Double amount;
    private Status status;
}
