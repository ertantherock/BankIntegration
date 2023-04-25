package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.ResponseEntity;

class ControllerTests {

    @Spy
    @InjectMocks
    private AccountController controller;

    @Mock
    private AccountService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testWithdrawal_withSufficientBalance_shouldReturnJson() throws Exception {
        // given
        Account account = new Account("John Doe", "12345");
        account.setBalance(1500.0);
        doReturn(account).when(service).getBankAccount("12345");

        // when
        ResponseEntity<TransactionStatus> result = controller.withdraw("12345", new WithdrawalTransaction(account, 1000.0));

        // then
        verify(service, times(1)).getBankAccount("12345");
        assertEquals("OK", result.getBody().getStatus());
    }
}
