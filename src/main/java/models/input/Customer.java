package models.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import utils.ConversionUtils;

import java.util.List;

/**
 * Cette classe permet de stocker l'information entrée sur le client par le fichier JSON entrant
 */
public class Customer {

    private String contractType;
    private String clientNumber;
    private String claimPeriod;
    private List<Claim> claimList;

    public Customer() {}

    public Customer(String fileNumber, String claimPeriod, List<Claim> claimList) {
        this.contractType = ConversionUtils.extractContractTypeFrom(fileNumber);
        this.clientNumber = ConversionUtils.extractClientNoFrom(fileNumber);
        this.claimPeriod = claimPeriod;
        this.claimList = claimList;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public String getContractType() {
        return contractType;
    }

    public String getClaimPeriod() {
        return claimPeriod;
    }

    public List<Claim> getClaimsList() {
        return claimList;
    }

    //Les propriétés JSON permettent de générer les informations entrantes dans les bons setters.
    @JsonProperty("dossier")
    public void setFileNumber(String fileNumber) {
        this.contractType = ConversionUtils.extractContractTypeFrom(fileNumber);
        this.clientNumber = ConversionUtils.extractClientNoFrom(fileNumber);
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    @JsonProperty("mois")
    public void setClaimPeriod(String claimPeriod) {
        this.claimPeriod = claimPeriod;
    }

    //C'est ici que l'on génère une liste d'objet claims contenant toutes les réclamations.
    @JsonProperty("reclamations")
    public void setClaimsList(List<Claim> claimList) {
        this.claimList = claimList;
    }
}
