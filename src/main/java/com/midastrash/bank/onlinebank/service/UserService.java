package com.midastrash.bank.onlinebank.service;

import com.midastrash.bank.onlinebank.models.Customer;
import com.midastrash.bank.onlinebank.models.Transaction;
import com.midastrash.bank.onlinebank.repository.TransactionRepository;
import com.midastrash.bank.onlinebank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    TransactionRepository transactionRepository;

    public boolean checkBalance(int balance){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        int curr = userDetails.getBalance();
        if(curr < balance){
            return false;
        }
        return true;
    }

    public boolean existsByPhoneNumber(String phoneNumber){
        return repository.existsByPhoneNumber(phoneNumber);
    }

    public boolean transfer(int amount, String phoneNumber){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.err.println(amount +" "+ userDetails.getPhoneNumber());
        repository.minusMoney(amount, userDetails.getPhoneNumber());
        repository.plusMoney(amount, phoneNumber);
//        long user_id, String fromUserName, String transactionType, int amount, String toUserName, String description
        Optional<Customer> customer = repository.findByPhoneNumber(phoneNumber);
        Transaction transaction = new Transaction(userDetails.getFirst_name() + " " + userDetails.getLast_name(),
                "transfer", amount, customer.get().getFirst_name()+ " " + customer.get().getLast_name(), "transfer money");

        transactionRepository.save(transaction);
        return true;
    }

    protected boolean checkPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return encoder.matches(password, userDetails.getPassword());
    }

    public List<Customer> getAll(){
        return repository.findAll();
    }

//    public boolean changePassword(ChangePasswordRequest request){
//        if(checkPassword(request.getCurrentPassword())){
//
//        }
//    }
}
