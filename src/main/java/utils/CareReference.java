package utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import models.input.Cares;

import java.util.List;

public class CareReference {
    List<Cares> caresList;
    List<String> contractTypesList;

    public List<Cares> getCaresList() {
        return caresList;
    }

    @JsonProperty("cares")
    public void setCaresList(List<Cares> caresList) {
        this.caresList = caresList;
    }

    public List<String> getContractTypesList() { return contractTypesList; }

    @JsonProperty("contractTypes")
    public void setContractTypesList(List<String> contractTypesList) {
        this.contractTypesList = contractTypesList;
    }

    public Cares getAppropriateCareObject(int referenceNumber){
        Cares returnedObject = null;
        for (Cares cares: caresList){
            if (cares.getCareNumberMax() >= referenceNumber
                    && cares.getCareNumberMin() <= referenceNumber){
                returnedObject = cares;
            }
        }
        return returnedObject;
    }
}
