package com.midastrash.bank.onlinebank.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "email"),
            @UniqueConstraint(columnNames = "phone_number"),
        })
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "gender")
    private String gender;
    @Column(name = "date_of_birth")
    private Date date_of_birth;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "address")
    private String address;
    @Column(name = "balance")
    private int balance;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private List<Transaction> transactions;

    public Customer() {
    }
    public Customer(String first_name, String last_name, String email, String phoneNumber, String password,
                    String gender, Date date_of_birth, String nationality, String address, int balance) {
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
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone_number() {
        return phoneNumber;
    }
    public void setPhone_number(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
