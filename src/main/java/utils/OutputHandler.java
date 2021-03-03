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
        customerOut.setContractType(input.getContractType());
        customerOut.setClientNumber(input.getClientNumber());
        customerOut.setClaimPeriod(input.getClaimPeriod());
        customerOut.setClaimsOutList(claimsToOut(input.getClaimsList()));
        return customerOut;
    }

    //Cette méthode transforme la claimsList dans le Customer vers une liste de ClaimsOut
    public static List<ClaimOut> claimsToOut(List<Claim> claimList){
        List<ClaimOut> claimOutList = new ArrayList<>();
        for (Claim claim: claimList){
            claimOutList.add(claimToOut(claim));
        }
        return claimOutList;
    }

    //Cette méthode transforme un Claim en ClaimOut
    public static ClaimOut claimToOut(Claim claim){
        int treatmentNumber = claim.getTreatmentNumber();
        String date = claim.getClaimDate();
        String refund = floatToString(claim.getRefundAmount());
        ClaimOut claimOut = new ClaimOut(treatmentNumber, date, refund);
        return claimOut;
    }
}
