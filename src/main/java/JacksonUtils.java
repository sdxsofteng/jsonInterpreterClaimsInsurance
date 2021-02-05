import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class JacksonUtils {

    private static ObjectMapper mapper = GenerateAndConfigureMapper();


    private static ObjectMapper GenerateAndConfigureMapper() {
        ObjectMapper newMapper = new ObjectMapper();
        newMapper.registerModule(new JavaTimeModule());
        return newMapper;
    }

    public static Customer JsonToCustomerInput(File src){
        Customer programInput = null;
        try {
            programInput = mapper.readValue(src, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return programInput;
    }

    public static CareReference JsonToReference(File src){
        CareReference referenceInput = null;
        try {
            referenceInput = mapper.readValue(src, CareReference.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return referenceInput;
    }
}
