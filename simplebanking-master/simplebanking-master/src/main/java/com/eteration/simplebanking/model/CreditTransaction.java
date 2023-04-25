package com.eteration.simplebanking.model;



import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CreditTransaction extends Transaction {
    @Id
    private String transactionId;

    public CreditTransaction(double amount) {}

    public CreditTransaction(Account account, double amount, String transactionId) {
        super(account, amount);
        this.transactionId = transactionId;
    }

    public CreditTransaction(Account account, double amount) {
        super(account, amount);
    }


    public String getType() {
        return "CreditTransaction";
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
