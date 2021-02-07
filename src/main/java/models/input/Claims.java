package models.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import utils.Conversion;

public class Claims {

    private int treatmentNumber;
    private String claimDate;
    private String treatmentCost;
    private float refundAmount;

    public int getTreatmentNumber() {
        return treatmentNumber;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public String getTreatmentCost() {
        return treatmentCost;
    }

    public float getTreatmentCostFloat(){
        return Conversion.convertCostStringToFloat(treatmentCost);
    }

    public float getRefundAmount() {
        return refundAmount;
    }

    @JsonProperty("soin")
    public void setTreatmentNumber(int treatmentNumber) {
        this.treatmentNumber = treatmentNumber;
    }

    @JsonProperty("date")
    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    @JsonProperty("montant")
    public void setTreatmentCost(String treatmentCost) {
        this.treatmentCost = treatmentCost;
    }

    public void setRefundAmount(float refundAmount) {
        this.refundAmount = refundAmount;
    }
}
