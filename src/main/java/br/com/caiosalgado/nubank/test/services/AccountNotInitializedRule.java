package br.com.caiosalgado.nubank.test.services;

import br.com.caiosalgado.nubank.test.models.Account;
import br.com.caiosalgado.nubank.test.models.TransactionOperation;

public class AccountNotInitializedRule implements TransactionRule {

    public static final String ERROR_CODE = "account-not-initialized";

    @Override
    public void validate(Account account, TransactionOperation operation) {
        if(account == null) {
            throw new RuntimeException(AccountNotInitializedRule.ERROR_CODE);
        }
    }
}
