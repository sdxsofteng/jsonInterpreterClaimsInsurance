import com.fasterxml.jackson.annotation.JsonProperty;

public class ContractTypeValue {

    String type;
    float maximumDeductibleAmount;
    float refundPercentage;

    public String getType() {
        return type;
    }
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public float getMaximumDeductibleAmount() {
        return maximumDeductibleAmount;
    }
    @JsonProperty("max")
    public void setMaximumDeductibleAmount(float maximumDeductibleAmount) {
        this.maximumDeductibleAmount = maximumDeductibleAmount;
    }

    public float getRefundPercentage() {
        return refundPercentage;
    }
    @JsonProperty("refundPercentage")
    public void setRefundPercentage(float refundPercentage) {
        this.refundPercentage = refundPercentage;
    }
}
