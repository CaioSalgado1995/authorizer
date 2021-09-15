package br.com.caiosalgado.nubank.test.services;

import br.com.caiosalgado.nubank.test.models.Account;
import br.com.caiosalgado.nubank.test.models.TransactionOperation;

public class DoubleTransactionRule implements TransactionRule {

    @Override
    public void validate(Account account, TransactionOperation operation) {
        if(account.getSuccessfulTransactions().contains(operation.getTransaction())) {
            throw new RuntimeException("double-transaction");
        }
    }
}
