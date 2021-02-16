package utils;

import models.input.CaresValues;
import models.input.Claim;
import models.input.ContractTypeValue;
import models.input.Customer;

import org.apache.commons.validator.GenericValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ValidationHandler {

    public final static int CLIENT_NUM_LENGTH = 6;
    public static JacksonUtils jUtil = new JacksonUtils();
    private static Customer customer;
    private static CareReference presets;
    private static String clientNo;
    private static String invoiceDate;
    private static int treatmentNumber;
    private static String claimDate;
    private static String treatmentCost;

    public static void ValidateInvoice(Customer customer, CareReference referenceObj) {
        setVariables(customer, referenceObj);
        validate();
    }

    public static void setVariables(Customer client, CareReference referenceObj) {
        customer = client;
        presets = referenceObj;
        clientNo = customer.getClientNumber();
        invoiceDate = customer.getClaimPeriod();
    }

    public static void validate() {
        boolean hasValidInvoiceData = isAContractTypeThatExistsInPresets() && isValidInvoiceDate();
        boolean hasValidClaimsData = validateClaims(customer.getClaimsList());

        if (!isValidClientNo() || !hasValidInvoiceData || !hasValidClaimsData) {
            jUtil.quitProgramWithError();
        }
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

    public static boolean isValidInvoiceDate() {
        return isValidYearAndMonthDate(invoiceDate) && isThisMonthOrEarlier(invoiceDate);
    }

    public static boolean isValidYearAndMonthDate(String date) {
        return GenericValidator.isDate(date, "yyyy-MM", false);
    }

    public static boolean isThisMonthOrEarlier(String yearAndMonth) {
        boolean isValid =  yearAndMonth.compareTo(getCurrentYearAndMonth()) <= 0;
        return isValid;
    }

    public static String getCurrentYearAndMonth(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }

    public static boolean validateClaims(List<Claim> claimList) {
        boolean isValid = true;
        for (Claim claim : claimList) {
            setClaimVariables(claim);
            if (!isValidClaim()) {
                isValid = false;
            }
        }
        return isValid;
    }

    public static void setClaimVariables(Claim singleClaim) {
        treatmentNumber = singleClaim.getTreatmentNumber();
        claimDate = singleClaim.getClaimDate();
        treatmentCost = singleClaim.getTreatmentCost();
    }

    public static boolean isValidClaim() {
        return isValidClaimType() && isValidClaimDate() && isValidCost(treatmentCost);
    }

    public static boolean isValidClaimType() {
        return presets.getAppropriateCareObject(treatmentNumber) != null;
    }

    public static boolean isValidClaimDate() {
        return isValidDateFormatYMD(claimDate) && isTodayOrEarlier(claimDate) && isCorrectClaimPeriod();
    }

    public static boolean isValidDateFormatYMD(String date) {
        return GenericValidator.isDate(date.trim(), "yyyy-MM-dd", true);
    }

    public static boolean isTodayOrEarlier(String date) {
        return date.compareTo(LocalDate.now().toString()) <= 0;
    }

    public static boolean isCorrectClaimPeriod() {
        return removeDayFromDate(claimDate).equals(invoiceDate);
    }

    public static String removeDayFromDate(String yearMonthAndDayDate) {
        return DateTimeFormatter.ofPattern("yyyy-MM").format(LocalDate.parse(yearMonthAndDayDate));
    }

    public static boolean isValidCost(String cost) {
        return cost.trim().matches("^[0-9]+(,|.)[0-9]{2}\\$$");
    }

    public static boolean hasValidArgs(int argsLength){
        return argsLength == 2;
    }
}