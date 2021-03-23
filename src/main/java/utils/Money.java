package utils;

import models.input.CaresValues;
import models.output.ErrorOut;
import models.output.Message;

/**
 * Cette classe permet de faire les calculs monétaires de façon sécuritaire. Dans cette classe on prend le cout
 * en float, on le transfere en int et ensuite on fait les calculs monétaires pour finalement renvoyer un int.
 */
public class Money {

    int valueInCents;
    JacksonUtils overMaxIntegerCase = new JacksonUtils();

    public Money(float value) {
        this.valueInCents = convertFloatToInt(value);
    }

    private int convertFloatToInt(float value){
        return Math.round(value * 100);
    }

    private float convertIntToFloat(int value){
        return (float)value / 100;
    }

    // Méthode principale pour calculer le montant a rembourser
    public float calculateAmountToRefund(float maxAmount, float refundPercentage, CaresValues careValue){
        int maxAmountCents = convertFloatToInt(maxAmount);
        int refundPercentageInt = convertFloatToInt(refundPercentage);
        int refundAmount = calculateRefundAmountCents(refundPercentageInt);
        if (refundAmount > maxAmountCents && maxAmount != 0){
            refundAmount = maxAmountCents;
        }
        refundAmount = adjustMonthlyMaxAmountCare(careValue, refundAmount);
        return  convertIntToFloat(refundAmount);
    }

    // Calcul le montant à rembourser et vérifie si celui-ci est plus grand que le Integer.MAX de java.
    // Si + grand on sort du programme en erreur.
    private int calculateRefundAmountCents(int refundPercentageInt) {
        int refundAmount = this.valueInCents * refundPercentageInt / 100;

        if (refundAmount < 0){
            overMaxIntegerCase.logStatsAndExitWithError(new ErrorOut(Message.OVER_MAX_INTEGER));
        }
        return refundAmount;
    }

    // Ajustement du montant mensuel total et modification du remboursement si celui ci est atteint.
    private int adjustMonthlyMaxAmountCare(CaresValues careValue, int refundAmount){
        if (careValue.getMonthlyMaxExists()){
            if (refundAmount >= careValue.monthlyMaxAmountInCents){
                refundAmount = careValue.monthlyMaxAmountInCents;
                careValue.monthlyMaxAmountInCents = 0;
            }else{
                careValue.monthlyMaxAmountInCents = careValue.monthlyMaxAmountInCents - refundAmount;
            }
        }
        return  refundAmount;
    }
}
