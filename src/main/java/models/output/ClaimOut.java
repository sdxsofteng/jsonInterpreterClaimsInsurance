package models.output;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Cette classe permet de représenter les claims sortants qui seront présent dans le JSON résultant.
 */
public class ClaimOut {

    private int careNumber;
    private String date;
    private String refundAmount;

    // L'annotation permet au parser de mettre information dans le JSON
    @JsonProperty("soin")
    private int getCareNumber() {
        return careNumber;
    }

    @JsonProperty("date")
    private String getDate() {
        return date;
    }

    @JsonProperty("montant")
    private String getRefundAmount() {
        return refundAmount;
    }

    public ClaimOut(){}

    public ClaimOut(int careNumber, String date, String refundAmount){
        this.careNumber = careNumber;
        this.date = date;
        this.refundAmount = refundAmount;
    }
}
