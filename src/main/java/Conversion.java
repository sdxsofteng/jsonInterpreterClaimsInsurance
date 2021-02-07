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
        value = "0" + value.replaceAll("\\.(?![^.]+$)|[^0-9.]","");
        return value;
    }
}
