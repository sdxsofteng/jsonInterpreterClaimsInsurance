import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class ClaimsOut {

    private int careNumber;
    private String date;
    private String refundAmount;

    @JsonProperty("soin")
    public int getCareNumber() {
        return careNumber;
    }

    public void setCareNumber(int careNumber) {
        this.careNumber = careNumber;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("montant")
    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    ClaimsOut(){}

    ClaimsOut(int careNumber, String date, String refundAmount){
        this.careNumber = careNumber;
        this.date = date;
        this.refundAmount = refundAmount;
    }
}
