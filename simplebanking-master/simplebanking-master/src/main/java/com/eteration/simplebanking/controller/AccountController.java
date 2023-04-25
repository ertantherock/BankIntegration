package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This class is a place holder you can change the complete implementation


    @RestController
    @RequestMapping("/account/v1")
    public class AccountController {
        private final AccountService accountService;

        public AccountController(AccountService accountService) {
            this.accountService = accountService;
        }

        @PostMapping("/credit/{accountNumber}")
        public ResponseEntity<CreditTransaction> credit(@PathVariable String accountNumber, @RequestBody CreditRequest creditRequest) {
            CreditTransaction creditTransaction = accountService.credit(accountNumber, creditRequest.getAmount());
            return ResponseEntity.ok(creditTransaction);
        }

        @PostMapping("/debit/{accountNumber}")
        public ResponseEntity<DebitResponse> debit(@PathVariable String accountNumber, @RequestBody DebitRequest debitRequest) {
            DebitTransaction transaction = accountService.debit(accountNumber, debitRequest.getAmount());
            DebitResponse debitResponse = new DebitResponse(transaction.getTransactionId());
            return ResponseEntity.ok(debitResponse);
        }

        @GetMapping("/{accountNumber}")
        public ResponseEntity<Account> getBankAccount(@PathVariable String accountNumber) {
            Account bankAccount = AccountService.getBankAccount(accountNumber);
            return ResponseEntity.ok(bankAccount);
        }

        @PostMapping("/{accountId}/withdraw")
        public ResponseEntity<TransactionStatus> withdraw(@PathVariable String accountId, @RequestBody WithdrawalTransaction transaction) throws InsufficientBalanceException {
            Account account = accountService.getBankAccount(accountId);
            account.withdraw(transaction.getAmount());
            return ResponseEntity.ok(new TransactionStatus("OK"));
        }
    }