package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Båt.Båt;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * @author Rany Tarek Bouorm - s236210
 * Validering av båter. Inkluderer validering av båt-objekter og validering av enkelte båtdata
 * for bruk i UI-feedback på inputfelter.
 */
public class BåtValidator {

    private static String registreringsnummerRegex = "^([A-ZÆØÅa-zæøå]{2}\\d{4}|[A-Za-z]{3}\\d{5})$";
    private static String merkeRegex = "^[a-zæøåA-ZÆØÅ\\d \\-]{2,50}$";
    private static String modellRegex = "^[a-zæøåA-ZÆØÅ\\- \\d]{2,50}$";
    private static String lengdeRegex = "^[0-9]{1,10}$";

    /**
     * Validerer om et båt-objekt er gyldig i forhold til klassens regler.
     * @param båt Båten som skal valideres
     * @return True hvis alle felt er gyldie.
     */
    public static boolean båtIsValid(Båt båt){
        String registreringsnummer = båt.getRegistreringsnummer();
        String merke = båt.getMerke();
        String modell = båt.getModell();
        String lengde = båt.getLengde();
        String årsmodell = båt.getÅrsmodell();

        return registreringsnummerIsValid(registreringsnummer) && merkeIsValid(merke) &&
                modellIsValid(modell) && lengdeIsValid(lengde) && årsmodellIsValid(årsmodell);
    }

    public static boolean registreringsnummerIsValid(String registreringsnummer){
        return Pattern.matches(registreringsnummerRegex, registreringsnummer);
    }
    public static boolean merkeIsValid(String merke){
        return Pattern.matches(merkeRegex, merke);
    }
    public static boolean modellIsValid(String modell){
        return Pattern.matches(modellRegex, modell);
    }
    public static boolean lengdeIsValid(String lengde){
        return Pattern.matches(lengdeRegex, lengde);
    }
    public static boolean årsmodellIsValid(String årsmodell){

        int årsmodellParsed;

        try{
            årsmodellParsed = Integer.parseInt(årsmodell);
        } catch(NumberFormatException e){
            return false;
        }

        int detteÅret = LocalDate.now().getYear();

        if(årsmodell.isEmpty()){
            return false;
        }
        else return årsmodellParsed <= detteÅret && årsmodellParsed > 1900;
    }

    public static boolean motorinfoIsValid(String motorinfo){
        return ! motorinfo.isEmpty();
    }

}
