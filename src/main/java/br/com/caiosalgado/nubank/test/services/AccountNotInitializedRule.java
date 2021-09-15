package br.com.caiosalgado.nubank.test.services;

import br.com.caiosalgado.nubank.test.models.Account;
import br.com.caiosalgado.nubank.test.models.TransactionOperation;

public class AccountNotInitializedRule implements TransactionRule {

    @Override
    public void validate(Account account, TransactionOperation operation) {
        if(account == null) {
            throw new RuntimeException("account-not-initialized");
        }
    }
}
