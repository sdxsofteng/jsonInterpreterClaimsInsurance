package utils;

import models.input.CaresValues;
import models.input.ContractTypeValue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    static Money m;
    static Money n;
    static CaresValues caresValues;
    static List<ContractTypeValue> contractTypeValueList;

    @BeforeAll
    public static void setUp(){
        contractTypeValueList = new ArrayList<>();
        contractTypeValueList.add(new ContractTypeValue("A", 50.00f, 0.75f));
        caresValues = new CaresValues("test", 1200, 1300, contractTypeValueList);
        caresValues.setMonthlyMaxAmountInCents(2000);
        m = new Money(40.00f);
        n = new Money(10.00f);
    }

    @Test
    @DisplayName("Test Adjust Monthly Max Amount Care if max is exceded")
    public void testAdjustMonthlyMaxAmountCare(){
        assertEquals(12.50f, m.calculateAmountToRefund(50.00f, 0.75f, caresValues));
    }

    @Test
    @DisplayName("Test Adjust Monthly Max Amount Care if max is not exceded")
    public void testAdjustMonthlyMaxAmountCare2(){
        assertEquals(7.50f, n.calculateAmountToRefund(50.00f, 0.75f, caresValues));
    }
}