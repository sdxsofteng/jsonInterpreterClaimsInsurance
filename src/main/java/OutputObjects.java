import java.util.ArrayList;
import java.util.List;

public class OutputObjects {

    private static CustomerOut customerOut;
    private static List<ClaimsOut> claimsListOut;


    public static CustomerOut customerToOut(Customer input) {

        CustomerOut temp = new CustomerOut();
        temp.setCustomer(input.getClientNumber());
        temp.setCustomerClaimPeriod(input.getClaimPeriod());

        claimsListOut = claimsToOut(input.getClaimsList());
        temp.setClaimsOutList(claimsListOut);

        return temp;
    }

    private static List<ClaimsOut> claimsToOut(List<Claims> claimsList){
        List<ClaimsOut> temp = new ArrayList<>();
        int treatmentNumber;
        String date;
        String refund;
        for (Claims claim: claimsList){
            treatmentNumber = claim.getTreatmentNumber();
            date = claim.getClaimDate();
            refund = floatToString(claim.getRefundAmount());
            temp.add(new ClaimsOut(treatmentNumber, date, refund));
        }
        return temp;
    }

    private static String floatToString(float refundAmount){
        return String.format("%.2f$", refundAmount);
    }
}
