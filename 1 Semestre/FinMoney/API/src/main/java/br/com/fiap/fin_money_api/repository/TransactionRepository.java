package br.com.fiap.fin_money_api.repository;

import br.com.fiap.fin_money_api.model.Transaction;
import br.com.fiap.fin_money_api.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

//    SELECT * FROM transaction WHERE description =: description
//    @Query("SELECT t FROM transaction as t WHERE t.description =: description") //JPQL
//    Page<Transaction> findByDescriptionContainingIgnoringCase(String description, Pageable pageable);
//    Page<Transaction> findByDescriptionContainingIgnoringCaseAndDate(String description, LocalDate date, Pageable pageable);
//    Page<Transaction> findByDate(LocalDate date, Pageable pageable);

    List<Transaction> findByType(TransactionType type);
}
