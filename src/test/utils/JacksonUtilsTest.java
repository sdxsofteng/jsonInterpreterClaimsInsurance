package utils;


import models.input.CaresValues;
import models.output.ErrorMessage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.ValidationHandler.jUtil;

public class JacksonUtilsTest {

    static JacksonUtils jsonUtil;

    @BeforeAll
    static void setUp() {
        jsonUtil = new JacksonUtils();
    }

    @Test
    @DisplayName("A proper CareReference object is built from a given JSON InputStream.")
    public void testCareReferenceBuilderReturnsValidCareReference() {
        String contractTypeJsonData = "{ \"type\": \"A\", \"max\": 0, \"refundPercentage\": 0.25 }";
        String careJsonData = "{ \"name\": \"osteopathy\", \"careNumberMax\": 100, \"careNumberMin\": 100, " +
                "\"contractType\": [" + contractTypeJsonData + "] }";
        String referenceJsonData = "{\"cares\": [" + careJsonData + "]}";
        InputStream targetStream = new ByteArrayInputStream(referenceJsonData.getBytes());
        CareReference parsedRef = jsonUtil.jsonToReference(targetStream);
        CaresValues parsedCare = parsedRef.getCaresValuesList().get(0);
        assertEquals("osteopathy", parsedCare.getCareName());
        assertEquals("A", parsedCare.getContractTypeValues().get(0).getType());
    }

    @Test
    @DisplayName("Tests quitting program with default error code." +
            "Does not test message output.")
    @ExpectSystemExitWithStatus(-1)
    public void testQuitProgramWithErrorNoParams() {
        jUtil.quitProgramWithError();
    }

    @Test
    @DisplayName("Tests quitting program with parametrized error message." +
            "Does not test message output.")
    @ExpectSystemExitWithStatus(-4)
    public void testQuitProgramWithErrorMessageM20() {
        jUtil.quitProgramWithError(ErrorMessage.INVALID_INPUT_FILE);
    }

    @Test
    @DisplayName("Tests quitting program with parametrized error message." +
            "Does not test message output.")
    @ExpectSystemExitWithStatus(-10)
    public void testQuitProgramWithErrorMessageM40() {
        jUtil.quitProgramWithError(ErrorMessage.MISSING_FILENUMBER);
    }

    @Test
    @DisplayName("Tests quitting program with parametrized error message and cLaim number." +
            "Does not test message output.")
    @ExpectSystemExitWithStatus(-23)
    public void testQuitProgramWithErrorMessageAndClaimNumberM90() {
        jUtil.quitProgramWithError(ErrorMessage.INVALID_CLAIM_DATE, 2);
    }

    @Test
    @DisplayName("Tests quitting program with parametrized error message and cLaim number." +
            "Does not test message output.")
    @ExpectSystemExitWithStatus(-24)
    public void testQuitProgramWithErrorMessageAndClaimNumber() {
        jUtil.quitProgramWithError(ErrorMessage.MISSING_TREATMENT_COST, 3);
    }
}
