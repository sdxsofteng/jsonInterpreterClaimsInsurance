import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JacksonUtils {

    private static ObjectMapper mapper = GenerateAndConfigureMapper();

    private static ObjectMapper GenerateAndConfigureMapper() {

        ObjectMapper newMapper = new ObjectMapper();
        newMapper.registerModule(new JavaTimeModule());

        return newMapper;
    }

    public static File transformToFile(String argInput){
        File inputFile = null;

            inputFile = new File(argInput);

        return inputFile;

    }

    public static Customer JsonToObjectInput(File src){

        Customer programInput = null;
        try {
            programInput = mapper.readValue(src, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return programInput;
    }
    //TODO essayer de mettre les deux en un
    public static CareReference JsonToReference(File src){

        CareReference referenceInput = null;

        try {
            referenceInput = mapper.readValue(src, CareReference.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return referenceInput;
    }

    //TODO Faire la sortie en cas d'erreur fatale. Le doc JSON.
}
