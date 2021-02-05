import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Customer {

    private String clientNumber;
    private String contractLetter;
    private String claimPeriod;

    private List<Claims> claimsList;

    public String getClientNumber() {
        return clientNumber;
    }

    public String getContractLetter() {
        return contractLetter;
    }

    public String getClaimPeriod() {
        return claimPeriod;
    }

    public List<Claims> getClaimsList() {
        return claimsList;
    }
    @JsonProperty("client")
    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    @JsonProperty("contrat")
    public void setContractLetter(String contractLetter) {
        this.contractLetter = contractLetter;
    }
    @JsonProperty("mois")
    public void setClaimPeriod(String claimPeriod) {
        this.claimPeriod = claimPeriod;
    }
    @JsonProperty("reclamations")
    public void setClaimsList(List<Claims> claimsList) {
        this.claimsList = claimsList;
    }
}
