package utils;

import models.analytics.Analytics;
import models.analytics.ClaimCount;
import models.input.CaresValues;
import models.output.ClaimOut;

import java.util.ArrayList;
import java.util.List;

/**
 * Notes importantes sur les statistiques:
 *  - Nous ne prenons pas comptes des appels incorrectes au programme (Arguments invalides)
 *    afin de ne pas fausser les stats, donc seulement les tentatives avec des arguments valides
 *    sont prisent en compte dans la valeur "requetes rejetees".
 *  - Nous stockons les soins en fonctions de leur nom pour simplifier les cas de soins avec une plage
 *    de nombres, ou dans le cas ou un soin change de nombre. Il serait facile de modifier le code
 *    pour prendre compte du numero plutot que du nom du soin.
 *  - La classe est construite de façon à toujours se retourner elle-même pour pouvoir enchainer les commandes
 */
public class AnalyticsHandler {
    Analytics analytics;
    JacksonUtils jacksonUtils;
    String analyticsPath;

    public AnalyticsHandler(Analytics analytics) {
        this.analytics = analytics;
    }

    public AnalyticsHandler(String path) {
        this.analyticsPath = path;
        this.jacksonUtils = new JacksonUtils();
        this.analytics = this.jacksonUtils.getAnalytics(path);
    }

    public AnalyticsHandler reset() {
        analytics.setNbRequestsApproved(0);
        analytics.setNbRequestsRejected(0);
        analytics.setDeclaredCares(new ArrayList<>());
        return this;
    }

    public AnalyticsHandler save() {
        jacksonUtils.setAnalytics(this.analytics, analyticsPath);
        return this;
    }

    public AnalyticsHandler output() {
        System.out.println(analytics);
        return this;
    }

    public AnalyticsHandler countClaims(List<ClaimOut> claims, CareReference careReference) {
        for (ClaimOut claim: claims) {
            CaresValues care = careReference.getAppropriateCareObject(claim.getTreatmentNumber());
            countClaim(care);
            addValidRequest();
        }
        return this;
    }

    private AnalyticsHandler countClaim(CaresValues care) {
        ClaimCount trackedClaim = analytics.getDeclaredCares()
                .stream().filter(c -> c.getCareName() == care.getCareName()).findAny().orElse(null);
        if (trackedClaim != null) {
            trackedClaim.setAmount(trackedClaim.getAmount() + 1);
        } else {
           analytics.getDeclaredCares().add(new ClaimCount(care.getCareName(), 1));
        }
        return this;
    }

    public AnalyticsHandler addInvalidRequest() {
        analytics.setNbRequestsRejected(analytics.getNbRequestsRejected() + 1);
        return this;
    }

    public AnalyticsHandler addValidRequest() {
        analytics.setNbRequestsApproved(analytics.getNbRequestsApproved() + 1);
        return this;
    }
}
