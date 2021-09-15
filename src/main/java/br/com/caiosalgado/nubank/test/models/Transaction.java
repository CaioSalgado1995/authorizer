package br.com.caiosalgado.nubank.test.models;

import java.time.LocalDateTime;

public class Transaction {

    private String merchant;

    private int amount;

    private LocalDateTime time;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    @Override
    public boolean equals(Object obj) {
        Transaction t2 = (Transaction) obj;
        return t2.getAmount() == this.getAmount() && t2.getMerchant().equals(this.getMerchant());
    }

    @Override
    public String toString() {
        return "[Amount=" + this.amount + "] - [Merchant=" + this.merchant + "] - [Time=" + this.time + "]";
    }
}
