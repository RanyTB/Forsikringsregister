package com.rtbeb.model.validation;

import com.rtbeb.model.base.Kunde;

import java.util.regex.Pattern;

/**
 * @author Rany Tarek Bouorm - s236210
 * Validering av kunder.
 * Inkluderer metoder for validering av Kunde-objekter og validering av enkelt-felt i Kunde,
 * for bruk i UI-feedback på inputfelt.
 */
public class KundeValidator {

    //Regex regler for navn og adresse.
    private static String navnRegex = "[a-zæøåA-ZÆØÅ\\- ]{2,50}";
    private static String fakturaadresseRegex = "[a-zæøåA-ZÆØÅ\\-0-9 /']{5,50}";
    private static String postnummerRegex = "\\d{4}";

    /**
     * Validerer et Kunde-objekt.
     * @param kunde kunden som skal vurderes.
     * @return True hvis kunden er gyldig.
     */
    public static boolean kundeIsValid(Kunde kunde){
        String fornavn = kunde.getFornavn();
        String etternavn = kunde.getEtternavn();
        String fakturaadresse = kunde.getFakturaadresse();
        String postnummer = kunde.getPostnummer();


        return fornavnIsValid(fornavn) &&
                etternavnIsValid(etternavn) &&
                fakturaAdresseIsValid(fakturaadresse) &&
                postnummerIsValid(postnummer);
    }

    //Metoder for validering av enkelte felt.
    public static boolean fornavnIsValid(String fornavn) {

        return Pattern.matches(navnRegex, fornavn);
    }

    public static boolean etternavnIsValid(String etternavn) {
        return Pattern.matches(navnRegex, etternavn);
    }

    public static boolean fakturaAdresseIsValid(String fakturaadresse) {
        return Pattern.matches(fakturaadresseRegex, fakturaadresse);
    }

    public static boolean postnummerIsValid(String postnummer) {
        return Pattern.matches(postnummerRegex, postnummer);
    }


}
