package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Account {
    private String owner;
    @Id
    private String accountNumber;
    private double balance;


    private Date createDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    //constructor

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public Account() {

    }

    public void credit(Transaction transaction) {
        balance += transaction.getAmount();
        transactions.add(transaction);
    }

    public void debit(Transaction transaction) throws InsufficientBalanceException {
        if (balance < transaction.getAmount()) {
            throw new InsufficientBalanceException();
        }
        balance -= transaction.getAmount();
        transactions.add(transaction);
    }


    public void post(Transaction transaction) {
        balance += transaction.getAmount();
        transactions.add(transaction);
    }
    //getters and setters

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void withdraw(double amount) {
    }
}
