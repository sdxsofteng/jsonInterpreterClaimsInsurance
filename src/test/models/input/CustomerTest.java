package models.input;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    static Customer customer;
    static Claim testClaim;
    static List<Claim> testClaimList;

    @BeforeAll
    public static void setUp(){
        customer = new Customer();
        testClaim = new Claim();
        testClaimList = new ArrayList<Claim>();
        testClaimList.add(testClaim);
    }

    @Test
    @DisplayName("Test set File Number")
    public void testSetFileNumber(){
        customer.setFileNumber("A123456");
        assertEquals("A", customer.getContractType());
        assertEquals("123456", customer.getClientNumber());
    }

    @Test
    @DisplayName("Test set Claims List")
    public void testSetClaimsList(){
        customer.setClaimsList(testClaimList);
        assertEquals(testClaim, customer.getClaimsList().get(0));
    }

}
