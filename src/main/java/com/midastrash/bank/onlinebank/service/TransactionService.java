package com.midastrash.bank.onlinebank.service;

import com.midastrash.bank.onlinebank.models.Transaction;
import com.midastrash.bank.onlinebank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository repository;

    public List<Transaction> getAll(){
        return repository.findAll();
    }
}
