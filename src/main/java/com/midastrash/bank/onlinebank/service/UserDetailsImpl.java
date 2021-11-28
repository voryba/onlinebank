package com.midastrash.bank.onlinebank.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.midastrash.bank.onlinebank.models.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String phoneNumber;
    @JsonIgnore
    private String password;
    private String gender;
    private Date date_of_birth;
    private String nationality;
    private String address;
    private int balance;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String first_name, String last_name,String email,
                           String phoneNumber, String password, String gender,
                           Date date_of_birth, String nationality, String address,
                           int balance,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.nationality = nationality;
        this.address = address;
        this.balance = balance;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Customer user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPhone_number(),
                user.getPassword(),
                user.getGender(),
                user.getDate_of_birth(),
                user.getNationality(),
                user.getAddress(),
                user.getBalance(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    public long getId() {
        return id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public String getEmail() {
        return email;
    }
    public String getGender() {
        return gender;
    }
    public Date getDate_of_birth() {
        return date_of_birth;
    }
    public String getNationality() {
        return nationality;
    }
    public String getAddress() {
        return address;
    }
    public int getBalance() {
        return balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
