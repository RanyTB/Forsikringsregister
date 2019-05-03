package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Bolig.Bolig;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * @author Rany Tarek Bouorm - s236210
 * For validering av innboforsikring
 */
public class BoligValidator {

    private static String adresseRegex = "[a-zæøåA-ZÆØÅ\\-0-9 /']{5,50}";
    private static String postnummerRegex = "\\d{4}";
    private static int maxKvadratmeter = 3000;
    private static int minKvadratmeter = 5;
    private static int minByggeår = 1900;

    /**
     * Validerer om en bolig knyttet til en InnboForsikring har gyldige verdier.
     * @param bolig Boligen til vurdering.
     * @return True hvis alle felt i bolig er gyldig.
     */
    public static boolean boligIsValid(Bolig bolig){
        String adresse = bolig.getAdresse();
        String postnummer = bolig.getPostnummer();
        String byggeår = bolig.getByggeår();
        String antallkvadratmeter = bolig.getStørrelse();

        Bolig.Boligtype boligtype = bolig.getBoligtype();
        Bolig.Byggemateriale byggemateriale = bolig.getByggemateriale();
        Bolig.Standard standard = bolig.getStandard();

        return adresseIsValid(adresse) && postnummerIsValid(postnummer) && byggeårIsValid(byggeår)
                && størrelseIsValid(antallkvadratmeter) && boligtypeIsValid(boligtype)
                && byggematerialeIsValid(byggemateriale) && standardIsValid(standard);
    }

    public static boolean adresseIsValid(String adresse){
        return Pattern.matches(adresseRegex, adresse);
    }

    public static boolean postnummerIsValid(String postnummer){
        return Pattern.matches(postnummerRegex, postnummer);
    }

    public static boolean byggeårIsValid(String byggeår){

        int byggeårParsed;

        try{
            byggeårParsed = Integer.parseInt(byggeår);
        } catch(NumberFormatException e){
            return false;
        }

        int detteÅret = LocalDate.now().getYear();

        if(byggeår.isEmpty()){
            return false;
        }
        else return byggeårParsed <= detteÅret && byggeårParsed > minByggeår;
    }

    public static boolean størrelseIsValid(String størrelse) {
        try {
            int størrelseParsed = Integer.parseInt(størrelse);
            return størrelseIsValid(størrelseParsed);
        } catch(NumberFormatException e){
            return false;
        }
    }

    private static boolean størrelseIsValid(int størrelse){
        return størrelse > minKvadratmeter && størrelse < maxKvadratmeter;
    }

    private static boolean boligtypeIsValid(Bolig.Boligtype boligtype){
        return boligtype instanceof Bolig.Boligtype;
    }

    private static boolean byggematerialeIsValid(Bolig.Byggemateriale byggemateriale){
        return byggemateriale instanceof Bolig.Byggemateriale;
    }

    private static boolean standardIsValid(Bolig.Standard standard){
        return standard instanceof Bolig.Standard;
    }
}
