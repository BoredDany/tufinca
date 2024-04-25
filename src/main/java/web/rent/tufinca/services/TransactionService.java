package web.rent.tufinca.services;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.rent.tufinca.dtos.TransactionDTO;
import web.rent.tufinca.entities.Rent;
import web.rent.tufinca.entities.Transaction;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.TransactionRepository;
import web.rent.tufinca.repositories.RepositoryRent;
import web.rent.tufinca.repositories.RepositoryUser;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RepositoryUser userRepository;

    @Autowired
    private RepositoryRent rentRepository;

    @Autowired
    private ModelMapper modelMapper;

    // GET
    public List<TransactionDTO> get() {
        List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .collect(Collectors.toList());
    }

    // GET BY ID
    public TransactionDTO get(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        TransactionDTO transactionDTO = null;
        if (transactionOptional.isPresent()) {
            transactionDTO = modelMapper.map(transactionOptional.get(), TransactionDTO.class);
        }
        return transactionDTO;
    }

    // POST
    public TransactionDTO save(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        Optional<User> userOriginOptional = userRepository.findById(transaction.getOrigin().getIdUser());
        Optional<User> userDestOptional = userRepository.findById(transaction.getDestination().getIdUser());
        Optional<Rent> rentOptional = rentRepository.findById(transaction.getRent().getIdRent());

        if (userOriginOptional.isPresent() && userDestOptional.isPresent() && rentOptional.isPresent()) {
            transaction.setOrigin(userOriginOptional.get());
            transaction.setDestination(userDestOptional.get());
            transaction.setRent(rentOptional.get());

            transaction = transactionRepository.save(transaction);

            transactionDTO.setIdTransaction(transaction.getIdTransaction());
            return transactionDTO;
        }
        return null;
    }

    // PUT
    public TransactionDTO update(TransactionDTO transactionDTO, Long id) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        Optional<User> userOriginOptional = userRepository.findById(transaction.getOrigin().getIdUser());
        Optional<User> userDestOptional = userRepository.findById(transaction.getDestination().getIdUser());
        Optional<Rent> rentOptional = rentRepository.findById(transaction.getRent().getIdRent());

        if (userOriginOptional.isPresent() && userDestOptional.isPresent() && rentOptional.isPresent()) {
            transaction.setOrigin(userOriginOptional.get());
            transaction.setDestination(userDestOptional.get());
            transaction.setRent(rentOptional.get());
            transaction.setAmount(transaction.getAmount());
            transaction.setStatus(transaction.getStatus());

            transaction = transactionRepository.save(transaction);

            transactionDTO.setIdTransaction(transaction.getIdTransaction());
            return transactionDTO;
        }
        return null;
    }

    // DELETE
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

}
