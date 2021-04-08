package models.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClaimTest {

    @ParameterizedTest
    @MethodSource("claimsSource")
    @DisplayName("Claim getTreatmentCostFloat method should return the expected float value for its string counterpart")
    public void testClaimGetCostAsFloat(Claim claim) {
        String strippedCostString = claim.getTreatmentCost().trim().replace("$", "");
        String formattedCostString = strippedCostString.replace(",", ".");
        float expectedValue = Float.parseFloat(formattedCostString);
        assertEquals(expectedValue, claim.getTreatmentCostFloat());
    }

    @Test
    @DisplayName("Setters Testing")
    public void testSettersObject(){
        Claim t = new Claim();
        t.setTreatmentNumber(200);
        assertEquals(200, t.getTreatmentNumber());
        t.setClaimDate("12-01-2020");
        assertEquals("12-01-2020", t.getClaimDate());
        t.setTreatmentCost("100.00$");
        assertEquals("100.00$", t.getTreatmentCost());

    }

    /**
     * Liste d'arguments pour les tests qui nécéssites des claims
     */
    static Stream<Arguments> claimsSource() {
        return Stream.of(
                Arguments.of(new Claim(200, "2021-01-11", "0$")),
                Arguments.of(new Claim(100, "2021-01-01", "100$")),
                Arguments.of(new Claim(313,"2021-01-31", "1000000.00$")),
                Arguments.of(new Claim(400,"2021-02-31", "1000000,00$"))
        );
    }

}