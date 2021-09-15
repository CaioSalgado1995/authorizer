package br.com.caiosalgado.nubank.test.models;

import com.google.gson.annotations.SerializedName;

public class AccountInput {

    @SerializedName(value = "active-card")
    private boolean activeCard;

    @SerializedName(value = "available-limit")
    private int availableLimit;

    public int getAvailableLimit() {
        return availableLimit;
    }

    public void setAvailableLimit(int availableLimit) {
        this.availableLimit = availableLimit;
    }

    public boolean isActiveCard() {
        return activeCard;
    }

    public void setActiveCard(boolean activeCard) {
        this.activeCard = activeCard;
    }
}
