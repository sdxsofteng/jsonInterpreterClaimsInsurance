package utils;

import models.input.CaresValues;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
