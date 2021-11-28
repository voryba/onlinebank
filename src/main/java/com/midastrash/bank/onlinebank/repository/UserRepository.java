package com.midastrash.bank.onlinebank.repository;


import com.midastrash.bank.onlinebank.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhoneNumber(String phoneNumber);
    Boolean existsByPhoneNumber(String phoneNumber);
    Boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Customer u set u.balance = u.balance - :amount where u.phoneNumber = :phone")
    void minusMoney(@Param("amount") int amount, @Param("phone") String phone);

    @Modifying
    @Transactional
    @Query("update Customer u set u.balance = u.balance + :amount where u.phoneNumber = :phoneNumber")
    void plusMoney(@Param("amount") int amount, @Param("phoneNumber") String phoneNumber);

}
