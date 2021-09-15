package br.com.caiosalgado.nubank.test.services;

import br.com.caiosalgado.nubank.test.models.Account;
import br.com.caiosalgado.nubank.test.models.Transaction;
import br.com.caiosalgado.nubank.test.models.TransactionOperation;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DoubleTransactionRuleTest {
    private DoubleTransactionRule doubleTransactionRule = new DoubleTransactionRule();

    @Test
    public void shouldThrowErrorByDoubleTransaction() {
        try {
            int amount = 10;
            Account account = new Account(true, 100);
            account.execute(generateTxOperation(amount).getTransaction());
            doubleTransactionRule.validate(account, generateTxOperation(amount));
        } catch(RuntimeException ex) {
            assertEquals(DoubleTransactionRule.ERROR_CODE, ex.getMessage());
        }
    }

    @Test
    public void shouldNotThrowErrorByDoubleTransactionBecauseDoNotHaveAnyTransaction() {
        try {
            doubleTransactionRule.validate(new Account(true, 100), generateTxOperation(10));
        }catch (RuntimeException ex) {
            fail("Cannot throw any exception");
        }
    }

    @Test
    public void shouldNotThrowErrorByDoubleTransactionBecauseDoNotHaveAnyEqualTransaction() {
        try {
            int amount_1 = 100;
            int amount_2 = 10;
            Account account = new Account(true, 100);
            account.execute(generateTxOperation(amount_1).getTransaction());
            doubleTransactionRule.validate(account, generateTxOperation(amount_2));
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
