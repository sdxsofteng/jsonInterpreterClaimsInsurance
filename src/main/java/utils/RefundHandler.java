package utils;

import models.input.CaresValues;
import models.input.Claim;
import models.input.Customer;

import java.util.List;

/**
 * Cette classe représente le noyau de calculs de notre programme. Tous les calculs de remboursement s'effectue dans
 * cette classe.
 */
public class RefundHandler {

    private float maxAmount;
    private float refundPercentage;

    //Cette méthode est la méthode générale appelé depuis le main pour calculer les remboursements.
    public void refund(Customer customerInfo, CareReference referenceInfo){
        String contractLetter = customerInfo.getContractType();
        List<Claim> claimList = customerInfo.getClaimsList();
        for (Claim claim: claimList){
            getRefundMaxAndPercentage(claim, contractLetter, referenceInfo);
            calculateRefund(claim);
        }
    }

    //Cette méthode permet de chercher le montant maximum et le pourcentage pour la réclamation
    private void getRefundMaxAndPercentage(Claim claim, String contractType, CareReference referenceInfo){
        int treatmentNumber = claim.getTreatmentNumber();
        CaresValues careValue = referenceInfo.getAppropriateCareObject(treatmentNumber);
        maxAmount = careValue.getAppropriateMaxAmount(contractType);
        refundPercentage = careValue.getAppropriateRefundPercentage(contractType);
    }

    //Calcul le remboursement selon le maxAmount et le refundPercentage
    private void calculateRefund(Claim claim){
        float treatmentCost = claim.getTreatmentCostFloat();
        float refund = treatmentCost * refundPercentage; //Trouver le montant du remboursement selon le pourcentage
        if (refund > maxAmount && maxAmount != 0){ //Si celui si est au dessus du max et n'est pas 0 on met le
            refund = maxAmount;                    //montant maximal comme remboursement
        }
        claim.setRefundAmount(refund);
    }
}
