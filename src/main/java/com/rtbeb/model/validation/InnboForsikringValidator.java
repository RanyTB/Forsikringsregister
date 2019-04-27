package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Bolig.Bolig;
import com.rtbeb.model.base.forsikring.Bolig.Innboforsikring;

/**
 * @author Rany Tarek Bouorm - s236210
 * For validering av innboforsikringer
 */
public class InnboForsikringValidator extends ForsikringValidator{


    private static int minForsikringssbeløpForBygning = 10000;
    private static int maxForsikringssbeløpForBygning = 10000000;
    private static int minForsikringssbeløpForInnbo = 10000;
    private static int maxForsikringssbeløpForInnbo = 1000000;


    /**
     * Validerer en innboforsikring i sin helhet, med tilknyttet bolig.
     * @param forsikring Forsikringen som skal vurderes.
     * @return True hvis Innboforsikringen er gyldig.
     */
    public static boolean innboForsikringIsValid(Innboforsikring forsikring){

        Bolig bolig = forsikring.getBolig();
        int forsikringsbeløpBygning = forsikring.getForsikringssbeløpBygning();
        int forsikringsbeløpInnbo = forsikring.getForsikringsbeløpInnbo();

        return ForsikringValidator.ForsikringIsValid(forsikring) && BoligValidator.boligIsValid(bolig) &&
                forsikringsbeløpBygningIsValid(forsikringsbeløpBygning) && forsikringsbeløpInnboIsValid(forsikringsbeløpInnbo);
    }

    public static boolean forsikringsbeløpBygningIsValid(String forsikringsbeløpForBygning){

        try{
            //Forsøker å parse og validere input, returnerer false hvis NumberformatException blir fanget.
            int forsikringsbeløpForBygningParsed = Integer.parseInt(forsikringsbeløpForBygning);
            return forsikringsbeløpBygningIsValid(forsikringsbeløpForBygningParsed);

        } catch(NumberFormatException e){
            return false;
        }

    }

    private static boolean forsikringsbeløpBygningIsValid(int forsikringsbeløpForBygning){
        return forsikringsbeløpForBygning >= minForsikringssbeløpForBygning && forsikringsbeløpForBygning <= maxForsikringssbeløpForBygning;
    }


    public static boolean forsikringsbeløpInnboIsValid(String forsikringsbeløpForInnbo){

        try{
            //Forsøker å parse og validere input, returnerer false hvis NumberformatException blir fanget.
            int forsikringsbeløpForInnboParsed = Integer.parseInt(forsikringsbeløpForInnbo);
            return forsikringsbeløpInnboIsValid(forsikringsbeløpForInnboParsed);
        } catch(NumberFormatException e){
            return false;
        }
    }

    private static boolean forsikringsbeløpInnboIsValid(int forsikringsbeløpForInnbo){
        return forsikringsbeløpForInnbo >= minForsikringssbeløpForInnbo && forsikringsbeløpForInnbo <= maxForsikringssbeløpForInnbo;
    }

}
