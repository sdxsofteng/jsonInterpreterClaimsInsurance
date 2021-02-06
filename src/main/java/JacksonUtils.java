import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class JacksonUtils {

    private final String INVALID_DATA_OUTPUT_PATH = "invalidData.json";
    private ObjectMapper mapper = generateAndConfigureMapper();



    private ObjectMapper generateAndConfigureMapper() {
        ObjectMapper newMapper = new ObjectMapper();
        newMapper.registerModule(new JavaTimeModule());
        newMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return newMapper;
    }

    public Customer jsonToCustomerInput(File src){
        Customer programInput = null;
        try {
            programInput = mapper.readValue(src, Customer.class);
        } catch (IOException e) {
            errorOutputToJsonFile();
        }
        return programInput;
    }

    public CareReference jsonToReference(InputStream src){
        CareReference referenceInput = null;
        try {
            referenceInput = mapper.readValue(src, CareReference.class);
        } catch (IOException e) {
            errorOutputToJsonFile();
        }
        return referenceInput;
    }

    public void errorOutputToJsonFile(){
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
