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
 * et les maximums pour le care et le type de contrat associé à ces valeurs. Cette façon de représenté le contexte nous
 * permettra d'ajouter des types de contrats très facilement, et ce,
 * sans avoir besoin d'avoir les mêmes soins pour chaque type de contrat existant.
 */
public class CareReference {

    List<CaresValues> caresValuesList;

    public List<CaresValues> getCaresValuesList() {
        return caresValuesList;
    }

    //Dirige le parser dans la liste des CaresValues pour créer les objets CaresValues
    @JsonProperty("cares")
    public void setCaresValuesList(List<CaresValues> caresValuesList) {
        this.caresValuesList = caresValuesList;
    }

    //Permet de trouver le bon soin selon un numéro donné, si le numéro n'est pas valide, on retourne null.
    public CaresValues getAppropriateCareObject(int careNumber){
        CaresValues returnObject = null;
        for (CaresValues caresValue: caresValuesList){
            if (caresValue.getCareNumberMax() >= careNumber && caresValue.getCareNumberMin() <= careNumber){
                returnObject = caresValue;
            }
        }
        return returnObject;
    }
}
