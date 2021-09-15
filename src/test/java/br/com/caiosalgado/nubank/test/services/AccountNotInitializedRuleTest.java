package br.com.caiosalgado.nubank.test.services;

import br.com.caiosalgado.nubank.test.models.Account;
import br.com.caiosalgado.nubank.test.models.Transaction;
import br.com.caiosalgado.nubank.test.models.TransactionOperation;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AccountNotInitializedRuleTest {

    private AccountNotInitializedRule accountNotInitializedRule = new AccountNotInitializedRule();

    @Test
    public void shouldThrowErrorByAccountNotInitialized() {
        try {
            accountNotInitializedRule.validate(null, generateTxOperation());
        } catch(RuntimeException ex) {
            assertEquals(AccountNotInitializedRule.ERROR_CODE, ex.getMessage());
        }
    }

    @Test
    public void shouldNotThrowErrorByAccountNotInitialized() {
        try {
            accountNotInitializedRule.validate(new Account(true, 100), generateTxOperation());
        }catch (RuntimeException ex) {
            fail("Cannot throw any exception");
        }
    }

    private TransactionOperation generateTxOperation() {
        TransactionOperation transactionOperation = new TransactionOperation();
        Transaction transaction = new Transaction();
        transaction.setAmount(1);
        transaction.setMerchant("Merchant");
        transaction.setTime(LocalDateTime.now());
        transactionOperation.setTransaction(transaction);
        return transactionOperation;
    }

}
