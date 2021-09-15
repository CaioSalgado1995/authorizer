package br.com.caiosalgado.nubank.test.services;

import br.com.caiosalgado.nubank.test.models.Account;
import br.com.caiosalgado.nubank.test.models.TransactionOperation;

public class DoubleTransactionRule implements TransactionRule {

    public static final String ERROR_CODE = "double-transaction";

    @Override
    public void validate(Account account, TransactionOperation operation) {
        if(!account.getSuccessfulTransactions().isEmpty() &&
            account.getSuccessfulTransactions().contains(operation.getTransaction())) {
            throw new RuntimeException(DoubleTransactionRule.ERROR_CODE);
        }
    }
}
