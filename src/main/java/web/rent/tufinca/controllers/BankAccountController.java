package web.rent.tufinca.controllers;

import web.rent.tufinca.services.BankAccountService;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.rent.tufinca.dtos.BankAccountDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/grupo23/accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @CrossOrigin
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BankAccountDTO> get() {
        return bankAccountService.get();
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BankAccountDTO get(@PathVariable Long id) {
        return bankAccountService.get(id);
    }

    @CrossOrigin
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public BankAccountDTO save(@RequestBody BankAccountDTO bankAccountDTO) { 
        return bankAccountService.save(bankAccountDTO); 
    }

    @CrossOrigin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BankAccountDTO update(@RequestBody BankAccountDTO bankAccountDTO, @PathVariable Long id) throws IllegalArgumentException{ 
        return bankAccountService.update(bankAccountDTO, id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        bankAccountService.delete(id);
    }

}
