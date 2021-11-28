package com.midastrash.bank.onlinebank.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private long user_id;
    @Column(name = "full_name")
    private String fromUserName;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "amount")
    private int amount;
    @Column(name = "route")
    private String toUserName;
    @Column(name = "description")
    private String description;
    @Column(name = "transaction_date")
    private Date date;

    public Transaction(String fromUserName, String transactionType, int amount, String toUserName, String description) {
        this.fromUserName = fromUserName;
        this.transactionType = transactionType;
        this.amount = amount;
        this.toUserName = toUserName;
        this.description = description;
        long millis=System.currentTimeMillis();
        this.date = new Date(millis);
    }

    public Transaction() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
