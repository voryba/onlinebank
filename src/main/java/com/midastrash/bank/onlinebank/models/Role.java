package com.midastrash.bank.onlinebank.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private com.midastrash.bank.onlinebank.models.ERole name;

    public Role() {}
    public Role(com.midastrash.bank.onlinebank.models.ERole name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.midastrash.bank.onlinebank.models.ERole getName() {
        return name;
    }

    public void setName(com.midastrash.bank.onlinebank.models.ERole name) {
        this.name = name;
    }
}
