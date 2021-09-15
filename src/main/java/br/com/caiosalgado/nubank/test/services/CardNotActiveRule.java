package br.com.caiosalgado.nubank.test.services;

import br.com.caiosalgado.nubank.test.models.Account;
import br.com.caiosalgado.nubank.test.models.AccountInput;
import br.com.caiosalgado.nubank.test.models.TransactionOperation;

public class CardNotActiveRule implements TransactionRule {

    public static final String ERROR_CODE = "card-not-active";

    @Override
    public void validate(Account account, TransactionOperation operation) {
        if(! account.isActiveCard()) {
            throw new RuntimeException(CardNotActiveRule.ERROR_CODE);
        }
    }
}
