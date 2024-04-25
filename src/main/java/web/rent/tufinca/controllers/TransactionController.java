package web.rent.tufinca.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.rent.tufinca.dtos.TransactionDTO;
import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.services.TransactionService;
import web.rent.tufinca.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import web.rent.tufinca.utils.HTTPResponse;

@RestController
@RequestMapping("/grupo23/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @CrossOrigin
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionDTO> get() {
        return transactionService.get();
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionDTO get(@PathVariable Long id) {
        return transactionService.get(id);
    }
    
    @CrossOrigin
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionDTO save(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.save(transactionDTO);
    }

    @CrossOrigin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionDTO update(@RequestBody TransactionDTO transactionDTO, @PathVariable Long id) throws IllegalArgumentException {
        return transactionService.update(transactionDTO, id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }
}
