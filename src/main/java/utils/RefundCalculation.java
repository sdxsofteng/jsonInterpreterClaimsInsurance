package utils;


import models.input.CaresValues;
import models.input.Claims;
import models.input.Customer;

import java.util.List;

public class RefundCalculation {

    private float maxAmount;
    private float refundPercentage;

    public void refund(Customer customerInfo, CareReference referenceInfo){
        String contractLetter = customerInfo.getContractType();
        List<Claims> claimsList = customerInfo.getClaimsList();
        for (Claims claim: claimsList){
            getRefundMaxAndPercentage(claim, contractLetter, referenceInfo);
            calculateRefund(claim);
        }
    }

    private void getRefundMaxAndPercentage(Claims claim, String contractLetter, CareReference referenceInfo){
        int treatmentNumber = claim.getTreatmentNumber();
        CaresValues care = referenceInfo.getAppropriateCareObject(treatmentNumber);
        maxAmount = care.getAppropriateMaxAmount(contractLetter);
        refundPercentage = care.getAppropriateRefundPercentage(contractLetter);
    }

    private void calculateRefund(Claims claim){
        float treatmentCost = claim.getTreatmentCostFloat();
        float refund = treatmentCost * refundPercentage;
        if (refund > maxAmount && maxAmount != 0){
            refund = maxAmount;
        }
        claim.setRefundAmount(refund);
    }




}
