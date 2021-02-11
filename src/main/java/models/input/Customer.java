package models.input;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/**
 * Cette classe permet de stocker l'information entrée sur le client par le fichier JSON entrant
 */
public class Customer {

    private String clientNumber;      //Variables d'objet
    private String contractType;
    private String claimPeriod;
    private List<Claim> claimList;  //Liste de claims contenant toutes les réclamations du client. Voir classe Claims.

    public Customer() {}

    public Customer(String clientNumber, String contractType, String claimPeriod, List<Claim> claimList) {
        this.clientNumber = clientNumber;
        this.contractType = contractType;
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

    @JsonProperty("client")//Les propriétés JSON permettent de générer les informations entrantes dans les bons setters.
    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    @JsonProperty("contrat")
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    @JsonProperty("mois")
    public void setClaimPeriod(String claimPeriod) {
        this.claimPeriod = claimPeriod;
    }

    @JsonProperty("reclamations")//C'est ici que l'on génére une liste d'objet claims contenant toutes les réclamations.
    public void setClaimsList(List<Claim> claimList) {
        this.claimList = claimList;
    }
}
