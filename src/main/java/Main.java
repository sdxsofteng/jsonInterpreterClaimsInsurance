import java.io.File;

public class Main {

    private static final String JSON_REFERENCE_PATH = "claimsReference.json";
    public static String outputPath;
    public static Customer customerInfo;
    public static CareReference referenceObject;
    public static RefundCalculation refundCalculation;
    public static CustomerOut customerOut;
    public static JacksonUtils jUtil = new JacksonUtils();
    public static File normalOutputFile;


    public static void main(String[] args) {
        verifyArgsLength(args.length);
        initializeObjects(args[0], args[1]);
        Validation.isValidInvoice(customerInfo, referenceObject);
        refundCalculation.refund(customerInfo, referenceObject);
        customerOut = OutputObjects.customerToOut(customerInfo);
        jUtil.normalOutputToJsonFile(normalOutputFile, customerOut);
    }

    public static void initializeObjects(String commandLineArgOne, String commandLineArgTwo) {
        File firstCliArgument = new File(commandLineArgOne);
        customerInfo = jUtil.jsonToCustomerInput(firstCliArgument);
        File referenceFile = new File(JSON_REFERENCE_PATH);
        referenceObject = jUtil.jsonToReference(referenceFile);
        refundCalculation = new RefundCalculation();
        normalOutputFile = new File(commandLineArgTwo);
    }

    private static void verifyArgsLength(int argsLength){
        if (argsLength != 2){
           jUtil.errorOutputToJsonFile();
        }
    }
}
