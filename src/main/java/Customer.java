import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Customer {

    private int clientNumber;
    private char contractLetter;
    private int claimPeriod;

    public Customer(int clientNumber, char contractLetter, int claimPeriod) {
        this.clientNumber = clientNumber;
        this.contractLetter = contractLetter;
        this.claimPeriod = claimPeriod;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public char getContractLetter() {
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

    public void setContractLetter(char contractLetter) {
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
