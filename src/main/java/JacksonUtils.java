import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JacksonUtils {

    private static final String INVALID_DATA_OUTPUT_PATH = "invalidData.json";
    private static ObjectMapper mapper = GenerateAndConfigureMapper();


    private static ObjectMapper GenerateAndConfigureMapper() {
        ObjectMapper newMapper = new ObjectMapper();
        newMapper.registerModule(new JavaTimeModule());
        newMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return newMapper;
    }

    public static Customer JsonToCustomerInput(File src){
        Customer programInput = null;
        try {
            programInput = mapper.readValue(src, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();
            ErrorOutputToJsonFile();
        }
        return programInput;
    }

    public static CareReference JsonToReference(File src){
        CareReference referenceInput = null;
        try {
            referenceInput = mapper.readValue(src, CareReference.class);
        } catch (IOException e) {
            ErrorOutputToJsonFile();
        }
        return referenceInput;
    }

    public static void ErrorOutputToJsonFile(){
        File outputErrorFile = new File(INVALID_DATA_OUTPUT_PATH);
        ErrorOut errorData = new ErrorOut();
        try {
            mapper.writeValue(outputErrorFile, errorData);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
