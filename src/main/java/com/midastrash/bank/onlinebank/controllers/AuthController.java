package com.midastrash.bank.onlinebank.controllers;


import com.midastrash.bank.onlinebank.models.Customer;
import com.midastrash.bank.onlinebank.models.ERole;
import com.midastrash.bank.onlinebank.models.Role;
import com.midastrash.bank.onlinebank.pojo.SignupRequest;
import com.midastrash.bank.onlinebank.repository.RoleRepository;
import com.midastrash.bank.onlinebank.repository.UserRepository;
import com.midastrash.bank.onlinebank.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
//@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("username", user.getFirst_name());
        return "admin";
    }

    @GetMapping("/success")
    public String getSuccessPage(){
        return "success";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("user", new SignupRequest());
        return "register";
    }

    @PostMapping("/process_register")
    public String processRegistration(SignupRequest signupRequest) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date = format.parse(signupRequest.getDate_of_birth());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

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
        return "register_success";
    }

    @GetMapping("/user")
    public String getUserPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("username", user.getFirst_name());
        return "user";
    }


}
