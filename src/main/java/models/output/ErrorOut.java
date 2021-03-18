package models.output;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Cette classe représente le contenu dans le JSON sortant qui est retourné lorsqu'il y a une erreur.
 */
public class ErrorOut {
    private String message = Message.DEFAULT_ERROR.getMessage();

    public ErrorOut() {
    }

    public ErrorOut(Message error) {
        this.message = error.getMessage();
    }

    public ErrorOut(Message error, int claimNumber) {
        if (claimNumber > 0) {
            this.message = error.getMessage() + " pour la reclamation " + claimNumber;
        } else {
            this.message = error.getMessage();
        }
    }

    @JsonProperty("Erreur")
    public String getMessage() {
        return message;
    }
}
