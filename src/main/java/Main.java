import java.io.File;

public class Main {


    private static final String JSON_REFERENCE_PATH = "src/main/java/claimsReference.json";
    public static Customer customerInfo;
    public static CareReference referenceObject;

    public static void main(String[] args) {
        initializeObjects(args[0],JSON_REFERENCE_PATH);
    }

    public static void initializeObjects(String commandLineArgumentPath, String referencePath) {
        File firstCliArgument = new File(commandLineArgumentPath);
        customerInfo = JacksonUtils.JsonToCustomerInput(firstCliArgument);
        File referenceFile = new File(referencePath);
        referenceObject = JacksonUtils.JsonToReference(referenceFile);
    }
}
