package web.rent.tufinca.services;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.rent.tufinca.dtos.BankAccountDTO;
import web.rent.tufinca.entities.BankAccount;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.BankAccountRepository;
import web.rent.tufinca.repositories.RepositoryUser;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private RepositoryUser userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // GET
    public List<BankAccountDTO> get() {
        List<BankAccount> accounts = (List<BankAccount>) bankAccountRepository.findAll();
        return accounts.stream()
                .map(account -> modelMapper.map(account, BankAccountDTO.class))
                .collect(Collectors.toList());
    }

    // GET BY ID
    public BankAccountDTO get(Long id) {
        Optional<BankAccount> accountOptional = bankAccountRepository.findById(id);
        BankAccountDTO bankAccountDTO = null;
        if (accountOptional.isPresent()) {
            bankAccountDTO = modelMapper.map(accountOptional.get(), BankAccountDTO.class);
        }
        return bankAccountDTO;
    }

    // POST
    public BankAccountDTO save(BankAccountDTO bankAccountDTO) {
        BankAccount account = modelMapper.map(bankAccountDTO, BankAccount.class);
        Optional<User> userOptional = userRepository.findById(bankAccountDTO.getIdUser());

        if (userOptional.isPresent()) {
            account.setUser(userOptional.get());
            account = bankAccountRepository.save(account);
            bankAccountDTO.setIdAccount(account.getIdAccount());
            return bankAccountDTO;
        }

        return null;
    }

    // PUT
    public BankAccountDTO update(BankAccountDTO bankAccountDTO, Long id) {
        Optional<BankAccount> optionalAccount = bankAccountRepository.findById(id);

        if (optionalAccount.isPresent()) {
            BankAccount account = optionalAccount.get();

            account.setBank(bankAccountDTO.getBank());
            account.setAccountNumber(bankAccountDTO.getAccountNumber());

            account = bankAccountRepository.save(account);
            bankAccountDTO = modelMapper.map(account, BankAccountDTO.class);
            return bankAccountDTO;
        }
        return null;
    }

    // DELETE
    public void delete(Long id) {
        bankAccountRepository.deleteById(id);
    }

}
