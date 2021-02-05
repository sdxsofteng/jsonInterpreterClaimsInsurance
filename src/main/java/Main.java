import java.io.File;

public class Main {
<<<<<<< src/main/java/Main.java

    private static final String JSON_REFERENCE_PATH = "src/main/java/claimsReference.json";
    public static Customer customerInfo;
    public static CareReference referenceObject;

    public static void main(String[] args) {
        initializeObjects(args[0],JSON_REFERENCE_PATH);
    }

    public static void initializeObjects(String commandLineArgumentPath, String referencePath){
        File firstCliArgument = new File(commandLineArgumentPath);
        customerInfo = JacksonUtils.JsonToCustomerInput(firstCliArgument);
        File referenceFile = new File(referencePath);
        referenceObject = JacksonUtils.JsonToReference(referenceFile);
=======
    public static void main(String[] args) {

        //TODO mettre sa dans une methode
        File inputFile = JacksonUtils.transformToFile(args[0]);
        Customer customerInfo = JacksonUtils.JsonToObjectInput(inputFile);
        File referenceFile = JacksonUtils.transformToFile("src/main/java/claimsReference.json");
        CareReference referenceObject = JacksonUtils.JsonToReference(referenceFile);

>>>>>>> src/main/java/Main.java
    }
}
