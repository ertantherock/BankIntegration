package com.eteration.simplebanking.controller;

public class DebitResponse {
    private String transactionId;

    public DebitResponse(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
