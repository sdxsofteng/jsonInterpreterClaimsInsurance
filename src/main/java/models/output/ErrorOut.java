package models.output;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Cette classe représente le contenu dans le JSON sortant qui est retourné lorsqu'il y a une erreur.
 */
public class ErrorOut {

    private String message = ErrorMessage.DEFAULT_ERROR.getMessage();
    private String description;
    //private String errorCode;

    public ErrorOut() {
    }

    public ErrorOut(ErrorMessage error) {
        this.message = error.getMessage();
        this.description = error.getDescription();
    }

    public ErrorOut(ErrorMessage error, int claimNumber) {
        this.message = error.getMessage() + " pour la reclamation " + claimNumber;
        this.description = error.getDescription();
    }

    @JsonProperty("Erreur")
    public String getMessage() {
        return message;
    }

}
