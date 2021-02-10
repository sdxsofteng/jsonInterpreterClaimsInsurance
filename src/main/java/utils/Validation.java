package utils;

import models.input.Claims;
import models.input.Customer;

import org.apache.commons.validator.GenericValidator;

import java.time.LocalDate;
import java.util.List;

public class Validation {

    /** CONSTANTES **/
    // TODO: 2021-02-05 Placer les constantes de validation dans un endroit strategique 
    public final static String VALID_CONTRACT_TYPES = "A,B,C,D";
    public final static int CLIENT_NUM_LENGTH = 6;
    public static JacksonUtils jUtil = new JacksonUtils();

    /**
     * Methode qui valide la demande
     *
     *      Verifie le numero de client
     *      Verifie le type de contrat
     *      Verifie le mois
     *
     *      Verifie les reclamationS
     */
    public static void ValidateInvoice(Customer customer,
                                       CareReference referenceObject) {
        if (isValidClientNo(customer.getClientNumber())
                && isValidContractType(customer.getContractType())
                && isValidInvoiceDate(customer.getClaimPeriod())
                && validateAllClaims(customer.getClaimsList(),
                                     customer.getClaimPeriod(),
                                     referenceObject)){
        } else jUtil.errorOutputToJsonFile();
    }

    /**
     * Methodes qui valident le numero client.
     *
     * @param
     * @return
     */
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

    /**
     * Methode qui valide le type de contrat.
     */
    public static boolean isValidContractType(String contractType){
        boolean isValid = false;
        contractType = contractType.trim();
        String[] result = VALID_CONTRACT_TYPES.split(",");
        for (int i=0; i<result.length; i++) {
            if (contractType.equals (result[i])) {
                isValid = true;
            }
        }
        return isValid;
    }

    /**
     * Methodes qui valident la date de la facture.
     */
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
        if (claimMonth.compareTo(removeDayFromDate(LocalDate.now().toString()))
                <= 0 ){
            return true;
        }
        return false;
    }

    // Enleve les 3 derniers characteres d'une date AAAA-MM-JJ
    public static String removeDayFromDate(String inputDate){
        return inputDate.substring(0, inputDate.length() - 3);
    }

    /**
     * Methode pour valider toutes les reclamation
     */
    public static boolean validateAllClaims(List<Claims> claimList,
                                            String claimPeriod,
                                            CareReference referenceObject){
        boolean isValid = true;
        for (Claims c: claimList
             ) {
            if (!isValidClaim(c, claimPeriod, referenceObject)){
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * Methode pour valider une reclamation
     */
    public static boolean isValidClaim(Claims claims, String claimPeriod,
                                       CareReference referenceObject){
        boolean isValid = (isValidClaimType(claims.getTreatmentNumber(),
                                            referenceObject)
                && isValidClaimDate(claimPeriod, claims.getClaimDate())
                && isValidCost(claims.getTreatmentCost()));
        return isValid;
    }

    public static boolean isValidClaimType(int claimType,
                                           CareReference referenceObject){
        boolean isValid = (referenceObject.getAppropriateCareObject(claimType)
                           != null);
        return isValid;
    }

    /**
     * Methodes pour valider la date d'une réclamation
     */
    public static boolean isValidClaimDate(String claimPeriod,
                                           String claimDate){
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
        boolean isValid = (claimDate.compareTo(LocalDate.now().toString())
                           <= 0 );
        return isValid;
    }

    public static boolean isCorrectClaimPeriod(String claimPeriod,
                                               String claimDate){
        boolean isValid = (removeDayFromDate(claimDate).equals(claimPeriod));
        return isValid;
    }

    /**
     * Methodes pour valider le cout d'une réclamation
     */
    public static boolean isValidCost(String cost){
        boolean isValid = (cost.trim().matches("^[0-9]+(,|.)[0-9]{2}\\$$"));
        return isValid;
    }
}