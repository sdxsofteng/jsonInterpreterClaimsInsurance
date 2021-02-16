package utils;

public class ConversionUtils {

    public static float convertCostStringToFloat(String amount) {
        amount = replaceCommasByPeriods(amount);
        amount = removeDollarSigns(amount);
        return Float.parseFloat(amount);
    }

    public static String replaceCommasByPeriods(String amount) {
        return amount.replace(',', '.');
    }

    public static String removeDollarSigns(String amount) {
        return "0" + amount.replace("$", "");
    }
    
    public static String floatToString(float refundAmount){
        return String.format("%.2f$", refundAmount);
    }
}
