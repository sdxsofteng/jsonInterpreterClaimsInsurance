package utils;
import models.input.Claims;
import models.input.Customer;
import models.output.ClaimsOut;
import models.output.CustomerOut;

import java.util.ArrayList;
import java.util.List;
/**
 * Cette classe permet de prendre l'objet Customer après le calcul et le transforme en CustomerOut et ClaimsOut
 * qui sera utilisé par le parser pour créer le JSON sortant.
 */
public class OutputObjects {
    //Dans cette méthode on set toutes les propriétés de l'objet CustomerOut ainsi que la liste ClaimsOut sous-jacente
    public static CustomerOut customerToOut(Customer input) {
        CustomerOut temp = new CustomerOut();
        temp.setCustomer(input.getClientNumber());
        temp.setCustomerClaimPeriod(input.getClaimPeriod());
        temp.setClaimsOutList(claimsToOut(input.getClaimsList())); //On set la liste de claimsOut. Voir claimsToOut
        return temp;
    }

    //Cette méthode transforme la claimsList dans le Customer vers une liste de ClaimsOut
    private static List<ClaimsOut> claimsToOut(List<Claims> claimsList){
        List<ClaimsOut> temp = new ArrayList<>();
        int treatmentNumber;
        String date;
        String refund;
        for (Claims claim: claimsList){  //Itère à traver la ClaimsList et créer des nouveau ClaimsOut
            treatmentNumber = claim.getTreatmentNumber();
            date = claim.getClaimDate();
            refund = floatToString(claim.getRefundAmount());
            temp.add(new ClaimsOut(treatmentNumber, date, refund)); //Ajoute le ClaimsOut créer dans la liste temporaire
        }
        return temp;
    }

    private static String floatToString(float refundAmount){
        return String.format("%.2f$", refundAmount);
    }
}
