package models.output;

public enum ErrorMessage {
    DEFAULT_ERROR(-1, "Données Invalides (message d'erreur générique)", ""),

    MISSING_ARGUMENTS(-10, "Paramètres d'exécution absents", ""),
    INCORRECT_ARGUMENTS(-11, "Paramètres d'exécution invalides", ""),

    INVALID_INPUT_FILE(-20, "Fichier d'entée invalide", ""),

    INVALID_REFERENCE_FILE(-25, "Données internes invalides", ""),

    ANALYTICS_FILE_READ_FAILURE(-30, "Incapable de lire le fichier statistique", ""),
    ANALYTICS_FILE_WRITE_FAILURE(-31, "Incapable de mettre à jour le fichier statistique", ""),

    MISSING_FILENUMBER( -40, "Numéro de dossier inexistant", ""),
    INCORRECT_FILENUMBER( -41, "Numéro de dossier incorrect",
            "Un numéro valide doit comprendre une lettre majuscule suivie de six chiffres."),

    MISSING_INVOICE_DATE( -50, "Mois de réclamation inexistant", ""),
    INCORRECT_INVOICE_DATE( -51, "Mois de réclamation incorrect", ""),

    MISSING_CLAIMS( -60, "Réclamation inexistante", ""),

    MISSING_CARE_NO( -70, "Numéro de soin manquant", ""),
    INVALID_CARE_NO( -71, "Numéro de soin invalide", ""),

    MISSING_CLAIM_DATE( -80, "Date de soin manquante", ""),
    INVALID_CLAIM_DATE( -81, "Date de soin invalide", ""),

    MISSING_TREATMENT_COST( -90, "Montant de soin manquant", ""),
    INVALID_TREATMENT_COST( -91, "Montant de soin invalide", ""),

    ;

    private int errorCode;
    private String errorMessage;
    private String errorDescription;

    ErrorMessage(int errorCode, String errorMessage, String errorDescription) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDescription = errorDescription;
    }

    public String getMessage() {
        return errorMessage;
    }

    public int getCode() {
        return errorCode;
    }

    public String getDescription() {
        return errorDescription;
    }

}
