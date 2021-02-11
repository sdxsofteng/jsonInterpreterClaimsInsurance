package utils;

import models.input.Claim;
import models.input.Customer;
import models.output.ClaimOut;
import models.output.CustomerOut;

import java.util.ArrayList;
import java.util.List;

import static utils.ConversionUtils.floatToString;

/**
 * Cette classe permet de prendre l'objet Customer après le calcul et le transforme en CustomerOut et ClaimsOut
 * qui sera utilisé par le parser pour créer le JSON sortant.
 */
public class OutputHandler {

    //Dans cette méthode on set toutes les propriétés de l'objet CustomerOut ainsi que la liste ClaimsOut sous-jacente
    public static CustomerOut customerToOut(Customer input) {
        CustomerOut customerOut = new CustomerOut();
        customerOut.setCustomer(input.getClientNumber());
        customerOut.setCustomerClaimPeriod(input.getClaimPeriod());
        customerOut.setClaimsOutList(claimsToOut(input.getClaimsList()));//On set la liste de claimsOut.Voir claimsToOut
        return customerOut;
    }

    //Cette méthode transforme la claimsList dans le Customer vers une liste de ClaimsOut
    private static List<ClaimOut> claimsToOut(List<Claim> claimList){
        List<ClaimOut> claimOut = new ArrayList<>();
        for (Claim claim: claimList){  //Itère à traver la ClaimsList pour créer les ClaimsOut associés
            int treatmentNumber = claim.getTreatmentNumber();
            String date = claim.getClaimDate();
            String refund = floatToString(claim.getRefundAmount());
            claimOut.add(new ClaimOut(treatmentNumber, date, refund)); //Ajoute le claimsOut dans la liste de retour
        }
        return claimOut;
    }

}
