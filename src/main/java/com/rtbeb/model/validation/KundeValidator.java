package com.rtbeb.model.validation;

import com.rtbeb.model.base.Kunde;

import java.util.regex.Pattern;

/**
 * Validering av kunder.
 * Inkluderer metoder for validering av Kunde-objekter og validering av enkelt-felt i Kunde,
 * for bruk i UI-feedback på inputfelt.
 */
public class KundeValidator {

    //Regex regler for navn og adresse.
    static String navnRegex = "[a-zæøåA-ZÆØÅ\\-\\ ]{2,50}";
    static String fakturaadresseRegex = "[a-zæøåA-ZÆØÅ\\-0-9\\ \\/\\']{5,50}";
    static String postnummerRegex = "\\d{4}";

    //Metode for validering av et kundeobjekt.
    public static boolean kundeIsValid(Kunde kunde){
        String fornavn = kunde.getFornavn();
        String etternavn = kunde.getEtternavn();
        String fakturaadresse = kunde.getFakturaadresse();
        String postnummer = kunde.getPostnummer();


        if(fornavnIsValid(fornavn) &&
                etternavnIsValid(etternavn) &&
                fakturaAdresseIsValid(fakturaadresse) &&
                postnummerIsValid(postnummer)) {
            return true;
        }
        return false;
    }

    //Metoder for validering av enkelte felt.
    public static boolean fornavnIsValid(String fornavn) {

        if (Pattern.matches(navnRegex, fornavn)) {
            return true;
        }
        return false;
    }

    public static boolean etternavnIsValid(String etternavn) {
        if (Pattern.matches(navnRegex, etternavn)) {
            return true;
        }
        return false;
    }

    public static boolean fakturaAdresseIsValid(String fakturaadresse) {
        if (Pattern.matches(fakturaadresseRegex, fakturaadresse)) {
            return true;
        }
        return false;
    }

    public static boolean postnummerIsValid(String postnummer) {
        if (Pattern.matches(postnummerRegex, postnummer)) {
            return true;
        }
        return false;
    }


}
