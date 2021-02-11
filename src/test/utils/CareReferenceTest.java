package utils;

import models.input.CaresValues;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CareReferenceTest {

    static CareReference careReference;

    /**
     * Initialisation de CaresValues pour une liste fixe de test (et qui ne dépend pas d'un JSON)
     * Les ContractTypeValues ne nous sont pas utiles pour ces tests.
     */
    @BeforeAll
    public static void setUp() {
        careReference = new CareReference();
        List<CaresValues> caresValues = new ArrayList<>();
        caresValues.add(new CaresValues("Test 1", 0, 0));
        caresValues.add(new CaresValues("Test 2", 100, 100));
        caresValues.add(new CaresValues("Test 3", 300, 399));
        careReference.setCaresValuesList(caresValues);
    }

    @ParameterizedTest(name = "CareValue: {0} (Name if exists: {1})")
    @MethodSource("careNumberSource")
    @DisplayName("getCareObject should return the associate object for a given number, or null.")
    public void testGetAppropriateCareObjectShouldReturnAssociatedCaresValue(int careNumber, String expectedName) {
        CaresValues returnedCaresValues = careReference.getAppropriateCareObject(careNumber);
        String actualName = (returnedCaresValues == null ? null : returnedCaresValues.getCareName());
        assertEquals(expectedName, actualName);
    }

    /**
     * Liste d'arguments pour les tests qui nécéssites un numéros de soins.
     * Nous tentons tous les cas (soins qui n'existe pas, soin qui comporte un range, un numéro fixe, etc.
     */
    static Stream<Arguments> careNumberSource() {
        return Stream.of(
                Arguments.of(0, "Test 1"), Arguments.of(100, "Test 2"), Arguments.of(-100, null),
                Arguments.of(Integer.MAX_VALUE, null), Arguments.of(1000000, null),
                Arguments.of(300, "Test 3"), Arguments.of(399, "Test 3"), Arguments.of(350, "Test 3")
        );
    }
}
