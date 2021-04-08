package models.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import utils.ConversionUtils;

import java.util.List;

/**
 * Cette classe permet de représenter et de contenir l'ensemble des valeurs à écrire dans le parser sortant.
 */
@JsonPropertyOrder({ "dossier", "mois", "remboursements", "total" })
public class CustomerOut {

    private String clientNumber;
    private String contractType;
    private String claimPeriod;
    private List<ClaimOut> claimOutList;

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public void setClaimPeriod(String claimPeriod) {
        this.claimPeriod = claimPeriod;
    }

    public void setClaimsOutList(List<ClaimOut> claimOutList) {
        this.claimOutList = claimOutList;
    }

    //Instructions pour parser sortant

    @JsonProperty("dossier")
    public String getFileNumber() {
        return contractType + clientNumber;
    }

    @JsonProperty("mois")
    public String getClaimPeriod() {
        return claimPeriod;
    }

    @JsonProperty("remboursements")
    public List<ClaimOut> getClaimsOutList() {
        return claimOutList;
    }

    @JsonProperty("total")
    public String getFormattedTotalRefundAmount() {
        float totalRefundAmount = 0;
        if (claimOutList != null) {
            for (ClaimOut claim : claimOutList) {
                totalRefundAmount += ConversionUtils.convertCostStringToFloat(claim.getRefundAmount());
            }
        }
        String formattedTotalRefundAmount = ConversionUtils.floatToString(totalRefundAmount);
        return formattedTotalRefundAmount;
    }

}

