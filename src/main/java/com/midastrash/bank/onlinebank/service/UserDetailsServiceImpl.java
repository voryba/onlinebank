package com.midastrash.bank.onlinebank.service;


import com.midastrash.bank.onlinebank.models.Customer;
import com.midastrash.bank.onlinebank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Customer user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with phone number: " + phoneNumber));

        return UserDetailsImpl.build(user);
    }
}
