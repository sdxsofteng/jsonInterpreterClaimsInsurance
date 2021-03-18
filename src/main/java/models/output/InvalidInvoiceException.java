package models.output;

import utils.JacksonUtils;

public class InvalidInvoiceException extends RuntimeException {
    public static JacksonUtils jUtil = new JacksonUtils();
    private Message errorMessage;
    private int claimNumber;

//    public InvalidInvoiceException() {
//    }

    public InvalidInvoiceException(Message errorMessage) {
        this.errorMessage = errorMessage;
    }

    public InvalidInvoiceException(Message errorMessage, int claimNumber) {
        this.errorMessage = errorMessage;
        this.claimNumber = claimNumber;
    }

    public Message getErrorMessage() {
        return errorMessage;
    }

    public int getClaimNumber() {
        return claimNumber;
    }
}