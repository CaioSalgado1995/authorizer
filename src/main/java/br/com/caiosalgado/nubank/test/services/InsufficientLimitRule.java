package br.com.caiosalgado.nubank.test.services;

import br.com.caiosalgado.nubank.test.models.Account;
import br.com.caiosalgado.nubank.test.models.AccountInput;
import br.com.caiosalgado.nubank.test.models.TransactionOperation;

public class InsufficientLimitRule implements TransactionRule {

    public static final String ERROR_CODE = "insufficient-limit";

    @Override
    public void validate(Account account, TransactionOperation operation) {
        if(operation.getTransaction().getAmount() > account.getAvailableLimit()) {
            throw new RuntimeException(InsufficientLimitRule.ERROR_CODE);
        }
    }
}
