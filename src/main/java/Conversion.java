public class Conversion {

    public static float convertCostStringToFloat(String amount){
        amount = replaceCommasByPeriods(amount);
        amount = removeAllButNumbersAndPeriods(amount);
        amount = removeAllButLastPeriod(amount);
        return Float.parseFloat(amount);
    }

    public static String replaceCommasByPeriods(String value){
        return value.replace(',', '.');
    }

    public static String removeAllButNumbersAndPeriods(String value){
        return value.replaceAll("[^.\\d]","");
    }

    public static String removeAllButLastPeriod(String value){
        String newValue = "";

        String[] result = value.split("\\.");
        for (int i=0; i < result.length; i++) {
            if (i < result.length -1 ){
                newValue = newValue + result[i];
            } else {
                newValue = newValue + "." + result[i];
            }
        }
        return newValue;
    }
}
