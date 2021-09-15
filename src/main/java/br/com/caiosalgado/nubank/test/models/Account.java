package br.com.caiosalgado.nubank.test.models;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final boolean activeCard;

    private int availableLimit;

    private List<Transaction> successfulTransactions;

    public Account(boolean activeCard, int availableLimit) {
        this.activeCard = activeCard;
        this.availableLimit = availableLimit;
        this.successfulTransactions = new ArrayList<>();
    }

    public void execute(Transaction transaction) {
        this.availableLimit -= transaction.getAmount();
        this.successfulTransactions.add(transaction);
    }

    public boolean isActiveCard() {
        return activeCard;
    }

    public int getAvailableLimit() {
        return availableLimit;
    }

    public List<Transaction> getSuccessfulTransactions() {
        return successfulTransactions;
    }
}
