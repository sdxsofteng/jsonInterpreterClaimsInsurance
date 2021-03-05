package utils;

import models.input.CaresValues;
import models.input.Claim;
import models.input.ContractTypeValue;
import models.input.Customer;

/**
 * Cette classe représente le noyau de calculs de notre programme. Tous les calculs de remboursement s'effectue dans
 * cette classe.
 */
public class RefundHandler {

    //Cette méthode est la méthode générale appelé depuis le main pour calculer les remboursements.
    public void processRefund(Customer customerInfo, CareReference referenceInfo){
        String contractType = customerInfo.getContractType();
        for (Claim claim: customerInfo.getClaimsList()){
            float refundAmount = calculateRefund(claim, contractType, referenceInfo);
            claim.setRefundAmount(refundAmount);
        }
    }

    //Cette méthode permet de chercher l'objet pour trouver le montant maximum et le pourcentage pour la réclamation
    public ContractTypeValue getRelatedContractTypeObject(Claim claim, String contractType, CareReference referenceInfo) {
        int treatmentNumber = claim.getTreatmentNumber();
        CaresValues careValue = referenceInfo.getAppropriateCareObject(treatmentNumber);
        ContractTypeValue contractTypeValue = careValue.getContractTypeData(contractType);
        return contractTypeValue;
    }

    private CaresValues getRelatedCareValues(Claim claim, CareReference referenceInfo){
        int treatmentNumber = claim.getTreatmentNumber();
        return referenceInfo.getAppropriateCareObject(treatmentNumber);
    }


    //Calcul le remboursement selon le maxAmount, le refundPercentage et le monthlyMaxAmount
    public float calculateRefund(Claim claim, String contractType, CareReference referenceInfo) {
        ContractTypeValue targetCareValues = getRelatedContractTypeObject(claim, contractType, referenceInfo);
        CaresValues monthlyMaxForCare = getRelatedCareValues(claim, referenceInfo);
        float maxAmount = targetCareValues.getMaxDeductibleAmount();
        float refundPercentage = targetCareValues.getRefundPercentage();
        Money treatmentCost = new Money(claim.getTreatmentCostFloat());
        return treatmentCost.calculateAmountToRefund(maxAmount, refundPercentage, monthlyMaxForCare);
    }
}
