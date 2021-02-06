public class Conversion {


    // to convert clientNo. to int ? Necessaire????????
    public static int convertStringToInt(String clientNum){
        return Integer.valueOf(clientNum);
    }

    // reformat A,B,C,D ?? Pas necessaire !!
    public static char convertStringToChar(String contractType){
        contractType = contractType.trim();
        char newChar = contractType.charAt(0);

        return newChar;
    }

    /**
     * Methode convertir String cout en float:
     *
     * retire le $
     * remplace les virgules par des points
     * retire les espaces
     * converti en float
     */
    public static float convertStringToFloat(String amount){

        amount = amount.replaceAll("\\s","");
        amount = amount.replace(',', '.');
        amount = amount.substring(0, amount.length() - 1);

        float newFloat = Float.parseFloat(amount);

        return newFloat;
    }


}
