package com.eteration.simplebanking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DebitTransaction extends Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public DebitTransaction(double amount) {
        // default constructor
    }

    public DebitTransaction(Account account, double amount) {
        super(account, amount);
    }

    public String getType() {
        return "DebitTransaction";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
