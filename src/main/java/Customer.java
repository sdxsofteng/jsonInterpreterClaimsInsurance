public class Customer {

    private int clientNumber;
    private char contractNumber;
    private int claimPeriod;

    public Customer(int clientNumber, char contractNumber, int claimPeriod) {
        this.clientNumber = clientNumber;
        this.contractNumber = contractNumber;
        this.claimPeriod = claimPeriod;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public char getContractNumber() {
        return contractNumber;
    }

    public int getClaimPeriod() {
        return claimPeriod;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void setContractNumber(char contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setClaimPeriod(int claimPeriod) {
        this.claimPeriod = claimPeriod;
    }
}
