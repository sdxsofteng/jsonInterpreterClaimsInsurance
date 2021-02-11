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
 * Cette classe expose des méthodes pour permettre la sérialisations/désérialisation de nos données.
 */
public class JacksonUtils {

    private final String INVALID_DATA_OUTPUT_PATH = "invalidData.json";
    private ObjectMapper mapper = generateAndConfigureMapper();

    //Cette méthode permet de générer le object mapper et de le configurer selon nos spécifications
    private ObjectMapper generateAndConfigureMapper() {
        ObjectMapper newMapper = new ObjectMapper();
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter(); //Instancie notre pretty printer
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE); //Ajuste le PP (format personnaliser)
        newMapper.setDefaultPrettyPrinter(prettyPrinter); //On set le PP comme PP par défaut sur notre mapper
        newMapper.registerModule(new JavaTimeModule());  //Introduction du time module pour gérer les dates
        newMapper.configure(SerializationFeature.INDENT_OUTPUT, true); //Ajoute l'indentation automatique en sortie
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

    //Lorsqu'une erreur est détectée on sort du programme et créer un fichier de sortie en JSON
    public void errorOutputToJsonFile(){
        File outputErrorFile = new File(INVALID_DATA_OUTPUT_PATH);
        ErrorOut errorData = new ErrorOut();
        try {
            mapper.writeValue(outputErrorFile, errorData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1); // Code de sortie 1 signifie une erreur de input
    }

    //Sortie normal du programme et transfert des objets CustomerOut et ClaimsOut en format JSON
    public void normalOutputToJsonFile(File path, CustomerOut customerOut){
        try {
            mapper.writeValue(path, customerOut);
            mapper.writerWithDefaultPrettyPrinter().writeValue(path, customerOut);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
}
