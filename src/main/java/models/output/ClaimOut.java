package models.output;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Cette classe permet de représenter les claims sortants qui seront présent dans le JSON résultant.
 */
@JsonPropertyOrder({ "soin", "date", "montant" })
public class ClaimOut {

    private int treatmentNumber;
    private String claimDate;
    private String refundAmount;
    private float claimAmount;

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

    @JsonIgnore
    public float getClaimAmount() {
        return claimAmount;
    }

    @JsonIgnore
    public void setClaimAmount(float claimAmount) {
        this.claimAmount = claimAmount;
    }

    public ClaimOut(){}

    public ClaimOut(int careNumber, String claimDate, String refundAmount, float claimAmount){
        this.treatmentNumber = careNumber;
        this.claimDate = claimDate;
        this.refundAmount = refundAmount;
        this.claimAmount = claimAmount;
    }
}
