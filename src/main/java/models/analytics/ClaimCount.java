package models.analytics;

import com.fasterxml.jackson.annotation.JsonProperty;

import models.output.ClaimOut;

import utils.Money;

public class ClaimCount {

    private String name;
    private int nbClaims;
    private float totalClaimedAmount = 0;
    private float highestClaimValue = 0;

    public ClaimCount() { }

    // Une nouvelle instance signifie que nous avons eu un soin de trouvé, donc amount aura 1 par défaut.
    public ClaimCount(String name, float claimAmount) {
        this.name = name;
        this.nbClaims = 1;
        this.highestClaimValue = claimAmount;
        this.totalClaimedAmount = claimAmount;
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
    public int getNbClaims() {
        return nbClaims;
    }

    @JsonProperty("nbFois")
    public void setNbClaims(int nbClaims) {
        this.nbClaims = nbClaims;
    }

    public String toString() {
        float average = (this.totalClaimedAmount * 100 / this.nbClaims) / 100;
        return "\t"  + this.name + ": " + this.nbClaims
                + " (Montant max d'un soin: " + String.format("%.2f", this.highestClaimValue)
                + "$ Moyenne des soins:" + String.format("%.2f", average) + "$)";
    }

    @JsonProperty("totalClaimedAmount")
    public float getTotalClaimedAmount() {
        return totalClaimedAmount;
    }

    @JsonProperty("totalClaimedAmount")
    public void setTotalClaimedAmount(float totalClaimedAmount) {
        this.totalClaimedAmount = totalClaimedAmount;
    }

    @JsonProperty("highestClaimValue")
    public float getHighestClaimValue() {
        return highestClaimValue;
    }

    @JsonProperty("highestClaimValue")
    public void setHighestClaimValue(float highestClaimValue) {
        this.highestClaimValue = highestClaimValue;
    }

    public void trackClaim(ClaimOut claim) {
        this.nbClaims++;
        this.totalClaimedAmount = new Money(this.totalClaimedAmount).add(claim.getClaimAmount());
        if (this.highestClaimValue < claim.getClaimAmount()) {
            this.highestClaimValue = claim.getClaimAmount();
        }
    }

}
