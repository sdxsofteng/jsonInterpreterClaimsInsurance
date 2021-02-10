package utils;

import models.input.Cares;
import models.input.Claims;
import models.input.ContractTypeValue;
import models.input.Customer;

import org.apache.commons.validator.GenericValidator;

import java.time.LocalDate;
import java.util.List;

public class Validation {
    public final static int CLIENT_NUM_LENGTH = 6;
    public static JacksonUtils jUtil = new JacksonUtils();

    public static void ValidateInvoice(Customer customer, CareReference referenceObject) {
        if (isValidClientNo(customer.getClientNumber())
                && isValidContractLetter(customer, referenceObject)
                && isValidInvoiceDate(customer.getClaimPeriod())
                && validateAllClaims(customer.getClaimsList(),
                customer.getClaimPeriod(), referenceObject)){ ;
        } else jUtil.errorOutputToJsonFile();
    }

    public static boolean isValidClientNo(String clientNo) {
        boolean isValid = (isSpecificLength(clientNo, CLIENT_NUM_LENGTH)
                && isOnlyDigits(clientNo) );
        return isValid;
    }

    public static boolean isSpecificLength(String clientNo, int lenght) {
        boolean isValid = (clientNo != null
                && clientNo.trim().length() == lenght);
        return isValid;
    }

    public static boolean isOnlyDigits(String clientNo) {
        boolean isValid = (clientNo.matches("^[0-9]+$"));
        return isValid;
    }

    // compare le type de contrat aux données du fichier reference
    public static boolean isValidContractLetter(Customer customer, CareReference referenceObject) {
        boolean isValid = false;
        for (Cares c : referenceObject.getCaresList()) {
            for (ContractTypeValue d :  c.getContractTypeValues()) {
                if (d.getType().equals(customer.getContractLetter()));
                    isValid = true;
            }
        }
        return isValid;
    }

    public static boolean isValidInvoiceDate(String invoiceDate){
        boolean isValid = (isValidDateFormatYM(invoiceDate)
                && isThisMonthOrEarlier(invoiceDate));
        return isValid;
    }

    // Verifie date en format AAAA-MM
    public static boolean isValidDateFormatYM(String date){
        boolean isValid = (GenericValidator.isDate(date, "yyyy-MM", false));
        return isValid;
    }

    // Compare une date AAAA-MM à la date courante AAAA-MM
    public static boolean isThisMonthOrEarlier(String claimMonth){
        if (claimMonth.compareTo(removeDayFromDate(LocalDate.now().toString())) <= 0 ){
            return true;
        }
        return false;
    }

    public static String removeDayFromDate(String inputDate){
        return inputDate.substring(0, inputDate.length() - 3);
    }

    public static boolean validateAllClaims(List<Claims> claimList, String claimPeriod,
                                            CareReference referenceObject){
        boolean isValid = true;
        for (Claims c: claimList) {
            if (!isValidClaim(c, claimPeriod, referenceObject)){
                isValid = false;
            }
        }
        return isValid;
    }

    public static boolean isValidClaim(Claims claims, String claimPeriod, CareReference referenceObject){
        boolean isValid = (isValidClaimType(claims.getTreatmentNumber(), referenceObject)
                && isValidClaimDate(claimPeriod, claims.getClaimDate())
                && isValidCost(claims.getTreatmentCost()));
        return isValid;
    }

    public static boolean isValidClaimType(int claimType, CareReference referenceObject){
        boolean isValid = (referenceObject.getAppropriateCareObject(claimType) != null);
        return isValid;
    }

    public static boolean isValidClaimDate(String claimPeriod, String claimDate){
        boolean isValid = (isValidDateFormatYMD(claimDate)
                && isCorrectClaimPeriod(claimPeriod, claimDate)
                && isTodayOrEarlier(claimDate));
        return isValid;
    }

    public static boolean isValidDateFormatYMD(String date){
        boolean isValid = (GenericValidator.isDate(date.trim(),
                "yyyy-MM-dd", true));
        return isValid;
    }

    public static boolean isTodayOrEarlier(String claimDate){
        boolean isValid = (claimDate.compareTo(LocalDate.now().toString()) <= 0 );
        return isValid;
    }

    public static boolean isCorrectClaimPeriod(String claimPeriod, String claimDate){
        boolean isValid = (removeDayFromDate(claimDate).equals(claimPeriod));
        return isValid;
    }

    public static boolean isValidCost(String cost){
        boolean isValid = (cost.trim().matches("^[0-9]+(,|.)([0-9]{2})?\\$$"));
        return isValid;
    }
}