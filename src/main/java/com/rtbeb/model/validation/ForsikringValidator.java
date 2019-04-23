package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Forsikring;

/**
 * Validering av Forsikring-objekter. Inkluderer også validering av enkelte felt
 * i disse for bruk i UI-feedback på inputfelter.
 */
public class ForsikringValidator {

    //Minimum og maksimumsverdier
    private static int maxForsikringspremie = 100000;
    private static int minForsikringspremie = 500;
    private static int maxForsikringsbelop = 20000000;
    private static int minForsikringsbelop = 1000;

    public static boolean ForsikringIsValid(Forsikring forsikring){

        //Datafelt i Forsikringsklassen
        int forsikringspremie = forsikring.getForsikringspremie();
        int forsikringsbelop = forsikring.getForsikringsbeløp();
        String forsikringsbetingelser = forsikring.getForsikringsbetingelser();

        //Validerer alle felt
        if(forsikringspremieIsValid(forsikringspremie) && forsikringsbelopIsValid(forsikringsbelop) &&
                forsikringsbetingelserIsValid(forsikringsbetingelser)){
            return true;
        }
        return false;
    }

    public static boolean forsikringspremieIsValid(String forsikringspremie){

        int forsikringspremieParsed = 0;

        try {
            forsikringspremieParsed = Integer.parseInt(forsikringspremie);
        } catch(NumberFormatException e){
            return false;
        }

        return forsikringspremieIsValid(forsikringspremieParsed);
    }

    private static boolean forsikringspremieIsValid(int forsikringspremie){
        if(forsikringspremie >= minForsikringspremie && forsikringspremie <= maxForsikringspremie){
            return true;
        }
        return false;
    }

    public static boolean forsikringsbelopIsValid(String forsikringsbelop){

        int forsikringsbelopParsed = 0;
        try{
            forsikringsbelopParsed = Integer.parseInt(forsikringsbelop);
        } catch(NumberFormatException e){
            return false;
        }

        return forsikringsbelopIsValid(forsikringsbelopParsed);
    }

    private static boolean forsikringsbelopIsValid(int forsikringsbelop){
        if(forsikringsbelop >= minForsikringsbelop && forsikringsbelop <= maxForsikringsbelop ){
            return true;
        }
        return false;
    }

    public static boolean forsikringsbetingelserIsValid(String forsikringsbetingelser){
        if(!forsikringsbetingelser.isEmpty()){
            return true;
        }
        return false;
    }


}
