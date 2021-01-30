public class Claims {

    private int treatmentNumber;
    private int claimDate;
    private float treatmentCost;
    private float refundAmount;

    public Claims(int treatmentNumber, int claimDate, float treatmentCost) {
        this.treatmentNumber = treatmentNumber;
        this.claimDate = claimDate;
        this.treatmentCost = treatmentCost;
    }

    public int getTreatmentNumber() {
        return treatmentNumber;
    }

    public int getClaimDate() {
        return claimDate;
    }

    public float getTreatmentCost() {
        return treatmentCost;
    }

    public float getRefundAmount() {
        return refundAmount;
    }

    public void setTreatmentNumber(int treatmentNumber) {
        this.treatmentNumber = treatmentNumber;
    }

    public void setClaimDate(int claimDate) {
        this.claimDate = claimDate;
    }

    public void setTreatmentCost(float treatmentCost) {
        this.treatmentCost = treatmentCost;
    }

    public void setRefundAmount(float refundAmount) {
        this.refundAmount = refundAmount;
    }
}
