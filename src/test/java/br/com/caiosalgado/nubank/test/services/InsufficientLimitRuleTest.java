package br.com.caiosalgado.nubank.test.services;

import br.com.caiosalgado.nubank.test.models.Account;
import br.com.caiosalgado.nubank.test.models.Transaction;
import br.com.caiosalgado.nubank.test.models.TransactionOperation;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class InsufficientLimitRuleTest {

    private InsufficientLimitRule insufficientLimitRule = new InsufficientLimitRule();

    @Test
    public void shouldThrowErrorByInsufficientLimit() {
        try {
            insufficientLimitRule.validate(new Account(true, 10), generateTxOperation(20));
        } catch(RuntimeException ex) {
            assertEquals(InsufficientLimitRule.ERROR_CODE, ex.getMessage());
        }
    }

    @Test
    public void shouldNotThrowErrorByInsufficientLimit() {
        try {
            insufficientLimitRule.validate(new Account(true, 100), generateTxOperation(10));
        }catch (RuntimeException ex) {
            fail("Cannot throw any exception");
        }
    }

    private TransactionOperation generateTxOperation(int amount) {
        TransactionOperation transactionOperation = new TransactionOperation();
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setMerchant("Merchant");
        transaction.setTime(LocalDateTime.now());
        transactionOperation.setTransaction(transaction);
        return transactionOperation;
    }
}
