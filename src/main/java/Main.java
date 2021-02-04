import java.io.File;

public class Main {
    public static void main(String[] args) {

        //TODO mettre sa dans une methode
        File inputFile = JacksonUtils.transformToFile(args[0]);
        Customer customerInfo = JacksonUtils.JsonToObjectInput(inputFile);
        File referenceFile = JacksonUtils.transformToFile("src/main/java/claimsReference.json");
        CareReference referenceObject = JacksonUtils.JsonToReference(referenceFile);

    }
}
