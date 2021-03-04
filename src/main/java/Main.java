import models.input.Customer;
import models.output.CustomerOut;

import utils.*;

import java.io.File;
import java.io.InputStream;

import static utils.ValidationHandler.validateArgs;

public class Main {

    static final String ANALYTICS_PATH = "analytics.json";
    private static final String JSON_REFERENCE_PATH = "claimsReference.json";
    public static Customer customerInfo;
    public static CareReference referenceObject;
    public static RefundHandler refundHandler;
    public static CustomerOut customerOut;
    public static JacksonUtils jUtil = new JacksonUtils();
    public static File normalOutputFile;


    public static void main(String[] args) {
        if (args.length == 0) { jUtil.quitProgramWithError(); }
        if (args[0].equals("-S")) {
            launchStatsDisplayProgram(args);
        } else if (args[0].equals("-SR")) {
            launchStatsResetProgram(args);
        } else {
            launchMainProgram(args);
        }
    }

    public static void launchMainProgram(String[] args) {
        validateArgs(args.length, 2);
        initializeObjects(args[0], args[1]);
        ValidationHandler.ValidateInvoice(customerInfo, referenceObject);
        refundHandler.processRefund(customerInfo, referenceObject);
        customerOut = OutputHandler.customerToOut(customerInfo);
        jUtil.quitProgramWithData(normalOutputFile, customerOut, referenceObject);
    }

    public static void launchStatsResetProgram(String[] args) {
        validateArgs(args.length, 1);
        AnalyticsHandler analyticsHandler = new AnalyticsHandler(ANALYTICS_PATH);
        analyticsHandler.reset().save();
        System.out.println("Statistiques remises à zéro.");
    }

    public static void launchStatsDisplayProgram(String[] args) {
        validateArgs(args.length, 1);
        AnalyticsHandler analyticsHandler = new AnalyticsHandler(ANALYTICS_PATH);
        analyticsHandler.output();
    }

    public static void initializeObjects(String commandLineArgOne, String commandLineArgTwo) {
        jUtil.setInvalidOutputPath(commandLineArgTwo);
        File firstCliArgument = new File(commandLineArgOne);
        customerInfo = jUtil.jsonToCustomerInput(firstCliArgument);
        InputStream referenceFile = Main.class.getClassLoader().getResourceAsStream(JSON_REFERENCE_PATH);
        referenceObject = jUtil.jsonToReference(referenceFile);
        refundHandler = new RefundHandler();
        normalOutputFile = new File(commandLineArgTwo);
    }
}
