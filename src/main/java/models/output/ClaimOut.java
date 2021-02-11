package models.output;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Cette classe permet de représenter les claims sortants qui seront présent dans le JSON résultant.
 */
public class ClaimOut {

    private int treatmentNumber;
    private String claimDate;
    private String refundAmount;

    // L'annotation permet au parser de mettre information dans le JSON
    @JsonProperty("soin")
    public int getTreatmentNumber() {
        return treatmentNumber;
    }

    @JsonProperty("date")
    public String getClaimDate() {
        return claimDate;
    }

    @JsonProperty("montant")
    public String getRefundAmount() {
        return refundAmount;
    }

    public ClaimOut(){}

    public ClaimOut(int careNumber, String claimDate, String refundAmount){
        this.treatmentNumber = careNumber;
        this.claimDate = claimDate;
        this.refundAmount = refundAmount;
    }
}
