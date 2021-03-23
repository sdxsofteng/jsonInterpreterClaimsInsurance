package models.output;

public class InvalidInvoiceException extends RuntimeException {
    private ErrorOut errorOut;


    public InvalidInvoiceException(Message error) {
        super(error.getMessage());
        this.errorOut = new ErrorOut(error);
    }

    public InvalidInvoiceException(Message error, int claimNumber) {
        super(error.getMessage());
        this.errorOut = new ErrorOut(error, claimNumber);
    }

    public ErrorOut getErrorOut() {
        return errorOut;
    }
}