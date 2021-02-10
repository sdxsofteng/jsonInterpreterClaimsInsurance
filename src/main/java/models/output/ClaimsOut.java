package models.output;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Cette classe permet de storer les claims sortant qui iront dans le JSON
 */
public class ClaimsOut {

    private int careNumber;
    private String date;
    private String refundAmount;


    //Permet au parser de mettre information dans le JSON
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

    public ClaimsOut(){}

    public ClaimsOut(int careNumber, String date, String refundAmount){
        this.careNumber = careNumber;
        this.date = date;
        this.refundAmount = refundAmount;
    }
}
