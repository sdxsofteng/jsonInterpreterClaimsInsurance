package models.analytics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClaimCountTest {
    @Test
    @DisplayName("ToString Supplies the expected format")
    public void testToStringContainsAmountAndCareName() {
        ClaimCount claimCount = new ClaimCount();
        claimCount.setCareName("test");
        claimCount.setAmount(3);

        String expected = "test: 3";

        assertEquals(expected, claimCount.toString());
    }
}
