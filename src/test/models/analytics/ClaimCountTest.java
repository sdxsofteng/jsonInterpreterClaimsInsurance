package models.analytics;

import models.output.ClaimOut;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClaimCountTest {

    @Test
    @DisplayName("ToString Supplies the expected format")
    public void testToStringContainsAmountAndCareName() {
        ClaimCount claimCount = new ClaimCount();
        claimCount.setCareName("test");
        claimCount.setNbClaims(3);

        String expected = "\ttest: 3";

        assertTrue(claimCount.toString().startsWith(expected));
    }

    @Test
    @DisplayName("Track claim adds 1 to nbClaims")
    public void testTrackClaimIncrementsTotalClaims() {
        ClaimCount claimCount = new ClaimCount();
        ClaimOut testClaimOut1 = new ClaimOut(100, "2021-01-01", "100.11$", 100.11f);
        claimCount.trackClaim(testClaimOut1);
        assertEquals(1, claimCount.getNbClaims());
    }

    @Test
    @DisplayName("Track claim sets new max when the amount is above")
    public void testTrackClaimSetsNewTotalWhenHigher() {
        ClaimCount claimCount = new ClaimCount();
        claimCount.setHighestClaimValue(100);
        ClaimOut testClaimOut1 = new ClaimOut(100, "2021-01-01", "100.11$", 100.11f);
        claimCount.trackClaim(testClaimOut1);
        assertEquals(testClaimOut1.getClaimAmount(), claimCount.getHighestClaimValue());
    }

    @Test
    @DisplayName("Track claim does not set new max when the amount is under")
    public void testTrackClaimDoesNotSetNewTotalWhenLower() {
        ClaimCount claimCount = new ClaimCount();
        float originalMax = 100;
        claimCount.setHighestClaimValue(originalMax);
        ClaimOut testClaimOut1 = new ClaimOut(100, "2021-01-01", "100.11$", 99f);
        claimCount.trackClaim(testClaimOut1);
        assertEquals(originalMax, claimCount.getHighestClaimValue());
    }

    @Test
    @DisplayName("Track claim increases total claimed appropriately")
    public void testTrackClaimIncreasesTotalClaimed() {
        ClaimCount claimCount = new ClaimCount();
        float originalTotal = 100;
        claimCount.setTotalClaimedAmount(originalTotal);
        ClaimOut testClaimOut1 = new ClaimOut(100, "2021-01-01", "100.11$", 99f);
        claimCount.trackClaim(testClaimOut1);
        float expected = originalTotal + testClaimOut1.getClaimAmount();
        assertEquals(expected, claimCount.getTotalClaimedAmount());
    }

}
