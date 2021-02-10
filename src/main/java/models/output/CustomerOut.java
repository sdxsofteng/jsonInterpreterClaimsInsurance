package models.output;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
/**
 * Cette classe permet de garder les valeurs à écrire dans le parser sortant.
 */
public class CustomerOut {

    private String customer;
    private String customerClaimPeriod;
    private List<ClaimsOut> claimsOutList;

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setCustomerClaimPeriod(String customerClaimPeriod) {
        this.customerClaimPeriod = customerClaimPeriod;
    }

    public void setClaimsOutList(List<ClaimsOut> claimsOutList) {
        this.claimsOutList = claimsOutList;
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
    public List<ClaimsOut> getClaimsOutList() {
        return claimsOutList;
    }





}

