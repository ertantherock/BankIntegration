package com.eteration.simplebanking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private double amount;
    private String transactionId;

    @ManyToOne
    private Account account;

    public Transaction() {
    }

    public Transaction(double amount) {
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.transactionId = UUID.randomUUID().toString();
    }

    public Transaction(Account account, double amount, String approvalCode) {
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.transactionId = UUID.randomUUID().toString();
        this.account = account;
    }

    public Transaction(Account account, double amount) {
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.transactionId = UUID.randomUUID().toString();
        this.account = account;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
