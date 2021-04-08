package utils;

import models.input.CaresValues;
import models.input.ContractTypeValue;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    static Money m;
    static Money n;
    static Money o;
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
        o = new Money(100000000.00f);
    }

    @Test
    @DisplayName("Test adjust monthly max amount care if max is exceded")
    public void testAdjustMonthlyMaxAmountCare(){
        assertEquals(12.50f, m.calculateAmountToRefund(50.00f, 0.75f, caresValues));
    }

    @Test
    @DisplayName("Test adjust monthly max amount care if max is not exceded")
    public void testAdjustMonthlyMaxAmountCare2(){
        assertEquals(7.50f, n.calculateAmountToRefund(50.00f, 0.75f, caresValues));
    }

    @Test
    @DisplayName("Add money properly stores the added value")
    public void testAddMoneyStoresValue() {
        float baseAmount = 10.00f;
        float addedAmount = 15.83f;
        float expected = baseAmount + addedAmount;
        Money testMoney = new Money(baseAmount);
        testMoney.add(addedAmount);
        assertEquals(expected, testMoney.getValueInFloat());
    }

    @Test
    @DisplayName("Add money properly returns the result value")
    public void testAddMoneyReturnsResult() {
        float baseAmount = 10.00f;
        float addedAmount = 15.83f;
        float expected = baseAmount + addedAmount;
        float actual = new Money(baseAmount).add(addedAmount);
        assertEquals(expected, actual);
    }
}