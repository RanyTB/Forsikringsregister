package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Reise.Reiseforsikring;

/**
 * For validering av reiseforsikringer i sin helhet.
 */
public class ReiseforsikringValidator extends ForsikringValidator{
    private static int maxForsikringssum = 1000000;
    private static int minForsikringssum = 1000;

    /**
     * Valiering av Reiseforsikring og datafelt i superklassen.
     * @param reiseforsikring
     * @return True hvis reiseforsikringen er gyldig.
     */
    public static boolean reiseforsikringIsValid(Reiseforsikring reiseforsikring){

        String forsikringsområde = reiseforsikring.getForsikringsområde();
        int forsikringssum = reiseforsikring.getForsikringssum();

        return ForsikringIsValid(reiseforsikring) && forsikringsområdeIsValid(forsikringsområde) &&
                forsikringsområdeIsValid(forsikringsområde) && forsikringssumIsValid(forsikringssum);
    }

    /**
     * @param forsikringsområde
     * @return Returnerer true hvis gyldig.
     */
    public static boolean forsikringsområdeIsValid(String forsikringsområde){
        if(forsikringsområde.length() > 2){
            return true;
        }
        return false;
    }

    /**
     * @param forsikringssum
     * @return Returnerer true hvis gyldig.
     */
    public static boolean forsikringssumIsValid(String forsikringssum){

        try{
            int forsikringssumParsed = Integer.parseInt(forsikringssum);
            return forsikringssumIsValid(forsikringssumParsed);
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * @param forsikringssum
     * @return Returnerer true hvis gyldig.
     */
    public static boolean forsikringssumIsValid(int forsikringssum){
        if(forsikringssum > minForsikringssum && forsikringssum < maxForsikringssum){
            return true;
        }
        return false;
    }

}
