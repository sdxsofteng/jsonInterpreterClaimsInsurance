package models.output;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClaimOutTest {

    static ClaimOut claimout;
    static ClaimOut claimoutTest;

    @BeforeAll
    public static void setUp(){
        claimout = new ClaimOut();
        claimout.setClaimAmount(0f);
        claimoutTest = claimout;
    }

    @Test
    @DisplayName("Testing basic constructor")
    public void testConstructor(){
        assertEquals(claimout, claimoutTest);
    }

}
