package models.input;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CaresValuesTest {

    static CaresValues caresValues;

    /**
     * Initialisation d'un caresValues avec les valeurs par type de contrat
     */
    @BeforeAll
    public static void setUp() {
        List<ContractTypeValue> contractTypeValueList = new ArrayList();
        contractTypeValueList.add(new ContractTypeValue("A", 100f, 0.50f));
        contractTypeValueList.add(new ContractTypeValue("B", 0f, 0.70f));
        caresValues = new CaresValues("Test", 0, 100, contractTypeValueList);
    }

    @Test
    @DisplayName("GetContractTypeData should return null if the contract type is not included.")
    public void testGetContractTypeDataReturnsNullWhenContractIsInexistant() {
        ContractTypeValue returnValue = caresValues.getContractTypeData("F");
        assertNull(returnValue);
    }

    @Test
    @DisplayName("Verify if getMonthlyMaxAmountInCents works")
    public void testGetMonthlyMaxAmountInCents(){
        assertEquals( 0 , caresValues.getMonthlyMaxAmountInCents());
    }

    @ParameterizedTest(name = "Contract instance: {0}")
    @MethodSource("contractTypeObjectource")
    @DisplayName("GetContractTypeData should return the proper data object.")
    public void testGetContractTypeDataReturnsAssociatedObject(ContractTypeValue expectedContract) {
        String expectedContractType = expectedContract.getType();
        ContractTypeValue returnedType = caresValues.getContractTypeData(expectedContractType);
        String actualContractType = returnedType.getType();
        assertEquals(expectedContractType, actualContractType);
    }

    @ParameterizedTest(name = "Type: {0} => {1}")
    @MethodSource("contractMaxRefundsExpectedSource")
    @DisplayName("Refund max should match with the contract type value.")
    public void testGetMaxRefundShouldMatchContractType(String type, float expected) {
        float actual = caresValues.getMaxRefundAmount(type);
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Type: {0} => {1}")
    @MethodSource("contractPercentageRefundsExpectedSource")
    @DisplayName("Refund percentage should match with the contract type value.")
    public void testGetRefundPercentageShouldMatchContractType(String type, float expected) {
        float actual = caresValues.getRefundPercentage(type);
        assertEquals(expected, actual);
    }

    /**
     * Liste d'arguments pour les tests qui on besoin d'un type de contrat et un montant max de remboursement attendu.
     */
    static Stream<Arguments> contractMaxRefundsExpectedSource() {
        return Stream.of(
                Arguments.of("A", 100f), Arguments.of("B", 0f)
        );
    }

    /**
     * Liste d'arguments pour les tests qui on besoin dun type de contrat, un pourcentage de remboursement attendu.
     */
    static Stream<Arguments> contractPercentageRefundsExpectedSource() {
        return Stream.of(
                Arguments.of("A", 0.50f), Arguments.of("B", 0.70f)
        );
    }

    /**
     * Liste d'arguments pour les tests qui on besoin d'un object de valeurs de contrats.
     */
    static Stream<Arguments> contractTypeObjectource() {
        return Stream.of(
                Arguments.of(caresValues.getContractTypeValues().get(0)),
                Arguments.of(caresValues.getContractTypeValues().get(1))
        );
    }
}
