package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Forsikring;

import java.util.regex.Pattern;

/**
 * @author Rany Tarek Bouorm - s236210
 * Validering av Forsikring-objekter. Inkluderer også validering av enkelte felt
 * i disse for bruk i UI-feedback på inputfelter.
 */
public class ForsikringValidator {

    //Minimum og maksimumsverdier
    private static int maxForsikringspremie = 100000;
    private static int minForsikringspremie = 500;
    private static int maxForsikringsbelop = 20000000;
    private static int minForsikringsbelop = 1000;
    private static String textTallRegex = "[a-zøæåA-ZÆØÅ0-9\\-\\n\\t@,.+:! ]{2,100}";

    /**
     * Validerer felt i en generell forsikring (forsikringspremie, forsikringsbeløp og betingelser.
     * @param forsikring forsikringen som skal valideres.
     * @return True hvis forsikringens felt er gyldige.
     */
    static boolean ForsikringIsValid(Forsikring forsikring){

        int forsikringspremie = forsikring.getForsikringspremie();
        int forsikringsbelop = forsikring.getForsikringsbeløp();
        String forsikringsbetingelser = forsikring.getForsikringsbetingelser();

        return forsikringspremieIsValid(forsikringspremie) && forsikringsbelopIsValid(forsikringsbelop) &&
                forsikringsbetingelserIsValid(forsikringsbetingelser);
    }

    public static boolean forsikringspremieIsValid(String forsikringspremie){

        int forsikringspremieParsed;

        try {
            forsikringspremieParsed = Integer.parseInt(forsikringspremie);
        } catch(NumberFormatException e){
            return false;
        }

        return forsikringspremieIsValid(forsikringspremieParsed);
    }

    private static boolean forsikringspremieIsValid(int forsikringspremie){
        return forsikringspremie >= minForsikringspremie && forsikringspremie <= maxForsikringspremie;
    }

    public static boolean forsikringsbelopIsValid(String forsikringsbelop){

        int forsikringsbelopParsed;
        try{
            forsikringsbelopParsed = Integer.parseInt(forsikringsbelop);
        } catch(NumberFormatException e){
            return false;
        }

        return forsikringsbelopIsValid(forsikringsbelopParsed);
    }

    private static boolean forsikringsbelopIsValid(int forsikringsbelop){
        return forsikringsbelop >= minForsikringsbelop && forsikringsbelop <= maxForsikringsbelop;
    }

    public static boolean forsikringsbetingelserIsValid(String forsikringsbetingelser){
        return Pattern.matches(textTallRegex, forsikringsbetingelser);
    }


}
