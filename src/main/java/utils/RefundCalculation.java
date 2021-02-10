package utils;
import models.input.CaresValues;
import models.input.Claims;
import models.input.Customer;

import java.util.List;
/**
 * Cette classe représente le noyau de calculs de notre programme. Tous les calculs de remboursement s'effectue dans
 * cette classe.
 */
public class RefundCalculation {

    private float maxAmount;
    private float refundPercentage;

    //Cette méthode est la méthode générale appelé depuis le main pour calculer les remboursements.
    public void refund(Customer customerInfo, CareReference referenceInfo){
        String contractLetter = customerInfo.getContractType();
        List<Claims> claimsList = customerInfo.getClaimsList();
        for (Claims claim: claimsList){
            getRefundMaxAndPercentage(claim, contractLetter, referenceInfo);
            calculateRefund(claim);
        }
    }

    //Cette méthode permet de chercher le montant maximum et le pourcentage pour la réclamation
    private void getRefundMaxAndPercentage(Claims claim, String contractLetter, CareReference referenceInfo){
        int treatmentNumber = claim.getTreatmentNumber();
        CaresValues care = referenceInfo.getAppropriateCareObject(treatmentNumber);
        maxAmount = care.getAppropriateMaxAmount(contractLetter);
        refundPercentage = care.getAppropriateRefundPercentage(contractLetter);
    }

    //Calcul le remboursement selon le maxAmount et le refundPercentage
    private void calculateRefund(Claims claim){
        float treatmentCost = claim.getTreatmentCostFloat();
        float refund = treatmentCost * refundPercentage; //Trouver le montant du remboursement selon le pourcentage
        if (refund > maxAmount && maxAmount != 0){ //Si celui si est au dessus du max et n'est pas 0 on met le
            refund = maxAmount;                    //montant maximal comme remboursement
        }
        claim.setRefundAmount(refund);
    }
}
