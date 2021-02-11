package models.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Cette classe crée des objets soins(Cares) depuis le fichier JSON de référence et contient leurs attributs ainsi
 * qu'une liste des valeur de pourcentage et de max possible selon le type(dans la liste ContractTypeValues).
 *
 * Pour avoir un aperçu de la logique utilisée pour les objets de référence voir JavaDoc CareReference
 */
public class CaresValues {

    String careName;        //Variables d'objet
    int careNumberMax;
    int careNumberMin;
    private List<ContractTypeValue> contractTypeValues;

    //Cette méthode va chercher le montant maximum du remboursement selon le type du contrat du client
    public float getAppropriateMaxAmount(String contractType){
        ContractTypeValue verifiedContractTypeObject = getContractTypeObject(contractType);
        return verifiedContractTypeObject.getMaxDeductibleAmount();
    }

    //Même chose que la méthode ci-haut pour le pourcentage
    public float getAppropriateRefundPercentage(String contractType){
        ContractTypeValue verifiedContractTypeObject = getContractTypeObject(contractType);
        return verifiedContractTypeObject.getRefundPercentage();
    }

    //Cette méthode retourne l'objet ContractTypeValue associé à la lettre fournie
    private ContractTypeValue getContractTypeObject(String contractType){
        ContractTypeValue contractTypeReturned = null;
        for (ContractTypeValue contractTypesObject: contractTypeValues){
            if(contractTypesObject.getType().equals(contractType)){
                contractTypeReturned = contractTypesObject;
            }
        }
        return contractTypeReturned;
    }

    public String getCareName() {
        return careName;
    }

    public int getCareNumberMax() {
        return careNumberMax;
    }

    public int getCareNumberMin() {
        return careNumberMin;
    }

    public List<ContractTypeValue> getContractTypeValues() {
        return contractTypeValues;
    }

    //Permet de setter les propriétés de chaque soin selon le JSON référence
    @JsonProperty("name")
    private void setCareName(String careName) {
        this.careName = careName;
    }

    @JsonProperty("careNumberMax")
    private void setCareNumberMax(int careNumberMax) {
        this.careNumberMax = careNumberMax;
    }

    @JsonProperty("careNumberMin")
    private void setCareNumberMin(int careNumberMin) {
        this.careNumberMin = careNumberMin;
    }

    //Va créer des sous objets contractTypes contenant les maximums et les pourcentage selon le type du soin.
    @JsonProperty("contractType")
    private void setContractTypeValues(List<ContractTypeValue> contractTypeValues) {
        this.contractTypeValues = contractTypeValues;
    }
}
