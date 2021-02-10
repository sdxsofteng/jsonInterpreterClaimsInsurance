package models.input;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Cette classe permet de storer les différentes valeurs pour chaque soins selon le type.
 *
 * Pour avoir un apperçu de la logique utilisée pour les objets de référence voir JavaDoc CareReference
 */
public class ContractTypeValue {

    String type;                //Variables d'objet
    float maxDeductibleAmount;
    float refundPercentage;

    public String getType() {
        return type;
    }

    public float getMaxDeductibleAmount() {
        return maxDeductibleAmount;
    }

    public float getRefundPercentage() {
        return refundPercentage;
    }

    //Permet de mettre les valeurs de max et de pourcentage selon le soins et le type depuis le JSON référence
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("max")
    public void setMaxDeductibleAmount(float maxDeductibleAmount) {
        this.maxDeductibleAmount = maxDeductibleAmount;
    }

    @JsonProperty("refundPercentage")
    public void setRefundPercentage(float refundPercentage) {
        this.refundPercentage = refundPercentage;
    }
}
