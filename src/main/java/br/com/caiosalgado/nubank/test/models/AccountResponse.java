package br.com.caiosalgado.nubank.test.models;

import com.google.gson.Gson;

import java.util.List;

public class AccountResponse {

    private AccountInput account;

    private List<String> violations;

    public AccountResponse(Account account, List<String> violations) {
        AccountInput accountInput = new AccountInput();
        if(account != null) {
            accountInput.setActiveCard(account.isActiveCard());
            accountInput.setAvailableLimit(account.getAvailableLimit());
        }
        this.setAccount(accountInput);
        this.setViolations(violations);
    }

    public AccountInput getAccount() {
        return account;
    }

    public void setAccount(AccountInput account) {
        this.account = account;
    }

    public List<String> getViolations() {
        return violations;
    }

    public void setViolations(List<String> violations) {
        this.violations = violations;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
