package utils;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import models.analytics.Analytics;
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

    static final String ANALYTICS_PATH = "analytics.json";
    private String invalidOutputPath = "output.json";
    private ObjectMapper mapper = generateAndConfigureMapper();

    public void setInvalidOutputPath(String path) {
        invalidOutputPath = path;
    }

    //Cette méthode permet de générer le object mapper et de le configurer selon nos spécifications
    private ObjectMapper generateAndConfigureMapper() {
        ObjectMapper newMapper = new ObjectMapper();
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        newMapper.setDefaultPrettyPrinter(prettyPrinter);
        newMapper.registerModule(new JavaTimeModule());
        newMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return newMapper;
    }

    //Transforme le input(JSON) en Objet Analytics
    public Analytics getAnalytics(String path){
        Analytics analytics = null;
        try {
            analytics = readAnalyticsFromFile(path);
        } catch (IOException e) {
            quitProgramWithError();
        }
        return analytics;
    }

    private Analytics readAnalyticsFromFile(String path) throws IOException {
        File src = new File(path);
        Analytics analytics = new Analytics();
        if (src.isFile()) {
            analytics = mapper.readValue(src, Analytics.class);
        }
        return analytics;
    }

    //Sauvegarde les statistiques
    public void setAnalytics(Analytics analytics, String path) {
        try {
            File analyticsFile = new File(path);
            analyticsFile.createNewFile();
            mapper.writerWithDefaultPrettyPrinter().writeValue(analyticsFile, analytics);
        } catch (IOException e) {
            quitProgramWithError();
        }
    }

    //Transforme le input(JSON) en Objet Customer et Claims
    public Customer jsonToCustomerInput(File src){
        Customer programInput = null;
        try {
            programInput = mapper.readValue(src, Customer.class);
        } catch (IOException e) {
            quitProgramWithErrorAndTracking();
        }
        return programInput;
    }

    //Transforme le référence(JSON) en objet CareReference, CareValues et ContractTypeValues
    public CareReference jsonToReference(InputStream src){
        CareReference referenceInput = null;
        try {
            referenceInput = mapper.readValue(src, CareReference.class);
        } catch (IOException e) {
            quitProgramWithErrorAndTracking();
        }
        return referenceInput;
    }

    //Lorsqu'une erreur est détectée on sort du programme et créer un fichier de sortie en JSON
    public void quitProgramWithErrorAndTracking(){
        new AnalyticsHandler(ANALYTICS_PATH).addInvalidRequest().save();
        quitProgramWithError();
    }

    public void quitProgramWithError(){
        File outputErrorFile = new File(invalidOutputPath);
        ErrorOut errorData = new ErrorOut();
        try {
            mapper.writeValue(outputErrorFile, errorData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1); // Code de sortie 1 signifie une erreur de input
    }

    //Sortie normal du programme et transfert des objets CustomerOut et ClaimsOut en format JSON
    public void quitProgramWithData(File path, CustomerOut customerOut, CareReference careReference){
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(path, customerOut);
            new AnalyticsHandler(ANALYTICS_PATH).addValidRequest()
                    .countClaims(customerOut.getClaimsOutList(), careReference).save();
            System.exit(0);
        } catch (IOException e) {
            System.exit(2);
        }
    }
}
