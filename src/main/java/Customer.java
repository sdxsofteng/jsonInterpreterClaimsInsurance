public class Customer {

    private int clientNumber;
    private char contractLetter;
    private int claimPeriod;

    public Customer(int clientNumber, char contractLetter, int claimPeriod) {
        this.clientNumber = clientNumber;
        this.contractLetter = contractLetter;
        this.claimPeriod = claimPeriod;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public char getContractLetter() {
        return contractLetter;
    }

    public int getClaimPeriod() {
        return claimPeriod;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void setContractLetter(char contractLetter) {
        this.contractLetter = contractLetter;
    }

    public void setClaimPeriod(int claimPeriod) {
        this.claimPeriod = claimPeriod;
    }
}
