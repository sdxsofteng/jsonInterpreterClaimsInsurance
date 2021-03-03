package utils;

import models.input.CaresValues;
import models.input.Claim;
import models.input.ContractTypeValue;
import models.input.Customer;

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

public class RefundHandlerTest {

    static Customer customer;
    static CareReference careReference;

    @BeforeAll
     static void setUpCustomer() {
        Claim testClaim1 = new Claim(300, "2021-01-11", "0$");
        Claim testClaim2 = new Claim(0, "2021-01-01", "201$");
        Claim testClaim3 = new Claim(313,"2021-01-31", "1000000.00$");
        List<Claim> claimList = new ArrayList<>();
        claimList.add(testClaim1);
        claimList.add(testClaim2);
        claimList.add(testClaim3);
        customer = new Customer("A200002", "2021-01", claimList);
    }

    @BeforeAll
     static void setUpReference() {
        careReference = new CareReference();
        List<CaresValues> caresValues = new ArrayList<>();
        caresValues.add(new CaresValues("Test 1", 0, 0));
        caresValues.add(new CaresValues("Test 2", 300, 399));
        careReference.setCaresValuesList(caresValues);
    }

    @BeforeAll
    static void setUpReferenceTypes() {
        List<ContractTypeValue> contractTypeValueList1 = new ArrayList();
        List<ContractTypeValue> contractTypeValueList2 = new ArrayList();
        contractTypeValueList1.add(new ContractTypeValue("A", 100f, 0.50f));
        contractTypeValueList2.add(new ContractTypeValue("A", 0f, 0.70f));
        contractTypeValueList2.add(new ContractTypeValue("B", 0f, 0.70f));
        careReference.getCaresValuesList().get(0).setContractTypeValues(contractTypeValueList1);
        careReference.getCaresValuesList().get(1).setContractTypeValues(contractTypeValueList2);
    }

    @Test
    @DisplayName("getRelatedContractTypeObject should return the related contract type object.")
    public void testGetRelatedContractTypeObjectReturnsLinkedObject() {
        String contractType = customer.getContractType();
        RefundHandler refundHandler = new RefundHandler();
        for (Claim claim: customer.getClaimsList()) {
            ContractTypeValue actual = refundHandler.getRelatedContractTypeObject(claim, contractType, careReference);
            CaresValues expectedCare = careReference.getAppropriateCareObject(claim.getTreatmentNumber());
            ContractTypeValue expectedContractValues = expectedCare.getContractTypeData(contractType);
            assertEquals(expectedContractValues, actual);
        }
    }

    @ParameterizedTest
    @MethodSource("claimsSource")
    @DisplayName("Calculate refund returns the expected refund amount")
    public void testCalculateRefundReturnsCorrectValues(Claim claim, float expectedRefund) {
        RefundHandler refundHandler = new RefundHandler();
        float actual = refundHandler.calculateRefund(claim, customer.getContractType(), careReference);
        assertEquals(expectedRefund, actual);
    }

    @Test
    @DisplayName("Process refunds sets the amount in the care object as expected for each care.")
    public void testCalculateRefundReturnsCorrectValues() {
        RefundHandler refundHandler = new RefundHandler();
        refundHandler.processRefund(customer, careReference);
        List<Claim> claimList = customer.getClaimsList();
        assertEquals(0f, claimList.get(0).getRefundAmount());
        assertEquals(100f, claimList.get(1).getRefundAmount());
        assertEquals(700000f, claimList.get(2).getRefundAmount());
    }

    /**
     * Liste d'arguments pour les tests qui nécéssites une liste de claims et le retour attendu
     */
    static Stream<Arguments> claimsSource() {
        return Stream.of(
                Arguments.of(customer.getClaimsList().get(0), 0f),
                Arguments.of(customer.getClaimsList().get(1), 100f),
                Arguments.of(customer.getClaimsList().get(2), 700000f)
        );
    }
}
