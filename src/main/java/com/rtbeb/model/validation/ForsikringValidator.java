package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Forsikring;

public class ForsikringValidator {

    //Minimum og maksimumsverdier
    private static int maxForsikringspremie = 50000;
    private static int minForsikringspremie = 500;
    private static int maxForsikringsbelop = 2000000;
    private static int minForsikringsbelop = 1000;

    public static boolean ForsikringIsValid(Forsikring forsikring){

        //Datafelt i Forsikringsklassen
        int forsikringspremie = forsikring.getForsikringspremie();
        int forsikringsbelop = forsikring.getForsikringsbelop();
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
        } catch(NumberFormatException e){}

        if(forsikringspremieParsed > minForsikringspremie && forsikringspremieParsed < maxForsikringspremie){
            return true;
        }
        return false;
    }

    public static boolean forsikringspremieIsValid(int forsikringspremie){
        if(forsikringspremie > minForsikringspremie && forsikringspremie < maxForsikringspremie){
            return true;
        }
        return false;
    }

    public static boolean forsikringsbelopIsValid(String forsikringsbelop){

        int forsikringsbelopParsed = 0;
        try{
            forsikringsbelopParsed = Integer.parseInt(forsikringsbelop);
        } catch(NumberFormatException e){}

        if(forsikringsbelopParsed > minForsikringsbelop && forsikringsbelopParsed < maxForsikringsbelop ){
            return true;
        }
        return false;
    }

    public static boolean forsikringsbelopIsValid(int forsikringsbelop){
        if(forsikringsbelop > minForsikringsbelop && forsikringsbelop < maxForsikringsbelop ){
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
