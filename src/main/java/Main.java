import models.input.Customer;
import models.output.CustomerOut;

import utils.*;

import java.io.File;
import java.io.InputStream;

import static utils.ValidationHandler.hasValidArgs;

public class Main {

    private static final String JSON_REFERENCE_PATH = "claimsReference.json";
    public static String outputPath;
    public static Customer customerInfo;
    public static CareReference referenceObject;
    public static RefundHandler refundHandler;
    public static CustomerOut customerOut;
    public static JacksonUtils jUtil = new JacksonUtils();
    public static File normalOutputFile;


    public static void main(String[] args) {
        if (!hasValidArgs(args.length)) {
            jUtil.errorOutputToJsonFile();
        }
        initializeObjects(args[0], args[1]);
        ValidationHandler.ValidateInvoice(customerInfo, referenceObject);
        refundHandler.processRefund(customerInfo, referenceObject);
        customerOut = OutputHandler.customerToOut(customerInfo);
        jUtil.normalOutputToJsonFile(normalOutputFile, customerOut);
    }

    public static void initializeObjects(String commandLineArgOne, String commandLineArgTwo) {
        File firstCliArgument = new File(commandLineArgOne);
        customerInfo = jUtil.jsonToCustomerInput(firstCliArgument);
        InputStream referenceFile = Main.class.getClassLoader().getResourceAsStream(JSON_REFERENCE_PATH);
        referenceObject = jUtil.jsonToReference(referenceFile);
        refundHandler = new RefundHandler();
        normalOutputFile = new File(commandLineArgTwo);
    }
}
