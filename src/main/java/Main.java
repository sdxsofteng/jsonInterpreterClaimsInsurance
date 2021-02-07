import java.io.File;
import java.io.InputStream;

public class Main {

    private static final String JSON_REFERENCE_PATH = "claimsReference.json";
    public static String outputPath;
    public static Customer customerInfo;
    public static CareReference referenceObject;
    public static RefundCalculation refundCalculation;
    public static JacksonUtils jUtil = new JacksonUtils();
    public static RefundCalculation refundCalculator = new RefundCalculation();

    public static void main(String[] args) {
        verifyArgsLength(args.length);
        outputPath = args[1];
        initializeObjects(args[0]);
        Validation.isValidInvoice(customerInfo, referenceObject);
        refundCalculation.refund(customerInfo, referenceObject);
    }

    public static void initializeObjects(String commandLineArgumentPath) {
        File firstCliArgument = new File(commandLineArgumentPath);
        customerInfo = jUtil.jsonToCustomerInput(firstCliArgument);
        InputStream referenceFile = Main.class.getClassLoader().getResourceAsStream(JSON_REFERENCE_PATH);
        referenceObject = jUtil.jsonToReference(referenceFile);
        refundCalculation = new RefundCalculation();
    }

    private static void verifyArgsLength(int argsLength){
        if (argsLength != 2){
           jUtil.errorOutputToJsonFile();
        }
    }
}
