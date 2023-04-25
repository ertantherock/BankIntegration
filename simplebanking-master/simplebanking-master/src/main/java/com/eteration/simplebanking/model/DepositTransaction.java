package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation
public class DepositTransaction extends Transaction {
    private final Account account;


    public DepositTransaction(Account account, double amount) {
        super(amount);
        this.account = account;
    }


    public void execute() {
        CreditTransaction transaction = new CreditTransaction(getAmount());
        account.credit(transaction);
    }


}