package models.analytics;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Analytics {
    private int nbRequestsRejected = 0;
    private int nbRequestsApproved = 0;
    private List<ClaimCount> declaredCares;

    public Analytics() {
        declaredCares = new ArrayList<>();
    }

    @JsonProperty("refuser")
    public int getNbRequestsRejected() {
        return nbRequestsRejected;
    }

    @JsonProperty("refuser")
    public void setNbRequestsRejected(int nbRequestsRejected) {
        this.nbRequestsRejected = nbRequestsRejected;
    }

    @JsonProperty("approuver")
    public int getNbRequestsApproved() {
        return nbRequestsApproved;
    }

    @JsonProperty("approuver")
    public void setNbRequestsApproved(int nbRequestsApproved) {
        this.nbRequestsApproved = nbRequestsApproved;
    }

    @JsonProperty("soins")
    public List<ClaimCount> getDeclaredCares() {
        return declaredCares;
    }

    @JsonProperty("soins")
    public void setDeclaredCares(List<ClaimCount> declaredCares) {
        this.declaredCares = declaredCares;
    }

    public String toString() {
        String string = "Requêtes approuvées: " + nbRequestsApproved + ";\n";
        string += "Requêtes rejetées: " + nbRequestsRejected + ";\nSoins traités:\n";
        if (declaredCares != null) {
            for (ClaimCount claim : declaredCares) {
                string += claim + "\n";
            }
        }
        return string;
    }
}
