package models.output;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import utils.ConversionUtils;
import utils.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerOutTest {

    static CustomerOut customerOut;

    @BeforeAll
    static void setUp() {
        customerOut = new CustomerOut();
        customerOut.setClientNumber("123456");
        customerOut.setClaimPeriod("2021-01");
        ClaimOut testClaimOut1 = new ClaimOut(100, "2021-01-01", "100.11$", 100.11f);
        ClaimOut testClaimOut2 = new ClaimOut(313,"2021-01-31", "50.02$", 50.02f);
        List<ClaimOut> claimOutList = new ArrayList<>();
        claimOutList.add(testClaimOut1);
        claimOutList.add(testClaimOut2);
        customerOut.setClaimsOutList(claimOutList);
    }

    @Test
    @DisplayName("Total refund amount should match the expected total.")
    public void testCustomerOutTotalRefundAmountProperTotal() {
        float expected = 150.13f;
        float actual = ConversionUtils.convertCostStringToFloat(customerOut.getFormattedTotalRefundAmount());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Total refund amount should match the expected format.")
    public void testCustomerOutTotalRefundAmountFormat() {
        boolean isValid = ValidationHandler.isValidCost(customerOut.getFormattedTotalRefundAmount());
        assertTrue(isValid);
    }

}

