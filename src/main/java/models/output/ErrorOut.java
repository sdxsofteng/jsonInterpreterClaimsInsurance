package models.output;

/**
 * Cette classe représente le contenu dans le JSON sortant qui est retourné lorsqu'il y a une erreur.
 */
public class ErrorOut {

    private final String message = "Données invalides";

    public String getMessage() {
        return message;
    }
}
