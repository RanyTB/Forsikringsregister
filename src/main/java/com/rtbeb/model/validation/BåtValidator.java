package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Båt;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Validering av båter. Inkluderer validering av båt-objekter og validering av enkelte båtdata
 * for bruk i UI-feedback på inputfelter.
 */
public class BåtValidator {

    private static String registreringsnummerRegex = "^([A-ZÆØÅa-zæøå]{2}\\d{4}|[A-Za-z]{3}\\d{5})$";
    private static String merkeRegex = "^[a-zæøåA-ZÆØÅ\\d \\-]{2,50}$";
    private static String modellRegex = "^[a-zæøåA-ZÆØÅ\\- \\d]{2,50}$";
    private static String lengdeRegex = "^[0-9]{1,10}$";
    //private static String årsmodellRegex = "^(19)[0-9]{2}|(20)[0-1][0-9]$";

    public static boolean båtIsValid(Båt båt){
        String registreringsnummer = båt.getRegistreringsnummer();
        String merke = båt.getMerke();
        String modell = båt.getModell();
        String lengde = båt.getLengde();
        String årsmodell = båt.getÅrsmodell();

        if(registreringsnummerIsValid(registreringsnummer) && merkeIsValid(merke) &&
            modellIsValid(modell) && lengdeIsValid(lengde) && årsmodellIsValid(årsmodell)){
            return true;
        }
        return false;
    }

    public static boolean registreringsnummerIsValid(String registreringsnummer){
        if(Pattern.matches(registreringsnummerRegex, registreringsnummer)){
            return true;
        }
        return false;
    }
    public static boolean merkeIsValid(String merke){
        if(Pattern.matches(merkeRegex,merke)){
            return true;
        }
        return false;
    }
    public static boolean modellIsValid(String modell){
        if(Pattern.matches(modellRegex,modell)){
            return true;
        }
        return false;
    }
    public static boolean lengdeIsValid(String lengde){
        if(Pattern.matches(lengdeRegex,lengde)){
            return true;
        }
        return false;
    }
    public static boolean årsmodellIsValid(String årsmodell){

        int årsmodellParsed = 0;
        try{
            årsmodellParsed = Integer.parseInt(årsmodell);
        } catch(NumberFormatException e){}

        int detteÅret = LocalDate.now().getYear();

        if(årsmodell.isEmpty()){
            return false;
        }
        else if(årsmodellParsed <= detteÅret && årsmodellParsed > 1900){
            return true;
        }
        return false;
    }

    public static boolean motorinfoIsValid(String motorinfo){
        if(!motorinfo.isEmpty()){
            return true;
        }
        return false;
    }

}
