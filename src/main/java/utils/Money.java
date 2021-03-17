package utils;

import models.input.CaresValues;

import java.math.BigInteger;

public class Money {

    int valueInCents;
    JacksonUtils overMaxIntegerCase = new JacksonUtils();

    public Money(float value) {
        this.valueInCents = convertFloatToInt(value);
    }

    public int getValueInCents() {
        return valueInCents;
    }

    public void setValueInCents(int valueInCents) {
        this.valueInCents = valueInCents;
    }

    private int convertFloatToInt(float value){
        return (int)Math.round(value * 100);
    }

    private float convertIntToFloat(int value){
        return (float)value / 100;
    }

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

    private int calculateRefundAmountCents(int refundPercentageInt) {

        int refundAmount = this.valueInCents * refundPercentageInt / 100;

        if (refundAmount < 0){
            overMaxIntegerCase.quitProgramWithErrorAndTracking();
        }
        return refundAmount;
    }

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
