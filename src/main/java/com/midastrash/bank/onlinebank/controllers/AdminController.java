package com.midastrash.bank.onlinebank.controllers;

import com.midastrash.bank.onlinebank.models.Customer;
import com.midastrash.bank.onlinebank.models.ERole;
import com.midastrash.bank.onlinebank.models.Role;
import com.midastrash.bank.onlinebank.models.Transaction;
import com.midastrash.bank.onlinebank.pojo.SignupRequest;
import com.midastrash.bank.onlinebank.pojo.TransactionRequest;
import com.midastrash.bank.onlinebank.repository.RoleRepository;
import com.midastrash.bank.onlinebank.repository.TransactionRepository;
import com.midastrash.bank.onlinebank.repository.UserRepository;
import com.midastrash.bank.onlinebank.service.TransactionService;
import com.midastrash.bank.onlinebank.service.UserDetailsImpl;
import com.midastrash.bank.onlinebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @GetMapping("/addUser")
    public String getAddUserPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        model.addAttribute("user", new SignupRequest());
        model.addAttribute("username", userDetails.getFirst_name());
        return "adduser";
    }

    @PostMapping("/process_adduser")
    public String processAddUser(SignupRequest signupRequest){

        Customer user = new Customer(signupRequest.getFirst_name(), signupRequest.getLast_name(),
                signupRequest.getEmail(), signupRequest.getPhoneNumber(), passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getGender(), signupRequest.getDate_of_birth(), signupRequest.getNationality(),
                signupRequest.getAddress(), 0
        );

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository
                .findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
        roles.add(userRole);


        user.setRoles(roles);
        userRepository.save(user);
        return "admin";
    }

    @GetMapping("/addTransaction")
    public String getAddTransactionPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        model.addAttribute("transaction", new TransactionRequest());
        model.addAttribute("username", userDetails.getFirst_name());
        return "addtransaction";
    }

    @PostMapping("/process_addtransaction")
    public String processAddTransaction(TransactionRequest request){
        Transaction transaction = new Transaction(request.getFromUserName(), request.getTransactionType(),
                request.getAmount(), request.getToUserName(),
                                    request.getDescription());

        transactionRepository.save(transaction);

        return "admin";
    }

    @GetMapping("/report")
    public String getTransactions(Model model){
        List<Transaction> list = transactionService.getAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        model.addAttribute("username", userDetails.getFirst_name());
        model.addAttribute("transactions", list);

        return "report";
    }

    @GetMapping("/getall")
    public String getAll(Model model){
        List<Customer> list = userService.getAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        model.addAttribute("username", userDetails.getFirst_name());
        model.addAttribute("users", list);
        return "getall";
    }




}
