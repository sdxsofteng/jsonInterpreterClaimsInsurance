package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static utils.ConversionUtils.*;

public class ConversionUtilsTest {

    @ParameterizedTest(name = "extract contract type from file number")
    @MethodSource("fileNoToContractTypeSource")
    @DisplayName("Contract type extracted from file number should only be the first character")
    public void testExtractContractTypeFrom(String fileNumber, String expected) {
        assertEquals(expected, extractContractTypeFrom(fileNumber));
    }

    @ParameterizedTest(name = "extract client number from file number")
    @MethodSource("fileNoToClientNumberSource")
    @DisplayName("Client number extracted from file number should be everything after the first character")
    public void testExtractClientNoFrom(String fileNumber, String expected) {
        assertEquals(expected, extractClientNoFrom(fileNumber));
    }

    @ParameterizedTest(name = "cost String to float: {0} => {1}")
    @MethodSource("costStringToFloatSource")
    @DisplayName("Cost as String should be properly parsed as a float of the same value.")
    public void testCostStringToFloat(String cost, float expected) {
        assertEquals(expected, convertCostStringToFloat(cost));
    }

    @ParameterizedTest(name = "cost float to String: {0} => {1}")
    @MethodSource("costFloatToStringSource")
    @DisplayName("Cost as float should be formatted as ##.##$.")
    public void testCostFloatToString(float cost, String expected) {
        assertEquals(expected, floatToString(cost));
    }

    @ParameterizedTest(name = "cost comma to period: {0} => {1}")
    @MethodSource("costCommaToPeriodSource")
    @DisplayName("Cost as String should be formatted as ##.##$ .")
    public void testCostCommaToPeriod(String inputCost, String expected) {
        assertEquals(expected, replaceCommasByPeriods(inputCost));
    }

    @ParameterizedTest(name = "cost string remove $: {0}")
    @MethodSource("costStripStringSource")
    @DisplayName("Cost as String should get stripped of $.")
    public void testCostCommaToPeriod(String inputCost) {
        String methodResult = removeDollarSigns(inputCost);
        boolean isValid = !methodResult.contains("$");
        assertTrue(isValid);
    }

    @ParameterizedTest(name = "date: {0} => {1}")
    @MethodSource("yearMonthDayAndEquivalentYYYYMMDatesSource")
    @DisplayName("Date in YYYY-MM-DD should be properly stripped down to YYYY-MM.")
    public void testIsValidYearAndMonthDateIdentifiesDatesCorrectly(String date, String expected) {
        String actual = removeDayFromDate(date);
        assertEquals(expected, actual);
    }

    /**
     * Liste d'arguments pour les tests qui n??cessitent un num??ro de dossier (extraction du type de contrat)
     */
    static Stream<Arguments> fileNoToContractTypeSource() {
        return Stream.of(
                Arguments.of("A256582", "A"),
                Arguments.of("R2-D2", "R"),
                Arguments.of("gustave", "g"),
                Arguments.of("42", "4")
        );
    }

    /**
     * Liste d'arguments pour les tests qui n??cessitent un num??ro de dossier (extraction du type de contrat)
     */
    static Stream<Arguments> fileNoToClientNumberSource() {
        return Stream.of(
                Arguments.of("A256582", "256582"),
                Arguments.of("R2-D2", "2-D2"),
                Arguments.of("gustave", "ustave"),
                Arguments.of("42", "2")
        );
    }

    /**
     * Liste d'arguments pour les tests qui n??cessitent une liste de co??ts. (String -> float)
     */
    static Stream<Arguments> costStringToFloatSource() {
        return Stream.of(
                Arguments.of("12.50$", 12.50f), Arguments.of("12,50$", 12.50f), Arguments.of("1.50$", 1.50f),
                Arguments.of("1,50$", 1.50f), Arguments.of("12$", 12f), Arguments.of("1$", 1f)
        );
    }

    /**
     * Liste d'arguments pour les tests qui n??cessitent une liste de co??ts. (float -> String)
     */
    static Stream<Arguments> costFloatToStringSource() {
        return Stream.of(
                Arguments.of(12.50f, "12.50$"),
                Arguments.of(1.50f, "1.50$"),
                Arguments.of(12f, "12.00$"),
                Arguments.of(1f, "1.00$")
        );
    }

    /**
     * Liste d'arguments pour les tests qui n??cessitent une liste de co??ts. (virgule vers point)
     */
    static Stream<Arguments> costCommaToPeriodSource() {
        return Stream.of(
                Arguments.of("12,50$", "12.50$"), Arguments.of("1,50$", "1.50$"), Arguments.of("12,00$", "12.00$"),
                Arguments.of("1,00$", "1.00$"), Arguments.of("12.50$", "12.50$"), Arguments.of("1.50$", "1.50$"),
                Arguments.of("12$", "12$"), Arguments.of("1$", "1$")
        );
    }

    /**
     * Liste d'arguments pour les tests qui n??cessitent une liste de co??ts. (Retire les $ et autres)
     */
    static Stream<Arguments> costStripStringSource() {
        return Stream.of(
                Arguments.of("12.50$"),
                Arguments.of("1.50$"),
                Arguments.of("12$"),
                Arguments.of("1$")
        );
    }

    /**
     * Liste d'arguments pour les tests qui n??c??ssites
     * une liste de dates sous format YYYY-MM-DD et sont ??quivalent en YYYY-MM.
     */
    static Stream<Arguments> yearMonthDayAndEquivalentYYYYMMDatesSource() {
        return Stream.of(
                Arguments.of("2020-01-01", "2020-01"),
                Arguments.of("2020-12-31", "2020-12"),
                Arguments.of("2020-11-01", "2020-11")
        );
    }

}
