package com.eteration.simplebanking;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModelTest {
	@Test
	public void testDepositTransaction() {
		Account account = new Account("Jim", "12345");
		DepositTransaction deposit = new DepositTransaction(account, 1000.0);
		account.post(deposit);
		Assertions.assertEquals(1000.0, account.getBalance(), 0.001);
	}

	@Test
	public void testWithdrawalTransaction() throws InsufficientBalanceException {
		Account account = new Account("Jim", "12345");
		DepositTransaction deposit = new DepositTransaction(account, 1000.0);
		account.post(deposit);
		WithdrawalTransaction withdrawal = new WithdrawalTransaction(account, 200.0);
		account.post(withdrawal);
		Assertions.assertEquals(800.0, account.getBalance(), 0.001);
	}



}
