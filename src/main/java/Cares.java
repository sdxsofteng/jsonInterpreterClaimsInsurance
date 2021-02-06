import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Cares {

    String careName;
    int careNumberMax;
    int careNumberMin;
    private List<ContractTypeValue> contractTypeValues;

    public float getAppropriateMaxAmount(String contractType){
        ContractTypeValue verifiedContractTypeObject = getContractTypeObject(contractType);
        return verifiedContractTypeObject.getMaxDeductibleAmount();
    }

    public float getAppropriateRefundPercentage(String contractType){
        ContractTypeValue verifiedContractTypeObject = getContractTypeObject(contractType);
        return verifiedContractTypeObject.getRefundPercentage();
    }

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

    @JsonProperty("name")
    public void setCareName(String careName) {
        this.careName = careName;
    }

    public int getCareNumberMax() {
        return careNumberMax;
    }
    @JsonProperty("careNumberMax")

    public void setCareNumberMax(int careNumberMax) {
        this.careNumberMax = careNumberMax;
    }

    public int getCareNumberMin() {
        return careNumberMin;
    }
    @JsonProperty("careNumberMin")

    public void setCareNumberMin(int careNumberMin) {
        this.careNumberMin = careNumberMin;
    }

    public List<ContractTypeValue> getContractTypeValues() {
        return contractTypeValues;
    }

    @JsonProperty("contractType")
    public void setContractTypeValues(List<ContractTypeValue> contractTypeValues) {
        this.contractTypeValues = contractTypeValues;
    }
}
