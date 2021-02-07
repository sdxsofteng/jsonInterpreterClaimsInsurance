package models.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CustomerOut {

    private String customer;
    private String customerClaimPeriod;
    private List<ClaimsOut> claimsOutList;

    @JsonProperty("client")
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @JsonProperty("mois")
    public String getCustomerClaimPeriod() {
        return customerClaimPeriod;
    }

    public void setCustomerClaimPeriod(String customerClaimPeriod) {
        this.customerClaimPeriod = customerClaimPeriod;
    }

    @JsonProperty("remboursements")
    public List<ClaimsOut> getClaimsOutList() {
        return claimsOutList;
    }

    public void setClaimsOutList(List<ClaimsOut> claimsOutList) {
        this.claimsOutList = claimsOutList;
    }
}

