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
}
