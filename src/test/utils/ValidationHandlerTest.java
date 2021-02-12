package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import static utils.ValidationHandler.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class ValidationHandlerTest {
    
    @ParameterizedTest(name = "String: {0} => {1}")
    @MethodSource("numberStringsSource")
    @DisplayName("Number as String should be properly flagged as valid/invalid. (For client no)")
    public void testIsOnlyDigitsProperlyValidatesString(String potentialNumber, boolean expected) {
        boolean actual = isOnlyDigits(potentialNumber);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Day after the current day should be flagged invalid.")
    public void testIsTodayOrEarlierWithFutureDate() {
        LocalDate dateInFuture = LocalDate.now().plusDays(1); // The next day
        String formattedDateString = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(dateInFuture);
        boolean returnedValue = isTodayOrEarlier(formattedDateString);
        assertFalse(returnedValue);
    }

    @Test
    @DisplayName("Current day or before should be flagged as valid.")
    public void testIsTodayOrEarlierWithCurrentDate() {
        LocalDate dateInFuture = LocalDate.now();
        String formattedDateString = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(dateInFuture);
        boolean returnedValue = isTodayOrEarlier(formattedDateString);
        assertTrue(returnedValue);
    }

    @Test
    @DisplayName("Month after the current month should be flagged invalid.")
    public void testIsThisMonthOrEarlierWithFutureDate() {
        LocalDate dateInFuture = LocalDate.now().plusMonths(1); // The next month
        String formattedDateString = DateTimeFormatter.ofPattern("yyyy-MM").format(dateInFuture);
        boolean returnedValue = isThisMonthOrEarlier(formattedDateString);
        assertFalse(returnedValue);
    }

    @Test
    @DisplayName("Current month or before should be flagged as valid.")
    public void testIsThisMonthOrEarlierWithCurrentDate() {
        LocalDate dateInFuture = LocalDate.now();
        String formattedDateString = DateTimeFormatter.ofPattern("yyyy-MM").format(dateInFuture);
        boolean returnedValue = isThisMonthOrEarlier(formattedDateString);
        assertTrue(returnedValue);
    }

    @ParameterizedTest(name = "date: {0} => {1}")
    @MethodSource("yearMonthDayDatesSource")
    @DisplayName("Date in YYYY-MM should be properly flagged as invalid/valid.")
    public void testIsValidYearMonthAndDayDateIdentifiesDatesCorrectly(String date, boolean expected) {
        boolean actual = isValidDateFormatYMD(date);
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "date: {0} => {1}")
    @MethodSource("yearMonthDatesSource")
    @DisplayName("Date in YYYY-MM should be properly flagged as invalid/valid.")
    public void testIsValidYearAndMonthDateIdentifiesDatesCorrectly(String date, boolean expected) {
        boolean actual = isValidYearAndMonthDate(date);
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "date: {0} => {1}")
    @MethodSource("yearMonthDayAndEquivalentYYYYMMDatesSource")
    @DisplayName("Date in YYYY-MM-DD should be properly stripped down to YYYY-MM.")
    public void testIsValidYearAndMonthDateIdentifiesDatesCorrectly(String date, String expected) {
        String actual = removeDayFromDate(date);
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "cost: {0} => {1}")
    @MethodSource("costStringsSource")
    @DisplayName("Cost as string should be formatted as ##.##$ or ##,##$ .")
    public void testIsValidCostShouldBeOfProperFormat(String cost, boolean expected) {
        boolean actual = isValidCost(cost);
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "argsLength: {0}")
    @DisplayName("Program should fail if called with anything besides 2 arguments.")
    @ValueSource(ints = {0, 1, 2, 3, Integer.MAX_VALUE})
    public void testValidateArgsLengthIsOnlyValidWhenEqualTo2(int argsLength) {
        boolean expected = argsLength == 2;
        boolean actual = hasValidArgs(argsLength);
        assertEquals(expected, actual);
    }

    /**
     * Liste d'arguments pour les tests qui nécéssites une liste de cout.
     * Contient des couts invalides pour tester toutes les formulations potentielles d'un cout valide et invalide.
     */
    static Stream<Arguments> costStringsSource() {
        return Stream.of(
                Arguments.of("12.50$", true), Arguments.of("12,50$", true), Arguments.of("1.50$", true),
                Arguments.of("1,50$", true), Arguments.of("12$", true), Arguments.of("1$", true),
                Arguments.of("A,A$", false), Arguments.of("A.A$", false), Arguments.of("A$", false),
                Arguments.of("A", false), Arguments.of("A,A", false), Arguments.of("A.A", false),
                Arguments.of("1", false), Arguments.of("12", false), Arguments.of("12,50", false),
                Arguments.of("12.50", false)
        );
    }

    /**
     * Liste d'arguments pour les tests qui nécéssites une liste de dates sous format YYYY-MM.
     * Contient des dates invalides pour tester toutes les formulations potentielles d'une date valide et invalide.
     */
    static Stream<Arguments> yearMonthDatesSource() {
        return Stream.of(
                Arguments.of("2020-01", true), Arguments.of("2020-12", true), Arguments.of("2000-01", true),
                Arguments.of("1980-01", true), Arguments.of("2020-00", false), Arguments.of("0000-12", false),
                Arguments.of("2000-13", false), Arguments.of("1980-111", false)
        );
    }

    /**
     * Liste d'arguments pour les tests qui nécéssites une liste de dates sous format YYYY-MM-DD.
     * Contient des dates invalides pour tester toutes les formulations potentielles d'une date valide et invalide.
     */
    static Stream<Arguments> yearMonthDayDatesSource() {
        return Stream.of(
                Arguments.of("2020-01-01", true), Arguments.of("2020-12-31", true),
                Arguments.of("2000-01-01", true), Arguments.of("1980-01-01", true),
                Arguments.of("2020-00-01", false), Arguments.of("0000-12-12", false),
                Arguments.of("2000-13-10", false), Arguments.of("1980-111-01", false),
                Arguments.of("1980-11-011", false), Arguments.of("1980-02-31", false)
        );
    }

    /**
     * Liste d'arguments pour les tests qui nécéssites
     * une liste de dates sous format YYYY-MM-DD et sont équivalent en YYYY-MM.
     */
    static Stream<Arguments> yearMonthDayAndEquivalentYYYYMMDatesSource() {
        return Stream.of(
                Arguments.of("2020-01-01", "2020-01"),
                Arguments.of("2020-12-31", "2020-12"),
                Arguments.of("2020-11-01", "2020-11")
        );
    }

    /**
     * Liste d'arguments pour les tests qui nécéssites des nombres en String
     */
    static Stream<Arguments> numberStringsSource() {
        return Stream.of(
                Arguments.of("10", true),
                Arguments.of("1", true),
                Arguments.of("20000000", true),
                Arguments.of("2020-11-01", false),
                Arguments.of("100.0", false),
                Arguments.of("100$", false),
                Arguments.of("2020A", false),
                Arguments.of("2,2", false)
        );
    }
}
