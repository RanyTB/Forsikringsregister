package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Reise.Reiseforsikring;

/**
 * @author Rany Tarek Bouorm - s236210
 * For validering av reiseforsikringer i sin helhet.
 */
public class ReiseforsikringValidator extends ForsikringValidator{
    private static int maxForsikringssum = 1000000;
    private static int minForsikringssum = 1000;

    /**
     * Valiering av Reiseforsikring og datafelt i superklassen.
     * @param reiseforsikring Reiseforsikringen som skal valideres.
     * @return True hvis reiseforsikringen er gyldig.
     */
    public static boolean reiseforsikringIsValid(Reiseforsikring reiseforsikring){

        String forsikringsområde = reiseforsikring.getForsikringsområde();
        int forsikringssum = reiseforsikring.getForsikringssum();

        return ForsikringIsValid(reiseforsikring) && forsikringsområdeIsValid(forsikringsområde) &&
                forsikringsområdeIsValid(forsikringsområde) && forsikringssumIsValid(forsikringssum);
    }

    /**
     * @param forsikringsområde forsikringsområdet som skal valideres.
     * @return Returnerer true hvis gyldig.
     */
    public static boolean forsikringsområdeIsValid(String forsikringsområde){
        return forsikringsområde.length() > 2;
    }

    /**
     * @param forsikringssum forsikringssummen som skal valideres.
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
     * @param forsikringssum forsikringssummen som skal valideres.
     * @return Returnerer true hvis gyldig.
     */
    private static boolean forsikringssumIsValid(int forsikringssum){
        return forsikringssum > minForsikringssum && forsikringssum < maxForsikringssum;
    }

}
