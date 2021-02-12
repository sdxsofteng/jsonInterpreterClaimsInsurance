package utils;

import models.input.Claim;
import models.input.Customer;

import models.output.ClaimOut;
import models.output.CustomerOut;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.ConversionUtils.floatToString;
import static utils.OutputHandler.*;

public class OutputHandlerTest {

    static Customer customer;

    @BeforeAll
    static void setUp() {
        Claim testClaim1 = new Claim(200, "2021-01-11", "0$");
        Claim testClaim2 = new Claim(100, "2021-01-01", "100$");
        Claim testClaim3 = new Claim(313,"2021-01-31", "1000000.00$");
        List<Claim> claimList = new ArrayList<>();
        claimList.add(testClaim1);
        claimList.add(testClaim2);
        claimList.add(testClaim3);
        customer = new Customer("100000", "B", "2021-01", claimList);
    }

    @Test
    @DisplayName("Customer out should retain the same clientNo")
    public void testCustomerToOutRetainsClientNo() {
        CustomerOut resultingCustomerOut = customerToOut(customer);
        assertEquals(customer.getClientNumber(), resultingCustomerOut.getClientNumber());
    }

    @Test
    @DisplayName("Customer out should retain the same claim period")
    public void testCustomerToOutRetainsClaimPeriod() {
        CustomerOut resultingCustomerOut = customerToOut(customer);
        assertEquals(customer.getClaimPeriod(), resultingCustomerOut.getClaimPeriod());
    }

    @Test
    @DisplayName("Customer out should retain the same number of claims")
    public void testCustomerToOutRetainsClaims() {
        CustomerOut resultingCustomerOut = customerToOut(customer);
        assertEquals(customer.getClaimsList().size(), resultingCustomerOut.getClaimsOutList().size());
    }

    @Test
    @DisplayName("Claim out should retain the same treatment number")
    public void testClaimOutRetainsTreatmentNumber() {
        for(Claim claim: customer.getClaimsList()) {
            ClaimOut resultingClaimOut = claimToOut(claim);
            assertEquals(claim.getTreatmentNumber(), resultingClaimOut.getTreatmentNumber());
        }
    }

    @Test
    @DisplayName("Claim out should retain the same claim date")
    public void testClaimOutRetainsClaimDate() {
        for(Claim claim: customer.getClaimsList()) {
            ClaimOut resultingClaimOut = claimToOut(claim);
            assertEquals(claim.getClaimDate(), resultingClaimOut.getClaimDate());
        }
    }

    @Test
    @DisplayName("Claim out should retain the same refund amount, but in the proper text format")
    public void testClaimOutRetainsRefundAmount() {
        for(Claim claim: customer.getClaimsList()) {
            ClaimOut resultingClaimOut = claimToOut(claim);
            String formattedCost = floatToString(claim.getRefundAmount());
            assertEquals(formattedCost, resultingClaimOut.getRefundAmount());
        }
    }
}
