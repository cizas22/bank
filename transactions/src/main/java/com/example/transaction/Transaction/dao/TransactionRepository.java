package com.example.transaction.Transaction.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByAccountNo(Long accountNo);
}
