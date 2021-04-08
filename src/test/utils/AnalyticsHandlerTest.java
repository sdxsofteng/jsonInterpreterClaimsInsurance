package utils;

import models.analytics.Analytics;

import models.analytics.ClaimCount;
import models.input.CaresValues;
import models.output.ClaimOut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyticsHandlerTest {
    static AnalyticsHandler analyticsHandler;
    static Analytics analytics;

    @BeforeEach
     void setUp() {
        analytics = new Analytics();
        analyticsHandler = new AnalyticsHandler(analytics);
    }

    @Test
    @DisplayName("AddValidRequest increments Analytics' valid requests by 1")
    public void testAddValidRequestIncrementsValidRequestsBy1() {
        int expected = analytics.getNbRequestsApproved() + 1;
        analyticsHandler.addValidRequest();
        int actual = analytics.getNbRequestsApproved();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("AddInvalidRequest increments Analytics' invalid requests by 1")
    public void testAddInvalidRequestIncrementsInvalidRequestsBy1() {
        int expected = analytics.getNbRequestsRejected() + 1;
        analyticsHandler.addInvalidRequest();
        int actual = analytics.getNbRequestsRejected();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Reset resets declared cares to initial state")
    public void testResetResetsDeclaredCares() {
        List<ClaimCount> claims = new ArrayList<>();
        ClaimCount claim = new ClaimCount();
        claim.setNbClaims(10);
        claim.setCareName("test");
        claims.add(claim);
        analytics.setDeclaredCares(claims);
        analyticsHandler.reset();
        assertEquals(0, analytics.getDeclaredCares().size());
    }

    @Test
    @DisplayName("Reset resets valid requests to initial state")
    public void testResetResetsValidRequests() {
        analytics.setNbRequestsApproved(5);
        analyticsHandler.reset();
        assertEquals(0, analytics.getNbRequestsApproved());
    }

    @Test
    @DisplayName("Reset resets invalid requests to initial state")
    public void testResetResetsInvalidRequests() {
        analytics.setNbRequestsRejected(5);
        analyticsHandler.reset();
        assertEquals(0, analytics.getNbRequestsRejected());
    }

    private CareReference setUpCareReference() {
        CareReference referenceObject = new CareReference();
        List<CaresValues> caresValues = new ArrayList<>();
        caresValues.add(new CaresValues("Test", 1, 100));
        referenceObject.setCaresValuesList(caresValues);
        return referenceObject;
    }

    @Test
    @DisplayName("Count claims increments total amount for a care appropriately")
    public void testClaimsCountCountsCareAppropriately() {
        CareReference referenceObject = setUpCareReference();
        List<ClaimOut> claims = new ArrayList<>();
        claims.add(new ClaimOut(10, "2020-01-01", "0.00$", 0));
        claims.add(new ClaimOut(11, "2020-01-01", "0.00$", 0));
        analyticsHandler.countClaims(claims, referenceObject);

        assertEquals(1, analytics.getDeclaredCares().size());
        assertEquals(2, analytics.getDeclaredCares().get(0).getNbClaims());
    }

    @Test
    @DisplayName("Count claims increments total global amount appropriately")
    public void testClaimsCountCountsGlobalAppropriately() {
        CareReference referenceObject = setUpCareReference();
        List<ClaimOut> claims = new ArrayList<>();
        claims.add(new ClaimOut(10, "2020-01-01", "0.00$", 0));
        claims.add(new ClaimOut(11, "2020-01-01", "0.00$", 0));
        analyticsHandler.countClaims(claims, referenceObject);

        assertEquals(2, analytics.getNbRequestsApproved());
    }

    @Test
    @DisplayName("Disabling persistence sets the correct property to false")
    public void testDisablingPersistenceSetsPersistenceToFalse() {
        AnalyticsHandler.disablePersistence();
        assertFalse(AnalyticsHandler.hasPersistence);
    }

}
