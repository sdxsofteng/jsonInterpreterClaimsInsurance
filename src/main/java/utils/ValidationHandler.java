package utils;

import models.input.CaresValues;
import models.input.Claim;
import models.input.ContractTypeValue;
import models.input.Customer;

import models.output.ErrorMessage;
import org.apache.commons.validator.GenericValidator;

import java.util.List;

public class ValidationHandler {

    public final static int CLIENT_NUM_LENGTH = 6;
    public static JacksonUtils jUtil = new JacksonUtils();
    private static Customer customer;
    private static CareReference presets;
    private static String clientNo;
    private static String invoiceDate;
    private static int claimNumber = 0;
    private static int treatmentNumber;
    private static String claimDate;
    private static String treatmentCost;


    public static void ValidateInvoice(Customer customer, CareReference referenceObj) {
        setVariables(customer, referenceObj);
        checkIfFilenumberIsPresent();
        validateFileNumber();
        validateInvoiceDate();
        validateAllClaims(customer.getClaimsList());
    }

    public static void setVariables(Customer client, CareReference referenceObj) {
        customer = client;
        presets = referenceObj;
        clientNo = customer.getClientNumber();
        invoiceDate = customer.getClaimPeriod();
    }

    public static void checkIfFilenumberIsPresent(){
        if (customer.getContractType() == null || customer.getContractType().equals("")){
            jUtil.quitProgramWithErrorAndTracking(ErrorMessage.MISSING_FILENUMBER);
        }
    }

    public static void validateFileNumber(){
        if (!isAContractTypeThatExistsInPresets() || !isValidClientNo()){
            jUtil.quitProgramWithErrorAndTracking(ErrorMessage.INCORRECT_FILENUMBER);
        }
    }

    public static boolean isAContractTypeThatExistsInPresets() {
        for (CaresValues careValue : presets.getCaresValuesList()) {
            for (ContractTypeValue contractTypeValue : careValue.getContractTypeValues()) {
                if (contractTypeValue.getType().equals(customer.getContractType())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidClientNo() {
        return isSpecificLength(CLIENT_NUM_LENGTH) && isOnlyDigits(clientNo);
    }

    public static boolean isSpecificLength(int length) {
        return clientNo != null && clientNo.trim().length() == length;
    }

    public static boolean isOnlyDigits(String number) {
        return (number.matches("^[0-9]+$"));
    }

    public static void validateInvoiceDate(){
        if (invoiceDate == null){
            jUtil.quitProgramWithErrorAndTracking(ErrorMessage.MISSING_INVOICE_DATE);
        } else if (!isValidYearAndMonthDate(invoiceDate)){
            jUtil.quitProgramWithErrorAndTracking(ErrorMessage.INCORRECT_INVOICE_DATE);
        }
    }

    public static boolean isValidYearAndMonthDate(String date) {
        return GenericValidator.isDate(date, "yyyy-MM", false);
    }

    public static void validateAllClaims(List<Claim> claimList) {
        if (claimList == null){
            jUtil.quitProgramWithErrorAndTracking(ErrorMessage.MISSING_CLAIMS);
        }
        for (Claim claim : claimList) {
            setClaimVariables(claim);
            validateClaim();
        }
    }

    public static void setClaimVariables(Claim singleClaim) {
        claimNumber = claimNumber +1;
        treatmentNumber = singleClaim.getTreatmentNumber();
        claimDate = singleClaim.getClaimDate();
        treatmentCost = singleClaim.getTreatmentCost();
    }

    public static void validateClaim() {
        validateClaimType();
        validateClaimDate();
        validateCost();
    }

    public static void validateClaimType() {
        if (treatmentNumber == 0){
            jUtil.quitProgramWithErrorAndTracking(ErrorMessage.MISSING_CARE_NO, claimNumber);
        }
        if (presets.getAppropriateCareObject(treatmentNumber) == null){
            jUtil.quitProgramWithErrorAndTracking(ErrorMessage.INVALID_CARE_NO, claimNumber);
        }
    }

    public static void validateClaimDate() {
        if (claimDate == null){
            jUtil.quitProgramWithErrorAndTracking(ErrorMessage.MISSING_CLAIM_DATE, claimNumber);
        }
        if (!isValidDateFormatYMD(claimDate) || !isCorrectClaimPeriod()){
            jUtil.quitProgramWithErrorAndTracking(ErrorMessage.INVALID_CLAIM_DATE, claimNumber);
        }
    }

    public static boolean isValidDateFormatYMD(String date) {
        return GenericValidator.isDate(date.trim(), "yyyy-MM-dd", true);
    }

    public static boolean isCorrectClaimPeriod() {
        return ConversionUtils.removeDayFromDate(claimDate).equals(invoiceDate);
    }

    public static void validateCost(){
        if (treatmentCost == null){
            jUtil.quitProgramWithErrorAndTracking(ErrorMessage.MISSING_TREATMENT_COST, claimNumber);
        }
        if (!isValidCost(treatmentCost)){
            jUtil.quitProgramWithErrorAndTracking(ErrorMessage.INVALID_TREATMENT_COST, claimNumber);
        }
    }

    public static boolean isValidCost(String cost) {
        return cost.trim().matches("^[0-9]+(,|.)[0-9]{2}\\$$");
    }

    public static void validateArgs(int actualNb, int expectedNb) {
        if (expectedNb != actualNb) {
            jUtil.quitProgramWithError(ErrorMessage.INCORRECT_ARGUMENTS);
        }
    }
}