package models.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContractTypeValue {

    String type;
    float maxDeductibleAmount;
    float refundPercentage;

    public String getType() {
        return type;
    }
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public float getMaxDeductibleAmount() {
        return maxDeductibleAmount;
    }

    @JsonProperty("max")
    public void setMaxDeductibleAmount(float maxDeductibleAmount) {
        this.maxDeductibleAmount = maxDeductibleAmount;
    }

    public float getRefundPercentage() {
        return refundPercentage;
    }
    @JsonProperty("refundPercentage")
    public void setRefundPercentage(float refundPercentage) {
        this.refundPercentage = refundPercentage;
    }
}
