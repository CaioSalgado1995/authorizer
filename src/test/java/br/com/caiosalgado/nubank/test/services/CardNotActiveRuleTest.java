package br.com.caiosalgado.nubank.test.services;

import br.com.caiosalgado.nubank.test.models.Account;
import br.com.caiosalgado.nubank.test.models.Transaction;
import br.com.caiosalgado.nubank.test.models.TransactionOperation;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CardNotActiveRuleTest {

    private CardNotActiveRule cardNotActiveRule = new CardNotActiveRule();

    @Test
    public void shouldThrowErrorByCardNotActive() {
        try {
            cardNotActiveRule.validate(new Account(false, 100), generateTxOperation());
        } catch(RuntimeException ex) {
            assertEquals(CardNotActiveRule.ERROR_CODE, ex.getMessage());
        }
    }

    @Test
    public void shouldNotThrowErrorByCardNotActive() {
        try {
            cardNotActiveRule.validate(new Account(true, 100), generateTxOperation());
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
