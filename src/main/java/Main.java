import java.io.File;

public class Main {

    private static final String JSON_REFERENCE_PATH = "claimsReference.json";
    public static String outputPath;
    public static Customer customerInfo;
    public static CareReference referenceObject;
    public static JacksonUtils jUtil = new JacksonUtils();

    public static void main(String[] args) {
        verifyArgsLength(args.length);
        outputPath = args[1];
        initializeObjects(args[0], JSON_REFERENCE_PATH);
        Validation.ValidateInvoice(customerInfo, referenceObject);
    }

    public static void initializeObjects(String commandLineArgumentPath, String ressourceFileSource) {
        File firstCliArgument = new File(commandLineArgumentPath);
        customerInfo = jUtil.JsonToCustomerInput(firstCliArgument);
        File referenceFile = new File(JSON_REFERENCE_PATH);
        referenceObject = jUtil.JsonToReference(referenceFile);
    }

    private static void verifyArgsLength(int argsLength){
        if (argsLength != 2){
           jUtil.ErrorOutputToJsonFile();
        }
    }
}
