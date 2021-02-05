import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CareReference {

    List<Cares> caresList;

    public List<Cares> getCaresList() {
        return caresList;
    }

    @JsonProperty("cares")
    public void setCaresList(List<Cares> caresList) {
        this.caresList = caresList;
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
