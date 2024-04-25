package web.rent.tufinca.repositories;

import org.springframework.data.repository.CrudRepository;

import web.rent.tufinca.entities.BankAccount;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {

}
