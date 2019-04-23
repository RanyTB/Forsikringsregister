package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Bolig.Bolig;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class BoligValidator {

    private static String adresseRegex = "[a-zæøåA-ZÆØÅ\\-0-9\\ \\/\\']{5,50}";
    private static String postnummerRegex = "\\d{4}";
    private static int maxKvadratmeter = 3000;
    private static int minKvadratmeter = 5;
    private static int minByggeår = 1900;

    public static boolean boligIsValid(Bolig bolig){
        String adresse = bolig.getAdresse();
        String postnummer = bolig.getPostnummer();
        String byggeår = bolig.getByggeår();
        String antallkvadratmeter = bolig.getStørrelse();

        return adresseIsValid(adresse) && postnummerIsValid(postnummer)
                && byggeårIsValid(byggeår) && størrelseIsValid(antallkvadratmeter);
    }

    public static boolean adresseIsValid(String adresse){
        return Pattern.matches(adresseRegex, adresse);
    }

    public static boolean postnummerIsValid(String postnummer){
        return Pattern.matches(postnummerRegex, postnummer);
    }

    public static boolean byggeårIsValid(String byggeår){

        int byggeårParsed = 0;
        try{
            byggeårParsed = Integer.parseInt(byggeår);
        } catch(NumberFormatException e){
            return false;
        }

        int detteÅret = LocalDate.now().getYear();

        if(byggeår.isEmpty()){
            return false;
        }
        else if(byggeårParsed <= detteÅret && byggeårParsed > minByggeår){
            return true;
        }
        return false;
    }

    public static boolean størrelseIsValid(String størrelse) {
        try {
            int størrelseParsed = Integer.parseInt(størrelse);
            return størrelseIsValid(størrelseParsed);
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean størrelseIsValid(int størrelse){
        return størrelse > minKvadratmeter && størrelse < maxKvadratmeter;
    }
}
