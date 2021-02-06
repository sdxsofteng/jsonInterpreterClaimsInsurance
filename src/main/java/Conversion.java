public class Conversion {

    public static float convertCostStringToFloat(String amount){
        amount = replaceCommasByPeriods(amount);
        amount = removeAllButNumbersAndPeriods(amount);
        return Float.parseFloat(amount);
    }

    public static String replaceCommasByPeriods(String value){
        return value.replace(',', '.');
    }

    public static String removeAllButNumbersAndPeriods(String value){
        return value.replaceAll("\\.(?![^.]+$)|[^0-9.]","");
    }
}
