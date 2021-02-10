package utils;

public class Conversion {

    public static float convertCostStringToFloat(String amount) {
        amount = replaceCommasByPeriods(amount);
        amount = removeAllButNumbersAndPeriods(amount);
        return Float.parseFloat(amount);
    }

    public static String replaceCommasByPeriods(String amount) {
        return amount.replace(',', '.');
    }

    public static String removeAllButNumbersAndPeriods(String amount) {
        return "0" + amount.replaceAll("\\.(?![^.]+$)|[^0-9.]","");
    }
}
