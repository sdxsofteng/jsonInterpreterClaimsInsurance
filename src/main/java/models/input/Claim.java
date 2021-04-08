package models.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import utils.ConversionUtils;

/**
 * Cette classe permet de représenter les différentes réclamations sous forme d'objets. Les objets de cette classe
 * seront dans un liste à l'intérieur de l'objet Customer.
 */
public class Claim {

    private int treatmentNumber;
    private String claimDate;
    private String treatmentCost;
    private float refundAmount;

    public Claim() {}

    public Claim(int treatmentNumber, String claimDate, String treatmentCost) {
        this.treatmentNumber = treatmentNumber;
        this.claimDate = claimDate;
        this.treatmentCost = treatmentCost;
    }

    public int getTreatmentNumber() {
        return treatmentNumber;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public String getTreatmentCost() {
        return treatmentCost;
    }

    //Transfert le montant dans TreatmentCost(String) en float pour effectuer le calcul du remboursement
    public float getTreatmentCostFloat(){
        return ConversionUtils.convertCostStringToFloat(treatmentCost);
    }

    public float getRefundAmount() {
        return refundAmount;
    }

    //Ces trois setteurs permettent de mettre l'information entrante approriée dans chaque Claims.
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

    //Permet de setter le remboursement après calcul du remboursement selon la réclamation.
    public void setRefundAmount(float refundAmount) {
        this.refundAmount = refundAmount;
    }

}
