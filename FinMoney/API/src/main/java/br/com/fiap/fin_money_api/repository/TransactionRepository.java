package br.com.fiap.fin_money_api.repository;

import br.com.fiap.fin_money_api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
