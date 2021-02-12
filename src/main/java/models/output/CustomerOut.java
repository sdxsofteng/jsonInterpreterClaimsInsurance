package models.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Cette classe permet de représenter et de contenir l'ensemble des valeurs à écrire dans le parser sortant.
 */
public class CustomerOut {

    private String clientNumber;
    private String claimPeriod;
    private List<ClaimOut> claimOutList;

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void setClaimPeriod(String claimPeriod) {
        this.claimPeriod = claimPeriod;
    }

    public void setClaimsOutList(List<ClaimOut> claimOutList) {
        this.claimOutList = claimOutList;
    }

    //Instructions pour parser sortant
    @JsonProperty("client")
    public String getClientNumber() {
        return clientNumber;
    }

    @JsonProperty("mois")
    public String getClaimPeriod() {
        return claimPeriod;
    }

    @JsonProperty("remboursements")
    public List<ClaimOut> getClaimsOutList() {
        return claimOutList;
    }
}

