package models.output;

public enum Message {
    DEFAULT_ERROR(-1, "Donnees Invalides"),

    MISSING_ARGUMENTS(-2, "Paramètres d'execution absents"),
    INCORRECT_ARGUMENTS(-3, "Paramètres d'execution invalides"),

    INVALID_INPUT_FILE(-4, "Fichier d'entee invalide"),
    INVALID_OUTPUT_FILE(-5, "Fichier sortie invalide"),

    INVALID_REFERENCE_FILE(-6, "Données internes invalides"),

    ANALYTICS_FILE_READ_FAILURE(-8, "Incapable de lire le fichier statistique"),
    ANALYTICS_FILE_WRITE_FAILURE(-9, "Incapable de mettre à jour le fichier statistique"),

    MISSING_FILENUMBER( -10, "Numéro de dossier inexistant"),
    INCORRECT_FILENUMBER( -11, "Numéro de dossier incorrect"),

    MISSING_INVOICE_DATE( -12, "Mois de reclamation inexistant"),
    INCORRECT_INVOICE_DATE( -13, "Mois de reclamation incorrect"),

    MISSING_CLAIMS( -14, "Reclamation inexistante"),

    MISSING_CARE_NO( -20, "Numero de soin manquant"),
    INVALID_CARE_NO( -21, "Numero de soin invalide"),

    MISSING_CLAIM_DATE( -22, "Date de soin manquante"),
    INVALID_CLAIM_DATE( -23, "Date de soin invalide"),

    MISSING_TREATMENT_COST( -24, "Montant de soin manquant"),
    INVALID_TREATMENT_COST( -25, "Montant de soin invalide"),
    INVALID_TREATMENT_COST_TOO_LOW( -26, "Montant de soin ne peut pas etre egal ou plus bas que 0"),
    MONTHLY_TREATMENT_COST_TOO_HIGH( -27, "Montant mensuel pour ce soin dépasse le maximum de 500$"),

    CLAIM_DATE_LIMIT_REACHED( -30, "Maximum de soins par jour dépasses"),

    OVER_MAX_INTEGER( -99, "Montant de reclamation trop eleve"),

    ;

    private int errorCode;
    private String errorMessage;

    Message(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }

    public int getCode() {
        return errorCode;
    }

}
