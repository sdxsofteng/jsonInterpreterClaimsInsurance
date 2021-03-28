package utils;

import models.input.CaresValues;
import models.input.Claim;
import models.input.ContractTypeValue;
import models.input.Customer;

import models.output.ErrorOut;
import models.output.InvalidInvoiceException;
import models.output.Message;
import org.apache.commons.validator.GenericValidator;

import java.util.List;

public class ValidationHandler {

    public final static int CLIENT_NUM_LENGTH = 6;
    public static JacksonUtils jUtil = new JacksonUtils();
    protected static Customer customer;
    protected static CareReference presets;
    protected static String contractType;
    protected static String clientNo;
    protected static String invoiceDate;
    protected static int claimNumber = 0;
    protected static int treatmentNumber;
    protected static String claimDate;
    protected static String treatmentCost;

    public static void ValidateInvoice(Customer customer, CareReference referenceObj) {
        setVariables(customer, referenceObj);
        try {
            validateFileNumber();
            validateInvoiceDate();
            validateAllClaims(customer.getClaimsList());
        } catch (InvalidInvoiceException e) {
            jUtil.logStatsAndExitWithError(e.getErrorOut());
        }
    }

    public static void setVariables(Customer client, CareReference referenceObj) {
        customer = client;
        contractType = customer.getContractType();
        presets = referenceObj;
        clientNo = customer.getClientNumber();
        invoiceDate = customer.getClaimPeriod();
    }

    public static void validateFileNumber() throws InvalidInvoiceException{
        checkIfFileNumberIsPresent();
        if (!isAContractTypeThatExistsInPresets() || !isValidClientNo()){
            throw new InvalidInvoiceException(Message.INCORRECT_FILENUMBER);
        }
    }

    public static void checkIfFileNumberIsPresent() throws InvalidInvoiceException{
        if (contractType == null || contractType.equals("")){
            throw new InvalidInvoiceException(Message.MISSING_FILENUMBER);
        }
    }

    public static boolean isAContractTypeThatExistsInPresets() {
        for (CaresValues careValue : presets.getCaresValuesList()) {
            for (ContractTypeValue contractTypeValue : careValue.getContractTypeValues()) {
                if (contractTypeValue.getType().equals(contractType)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidClientNo() {
        return isSpecificLength(clientNo, CLIENT_NUM_LENGTH) && isOnlyDigits(clientNo);
    }

    public static boolean isSpecificLength(String inputString, int expectedNb) {
        return (inputString != null && inputString.trim().length() == expectedNb);
    }

    public static boolean isOnlyDigits(String number) {
        return (number.matches("^[0-9]+$"));
    }

    public static void validateInvoiceDate() throws InvalidInvoiceException{
        if (invoiceDate == null || invoiceDate.equals("")){
            throw new InvalidInvoiceException(Message.MISSING_INVOICE_DATE);
        } else if (!isValidYearMonthOnlyDateFormat(invoiceDate)){
            throw new InvalidInvoiceException(Message.INCORRECT_INVOICE_DATE);
        }
    }

    public static boolean isValidYearMonthOnlyDateFormat(String date) {
        boolean isValid = false;
        boolean isValidDate = GenericValidator.isDate(date, "yyyy-MM", true);
        boolean isValidLength = isSpecificLength(date, 7);
        if (isValidDate && isValidLength){
            isValid = true;
        }
        return isValid;
    }

    public static void validateAllClaims(List<Claim> claimList) throws InvalidInvoiceException {
        if (claimList == null || claimList.isEmpty()){
            throw new InvalidInvoiceException(Message.MISSING_CLAIMS);
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

    public static void validateClaimType() throws InvalidInvoiceException {
        if (treatmentNumber == 0){
            throw new InvalidInvoiceException(Message.MISSING_CARE_NO, claimNumber);
        } else if (presets.getAppropriateCareObject(treatmentNumber) == null){
            throw new InvalidInvoiceException(Message.INVALID_CARE_NO, claimNumber);
        }
    }

    public static void validateClaimDate() throws InvalidInvoiceException {
        boolean isEmptyDate = claimDate == null || claimDate.equals("");
        if (isEmptyDate){
            throw new InvalidInvoiceException(Message.MISSING_CLAIM_DATE, claimNumber);
        } else if (!isValidYearMonthDayDateFormat(claimDate) || !isCorrectClaimPeriod()){
            throw new InvalidInvoiceException(Message.INVALID_CLAIM_DATE, claimNumber);
        }
    }

    public static boolean isValidYearMonthDayDateFormat(String date) {
        boolean hasValidFormat = GenericValidator.isDate(date.trim(), "yyyy-MM-dd", true);
        return hasValidFormat;
    }

    public static boolean isCorrectClaimPeriod() {
        boolean hasMatchingDate = ConversionUtils.removeDayFromDate(claimDate).equals(invoiceDate);
        return hasMatchingDate;
    }

    public static void validateCost() throws InvalidInvoiceException {
        boolean isEmptyCost = treatmentCost == null || treatmentCost.equals("");
        if (isEmptyCost){
            throw new InvalidInvoiceException(Message.MISSING_TREATMENT_COST, claimNumber);
        } else if (!isValidCost(treatmentCost)){
            throw new InvalidInvoiceException(Message.INVALID_TREATMENT_COST, claimNumber);
        }
    }

    public static boolean isValidCost(String cost) {
        return cost.trim().matches("^[0-9]+(,|.)[0-9]{2}\\$$");
    }

    public static void validateArgs(int actualNb, int expectedNb) {
        if (actualNb != expectedNb) {
            Message errorMsg = actualNb == 0 ? Message.MISSING_ARGUMENTS : Message.INCORRECT_ARGUMENTS;
            jUtil.exitWithError(new ErrorOut(errorMsg));
        }
    }
}