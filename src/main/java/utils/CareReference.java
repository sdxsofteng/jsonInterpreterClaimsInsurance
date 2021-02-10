package utils;
import com.fasterxml.jackson.annotation.JsonProperty;

import models.input.CaresValues;

import java.util.List;

/**
 * Cette classe contient toutes les informations par rapport au JSON de référence.
 *
 * La logique de nos références va comme suit:
 * 1) Nous avons un objet CareReference qui contient toutes les informations de références.
 * 2) Dans cet objet CareReference nous avons un tableau de CaresValues qui contient tous les cares en référence, leur
 * numéro max et minimum
 * 3)Dans tous ces objets CaresValues nous avons des objets de type ContractTypeValues qui contiennent les pourcentages
 * et les maximums pour le care spécifique et le contrat.
 */
public class CareReference {

    List<CaresValues> caresValuesList;

    public List<CaresValues> getCaresValuesList() {
        return caresValuesList;
    }

    @JsonProperty("cares")  //Dirige le parser dans la liste des CaresValues pour creer les objets CaresValues
    private void setCaresValuesList(List<CaresValues> caresValuesList) {
        this.caresValuesList = caresValuesList;
    }

    //Permet de trouver le bon soin selon le numéro, si le numéro n'est pas valide, on retourne null.
    public CaresValues getAppropriateCareObject(int referenceNumber){
        CaresValues returnedObject = null;
        for (CaresValues caresValues : caresValuesList){
            if (caresValues.getCareNumberMax() >= referenceNumber
                    && caresValues.getCareNumberMin() <= referenceNumber){
                returnedObject = caresValues;
            }
        }
        return returnedObject;
    }
}
