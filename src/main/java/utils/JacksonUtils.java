package utils;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import models.input.Customer;
import models.output.CustomerOut;
import models.output.ErrorOut;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
/**
 * Cette classe gère tous les transferts de JSON entre l'intérieur et l'extérieur.
 */
public class JacksonUtils {

    private final String INVALID_DATA_OUTPUT_PATH = "invalidData.json";
    private ObjectMapper mapper = generateAndConfigureMapper();

    //Cette méthode permet de générer le mapper et de le configurer selon nos spécifications
    private ObjectMapper generateAndConfigureMapper() {
        ObjectMapper newMapper = new ObjectMapper();
        newMapper.registerModule(new JavaTimeModule());  //Introduction du time module pour gérer les dates
        newMapper.configure(SerializationFeature.INDENT_OUTPUT, true); //Ajoute identation automatique sortie
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter(); //Configration du pretty printer (PP)
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE); //Ajuste le PP pour formater la sortie
        //.. tel que demandé
        newMapper.setDefaultPrettyPrinter(prettyPrinter); //On set le PP comme PP par défaut sur notre mapper
        return newMapper;
    }

    //Transforme le input(JSON) en Objet Customer et Claims
    public Customer jsonToCustomerInput(File src){
        Customer programInput = null;
        try {
            programInput = mapper.readValue(src, Customer.class);
        } catch (IOException e) {
            errorOutputToJsonFile();
        }
        return programInput;
    }

    //Transforme le référence(JSON) en objet CareReference, CareValues et ContractTypeValues
    public CareReference jsonToReference(InputStream src){
        CareReference referenceInput = null;
        try {
            referenceInput = mapper.readValue(src, CareReference.class);
        } catch (IOException e) {
            errorOutputToJsonFile();
        }
        return referenceInput;
    }

    //Losrqu'une erreure est détectée on sort du programme et créer un fichier de sortie en JSON
    public void errorOutputToJsonFile(){
        File outputErrorFile = new File(INVALID_DATA_OUTPUT_PATH);
        ErrorOut errorData = new ErrorOut();
        try {
            mapper.writeValue(outputErrorFile, errorData);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    //Sortie normal du programme et transfert des objets CustomerOut et ClaimsOut en format JSON
    public void normalOutputToJsonFile(File path, CustomerOut customerOut){
        try {
            mapper.writeValue(path, customerOut);
            mapper.writerWithDefaultPrettyPrinter().writeValue(path, customerOut);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
