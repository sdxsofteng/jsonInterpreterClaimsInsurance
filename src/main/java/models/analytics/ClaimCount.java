package models.analytics;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClaimCount {
    private String name;
    private int amount;

    public ClaimCount() { }

    public ClaimCount(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    @JsonProperty("noSoin")
    public String getCareName() {
        return name;
    }

    @JsonProperty("noSoin")
    public void setCareName(String name) {
        this.name = name;
    }

    @JsonProperty("nbFois")
    public int getAmount() {
        return amount;
    }

    @JsonProperty("nbFois")
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String toString() {
        return this.name + ": " + this.amount;
    }
}
