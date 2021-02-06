import com.fasterxml.jackson.annotation.JsonProperty;

public class Claims {

    private int treatmentNumber;
    private String claimDate;
    private String treatmentCost;
    private String refundAmount;

    public int getTreatmentNumber() {
        return treatmentNumber;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public String getTreatmentCost() {
        return treatmentCost;
    }

    public float getTreatmentCost(Claims claims) {
        return Conversion.convertCostStringToFloat(claims.getTreatmentCost());

    }

    public String getRefundAmount() {
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

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }
}
