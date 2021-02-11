package models.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Cette classe permet de représenter et de contenir l'ensemble des valeurs à écrire dans le parser sortant.
 */
public class CustomerOut {

    private String customer;
    private String customerClaimPeriod;
    private List<ClaimOut> claimOutList;

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setCustomerClaimPeriod(String customerClaimPeriod) {
        this.customerClaimPeriod = customerClaimPeriod;
    }

    public void setClaimsOutList(List<ClaimOut> claimOutList) {
        this.claimOutList = claimOutList;
    }

    //Instructions pour parser sortant
    @JsonProperty("client")
    public String getCustomer() {
        return customer;
    }

    @JsonProperty("mois")
    public String getCustomerClaimPeriod() {
        return customerClaimPeriod;
    }

    @JsonProperty("remboursements")
    public List<ClaimOut> getClaimsOutList() {
        return claimOutList;
    }
}

