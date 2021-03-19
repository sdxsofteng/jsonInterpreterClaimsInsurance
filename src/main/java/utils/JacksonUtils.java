package utils;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import models.analytics.Analytics;
import models.input.Customer;
import models.output.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Cette classe expose des méthodes pour permettre la sérialisations/désérialisation de nos données.
 * Note: nous n'acceptons pas les propriétés de plus dans les objets JSON
 */
public class JacksonUtils {

    static final String ANALYTICS_PATH = "analytics.json";
    private static String errorOutputPath = "output.json";
    private ObjectMapper mapper = generateAndConfigureMapper();

    public String getErrorOutputPath() {
        return errorOutputPath;
    }

    public void setErrorOutputPath(String path) {
        errorOutputPath = path;
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
            exitWithError(Message.ANALYTICS_FILE_READ_FAILURE);
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
            //analyticsFile.createNewFile();
            mapper.writerWithDefaultPrettyPrinter().writeValue(analyticsFile, analytics);
        } catch (IOException e) {
            exitWithError(Message.ANALYTICS_FILE_WRITE_FAILURE);
        }
    }

    //Transforme le input(JSON) en Objet Customer et Claims
    public Customer jsonToCustomerInput(File src){
        Customer programInput = null;
        try {
            programInput = mapper.readValue(src, Customer.class);
        } catch (JsonMappingException jme) {
            logStatsAndExitWithError(Message.INVALID_INPUT_FILE);
        } catch (IOException e) { exitWithError(Message.INVALID_INPUT_FILE); }
        return programInput;
    }

    //Transforme le référence(JSON) en objet CareReference, CareValues et ContractTypeValues
    public CareReference jsonToReference(InputStream src){
        CareReference referenceInput = null;
        try {
            referenceInput = mapper.readValue(src, CareReference.class);
        } catch (IOException e) {
            exitWithError(Message.INVALID_REFERENCE_FILE);
        }
        return referenceInput;
    }

    //Lorsqu'une erreur est détectée on sort du programme et crée un fichier de sortie en JSON
    public void logStatsAndExitWithError(Message error){
        logStatsAndExitWithError(error, 0);
    }

    public void logStatsAndExitWithError(Message error, int claimNumber){
        new AnalyticsHandler(ANALYTICS_PATH).addInvalidRequest().save();
        exitWithError(error, claimNumber);
    }

    public void exitWithError(Message error){
        exitWithError(error, 0);
    }

    public void exitWithError(Message error, int claimNumber){
        File outputErrorFile = new File(errorOutputPath);
        try {
            mapper.writeValue(outputErrorFile, new ErrorOut(error, claimNumber));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(error.getCode());
    }

    //Sortie normal du programme et transfert des objets CustomerOut et ClaimsOut en format JSON
    public void exitWithData(File path, CustomerOut customerOut, CareReference careReference){
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(path, customerOut);
            new AnalyticsHandler(ANALYTICS_PATH).countClaims(customerOut.getClaimsOutList(), careReference).save();
            System.exit(0);
        } catch (IOException e) {
            System.exit(Message.INVALID_OUTPUT_FILE.getCode());
        }
    }
}
