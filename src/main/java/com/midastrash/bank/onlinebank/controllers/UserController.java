package com.midastrash.bank.onlinebank.controllers;

import com.midastrash.bank.onlinebank.models.Transaction;
import com.midastrash.bank.onlinebank.pojo.TransferRequest;
import com.midastrash.bank.onlinebank.service.TransactionService;
import com.midastrash.bank.onlinebank.service.UserDetailsImpl;
import com.midastrash.bank.onlinebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @GetMapping("/balance")
    public String getBalancePage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        model.addAttribute("username", userDetails.getFirst_name());
        model.addAttribute("balance", userDetails.getBalance());
        return "balance";
    }

    @GetMapping("/transfer")
    public String getTransferPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        model.addAttribute("username", userDetails.getFirst_name());
        model.addAttribute("transfer", new TransferRequest());

        return "transfer";
    }

    @PostMapping("/transfer_process")
    public String transferProcess(TransferRequest transferRequest){
        if(!userService.checkBalance(transferRequest.getAmount())){
            return "exception";
        }
        if(!userService.existsByPhoneNumber(transferRequest.getRoute())){
            return "exception";
        }
        userService.transfer(transferRequest.getAmount(), transferRequest.getRoute());
        return "user";
    }

    @GetMapping("/report")
    public String getTransactions(Model model){
        List<Transaction> list = transactionService.getAll();
        model.addAttribute("transactions", list);
        return "report";
    }

    @GetMapping("/change_password")
    public String getPasswordPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        model.addAttribute("username", userDetails.getFirst_name());
        model.addAttribute("transfer", new TransferRequest());
        return "change_password_process";
    }

//    @PostMapping("/change_password_process")
//    public String passwordChangeProcess(ChangePasswordRequest request){
//
//    }
}
