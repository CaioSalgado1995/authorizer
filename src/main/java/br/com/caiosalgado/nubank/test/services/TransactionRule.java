package br.com.caiosalgado.nubank.test.services;

import br.com.caiosalgado.nubank.test.models.Account;
import br.com.caiosalgado.nubank.test.models.AccountInput;
import br.com.caiosalgado.nubank.test.models.TransactionOperation;

public interface TransactionRule {

    void validate(Account account, TransactionOperation operation);

}
