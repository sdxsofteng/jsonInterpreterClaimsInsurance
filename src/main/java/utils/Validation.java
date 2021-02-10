package utils;

import models.input.CaresValues;
import models.input.Claims;
import models.input.ContractTypeValue;
import models.input.Customer;

import org.apache.commons.validator.GenericValidator;

import java.time.LocalDate;
import java.util.List;

public class Validation {
    public final static int CLIENT_NUM_LENGTH = 6;
    public static JacksonUtils jUtil = new JacksonUtils();
    private static Customer client;
    private static CareReference presets;
    private static String clientNo;
    private static String invoiceDate;
    private static int treatmentNumber;
    private static String claimDate;
    private static String treatmentCost;

    public static void ValidateInvoice(Customer clientObject, CareReference referenceObject) {
        setVariables(clientObject, referenceObject);
        startValidation();
    }

    public static void setVariables(Customer clientObject, CareReference referenceObject) {
        client = clientObject;
        presets = referenceObject;
        clientNo = client.getClientNumber();
        invoiceDate = client.getClaimPeriod();
    }

    public static void startValidation() {
        if (!isValidClientNo()
                || !isValidContractType()
                || !isValidInvoiceDate()
                || !validateAllClaims(client.getClaimsList())) {
            jUtil.errorOutputToJsonFile();
        }
    }

    public static boolean isValidClientNo() {
        return (isSpecificLength(CLIENT_NUM_LENGTH)
                && isOnlyDigits(clientNo) );
    }

    public static boolean isSpecificLength(int length) {
        return (clientNo != null
                && clientNo.trim().length() == length);
    }

    public static boolean isOnlyDigits(String number) {
        return (number.matches("^[0-9]+$"));
    }

    // compare le type de contrat aux données du fichier reference
    public static boolean isValidContractType() {
        for (CaresValues singleCare : presets.getCaresValuesList()) {
            for (ContractTypeValue contractTypeValues :  singleCare.getContractTypeValues()) {
                if (contractTypeValues.getType().equals(client.getContractType()))
                    return true;
            }
        }
        return false;
    }

    public static boolean isValidInvoiceDate(){
        return (isValidYearAndMonthDate(invoiceDate)
                && isThisMonthOrEarlier(invoiceDate));
    }

    public static boolean isValidYearAndMonthDate(String date) {
        return (GenericValidator.isDate(date, "yyyy-MM", false));
    }

    public static boolean isThisMonthOrEarlier(String yearAndMonth) {
        return yearAndMonth.compareTo(removeDayFromDate(LocalDate.now().toString())) <= 0;
    }

    public static String removeDayFromDate(String yearMonthAndDayDate) {
        return yearMonthAndDayDate.substring(0, yearMonthAndDayDate.length() - 3);
    }

    public static boolean validateAllClaims(List<Claims> claimList) {
        boolean isValid = true;
        for (Claims singleClaim: claimList) {
            setClaimVariables(singleClaim);
            if (!isValidClaim()){
                isValid = false;
            }
        }
        return isValid;
    }

    public static void setClaimVariables(Claims singleClaim) {
        treatmentNumber = singleClaim.getTreatmentNumber();
        claimDate = singleClaim.getClaimDate();
        treatmentCost = singleClaim.getTreatmentCost();
    }

    public static boolean isValidClaim() {
        return (isValidClaimType()
                && isValidClaimDate()
                && isValidCost(treatmentCost));
    }

    public static boolean isValidClaimType() {
        return (presets.getAppropriateCareObject(treatmentNumber) != null);
    }

    public static boolean isValidClaimDate() {
        return (isValidDateFormatYMD(claimDate)
                && isTodayOrEarlier(claimDate)
                && isCorrectClaimPeriod());
    }

    public static boolean isValidDateFormatYMD(String date) {
        return (GenericValidator.isDate(date.trim(),
                "yyyy-MM-dd", true));
    }

    public static boolean isTodayOrEarlier(String date) {
        return (date.compareTo(LocalDate.now().toString()) <= 0 );
    }

    public static boolean isCorrectClaimPeriod() {
        return (removeDayFromDate(claimDate).equals(invoiceDate));
    }

    public static boolean isValidCost(String cost) {
        return (cost.trim().matches("^[0-9]+(,|.)([0-9]{2})?\\$$"));
    }
}