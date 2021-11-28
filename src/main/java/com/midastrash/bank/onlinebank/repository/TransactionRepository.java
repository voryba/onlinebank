package com.midastrash.bank.onlinebank.repository;

import com.midastrash.bank.onlinebank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
