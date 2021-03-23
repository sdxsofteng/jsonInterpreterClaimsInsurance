package models.output;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Cette classe représente le contenu dans le JSON sortant qui est retourné lorsqu'il y a une erreur.
 */
public class ErrorOut {
    private String message = Message.DEFAULT_ERROR.getMessage();
    private int code;

    public ErrorOut(Message error) {
        this.message = error.getMessage();
        this.code = error.getCode();
    }

    public ErrorOut(Message error, int claimNumber) {
            this.message = error.getMessage() + " pour la reclamation " + claimNumber;
            this.code = error.getCode();
    }

    @JsonProperty("Erreur")
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
