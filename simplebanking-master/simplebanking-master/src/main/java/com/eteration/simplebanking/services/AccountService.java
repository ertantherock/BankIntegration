package com.eteration.simplebanking.services;


import com.eteration.simplebanking.controller.CreditResponse;
import com.eteration.simplebanking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

// This class is a place holder you can change the complete implementation
@Service
public class AccountService {
    @Autowired
    private static BankAccountRepository bankAccountRepository;

    public Account createBankAccount(Account bankAccount) {
        bankAccount.setCreateDate(new Date());
        return bankAccountRepository.save(bankAccount);
    }

    public static Account getBankAccount(String accountNumber) {
        return bankAccountRepository.findById(accountNumber).orElse(null);
    }

    public List<Account> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public Transaction deposit(String accountNumber, double amount) {
        Account account = bankAccountRepository.findById(accountNumber).orElse(null);
        if (account == null) {
            throw new RuntimeException("Bank account not found");
        }
        account.setBalance(account.getBalance() + amount);
        Transaction transaction = new DepositTransaction(account, amount);
        account.getTransactions().add(transaction);
        bankAccountRepository.save(account);
        return transaction;
    }

    public Transaction withdraw(String accountNumber, double amount) {
        Account account = bankAccountRepository.findById(accountNumber).orElse(null);
        if (account == null) {
            throw new RuntimeException("Bank account not found");
        }
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        Transaction transaction = new WithdrawalTransaction(account, amount);
        account.getTransactions().add(transaction);
        bankAccountRepository.save(account);
        return transaction;
    }

    public CreditTransaction credit(String accountNumber, double amount) {
        Account account = bankAccountRepository.findById(accountNumber).orElse(null);
        if (account == null) {
            throw new RuntimeException("Bank account not found");
        }
        account.setBalance(account.getBalance() + amount);
        CreditTransaction transaction = new CreditTransaction(account, amount, UUID.randomUUID().toString());
        account.getTransactions().add(transaction);
        bankAccountRepository.save(account);
        return transaction;
    }

    public DebitTransaction debit(String accountNumber, double amount) {
        Account account = bankAccountRepository.findById(accountNumber).orElse(null);
        if (account == null) {
            throw new RuntimeException("Bank account not found");
        }
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        DebitTransaction transaction = new DebitTransaction(account, amount);
        account.getTransactions().add(transaction);
        bankAccountRepository.save(account);
        return transaction;
    }
}