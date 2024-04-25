package web.rent.tufinca.repositories;

import org.springframework.data.repository.CrudRepository;

import web.rent.tufinca.entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
