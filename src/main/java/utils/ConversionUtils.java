package utils;

public class ConversionUtils {

    public static JacksonUtils jUtil = new JacksonUtils();

    public static String extractContractTypeFrom(String fileNumber) {
        String contractType = null;

        if (fileNumber.length() > 1) {
            contractType = fileNumber.substring(0, 1);
        } else {
            jUtil.quitProgramWithErrorAndTracking();
        }
        return contractType;
    }

    public static String extractClientNoFrom (String fileNumber) {
        String clientNo = null;

        if (fileNumber.length() > 1) {
            clientNo = fileNumber.substring(1);
        } else {
            jUtil.quitProgramWithErrorAndTracking();
        }
        return clientNo;
    }

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
