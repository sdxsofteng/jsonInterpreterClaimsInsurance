package models.analytics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnalyticsTest {
    @Test
    @DisplayName("Analytic is initialized with a cares list to prevent NullPointers.")
    public void testAnalyticsInitializationContainsNonNullDeclaredCaresList() {
        Analytics analytics = new Analytics();
        assertNotNull(analytics.getDeclaredCares());
    }

    @Test
    @DisplayName("ToString contains approved amount")
    public void testToStringContainsApprovedRequests() {
        Analytics analytics = new Analytics();
        analytics.setNbRequestsApproved(2);
        assertTrue(analytics.toString().contains("Requêtes approuvées: 2"));
    }

    @Test
    @DisplayName("ToString contains rejected amount")
    public void testToStringContainsRejectedRequests() {
        Analytics analytics = new Analytics();
        analytics.setNbRequestsRejected(3);
        assertTrue(analytics.toString().contains("Requêtes rejetées: 3"));
    }

    @Test
    @DisplayName("ToString contains declared care info.")
    public void testToStringContainsDeclaredCares() {
        Analytics analytics = new Analytics();

        ClaimCount claimCount = new ClaimCount();
        claimCount.setCareName("test");
        claimCount.setNbClaims(3);
        analytics.getDeclaredCares().add(claimCount);

        assertTrue(analytics.toString().contains(claimCount.toString()));
    }
}
