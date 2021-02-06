import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    private static final String JSON_REFERENCE_PATH = "src/main/java/claimsReference.json";
    public static Customer customerInfo;
    public static CareReference referenceObject;

    public static void main(String[] args) {
        initializeObjects(args[0],JSON_REFERENCE_PATH);
        Validation.isValidInvoice(customerInfo, referenceObject);
    }

    public static void initializeObjects(String commandLineArgumentPath, String referencePath){
        File firstCliArgument = new File(commandLineArgumentPath);
        customerInfo = JacksonUtils.JsonToCustomerInput(firstCliArgument);
        File referenceFile = new File(referencePath);
        referenceObject = JacksonUtils.JsonToReference(referenceFile);
    }


// TODO: 2021-02-05 Mettre en place une sortie d'erreur 
    
    public final static String ERROR_MESSAGE
            = "{\n    \"message\": \"Données invalides\"\n}";
    public final static String FILEPATH = "refunds.json"; // remplacer par l'input de sortie

    /**
     * Methode pour rejeter une demande
     *
     * Ecrit un fichier JSON contenant le message d'erreur.
     */
    public static void outputErrorMessage(){
        try {
            FileWriter fileToWrite = new FileWriter(FILEPATH);
            PrintWriter output = new PrintWriter(fileToWrite);
            output.println(ERROR_MESSAGE);
            output.close();
        } catch (IOException e){
        }
    }

}
