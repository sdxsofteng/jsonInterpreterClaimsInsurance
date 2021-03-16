package models.output;

public enum ErrorMessage {
    DEFAULT_ERROR(-1, "Données Invalides", ""),

    MISSING_ARGUMENTS(-2, "Paramètres d'exécution absents", ""),
    INCORRECT_ARGUMENTS(-3, "Paramètres d'exécution invalides", ""),

    INVALID_INPUT_FILE(-4, "Fichier d'entée invalide", ""),

    INVALID_REFERENCE_FILE(-6, "Données internes invalides", ""),

    ANALYTICS_FILE_READ_FAILURE(-8, "Incapable de lire le fichier statistique", ""),
    ANALYTICS_FILE_WRITE_FAILURE(-9, "Incapable de mettre à jour le fichier statistique", ""),

    MISSING_FILENUMBER( -10, "Numéro de dossier inexistant", ""),
    INCORRECT_FILENUMBER( -11, "Numéro de dossier incorrect",
            "Un numéro valide doit comprendre une lettre majuscule suivie de six chiffres."),

    MISSING_INVOICE_DATE( -12, "Mois de réclamation inexistant", ""),
    INCORRECT_INVOICE_DATE( -13, "Mois de réclamation incorrect", ""),

    MISSING_CLAIMS( -14, "Réclamation inexistante", ""),

    MISSING_CARE_NO( -20, "Numéro de soin manquant", ""),
    INVALID_CARE_NO( -21, "Numéro de soin invalide", ""),

    MISSING_CLAIM_DATE( -22, "Date de soin manquante", ""),
    INVALID_CLAIM_DATE( -23, "Date de soin invalide", ""),

    MISSING_TREATMENT_COST( -24, "Montant de soin manquant", ""),
    INVALID_TREATMENT_COST( -25, "Montant de soin invalide", ""),

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
