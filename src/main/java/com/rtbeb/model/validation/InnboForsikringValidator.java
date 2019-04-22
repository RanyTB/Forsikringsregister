package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Bolig.Bolig;
import com.rtbeb.model.base.forsikring.Bolig.Innboforsikring;
import com.rtbeb.model.base.forsikring.Validatable;

public class InnboForsikringValidator extends ForsikringValidator{


    private static int minForsikringssbeløpForBygning = 10000;
    private static int maxForsikringssbeløpForBygning = 30000000;
    private static int minForsikringssbeløpForInnbo = 10000;
    private static int maxForsikringssbeløpForInnbo = 1000000;


    public static boolean innboForsikringIsValid(Innboforsikring forsikring){

        Bolig bolig = forsikring.getBolig();
        int forsikringsbeløpBygning = forsikring.getForsikringssbeløpBygning();
        int forsikringsbeløpInnbo = forsikring.getForsikringsbeløpInnbo();

        return BoligValidator.boligIsValid(bolig) && forsikringssbeløpBygningIsValid(forsikringsbeløpBygning) &&
                forsikringsbeløpInnboIsValid(forsikringsbeløpInnbo);
    }

    public static boolean forsikringssbeløpBygningIsValid(String forsikringsbeløpForBygning){

        try{
            //Forsøker å parse og validere input, returnerer false hvis NumberformatException blir fanget.
            int forsikringsbeløpForBygningParsed = Integer.parseInt(forsikringsbeløpForBygning);
            return forsikringssbeløpBygningIsValid(forsikringsbeløpForBygningParsed);
        } catch(NumberFormatException e){
            return false;
        }

    }

    public static boolean forsikringssbeløpBygningIsValid(int forsikringsbeløpForBygning){
        return forsikringsbeløpForBygning > minForsikringssbeløpForBygning && forsikringsbeløpForBygning < maxForsikringssbeløpForBygning;
    }


    public static boolean forsikringsbeløpInnboIsValid(String forsikringsbeløpForInnbo){

        try{
            //Forsøker å parse og validere input, returnerer false hvis NumberformatException blir fanget.
            int forsikringsbeløpForInnboParsed = Integer.parseInt(forsikringsbeløpForInnbo);
            return forsikringssbeløpBygningIsValid(forsikringsbeløpForInnboParsed);
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean forsikringsbeløpInnboIsValid(int forsikringsbeløpForInnbo){
        return forsikringsbeløpForInnbo > minForsikringssbeløpForInnbo && forsikringsbeløpForInnbo < maxForsikringssbeløpForInnbo;
    }

}
