package com.rtbeb.model.validation;

import com.rtbeb.model.base.Kunde;

import java.util.regex.Pattern;

public class KundeValidator {

    String fornavn;
    String etternavn;
    String fakturaadresse;
    String postnummer;

    String navnRegex = "[a-zæøåA-ZÆØÅ\\-\\ ]{2,50}";
    String fakturaadresseRegex = "[a-zæøåA-ZÆØÅ\\-0-9\\ \\/\\']{5,50}";
    String postnummerRegex = "\\d{4}";

    public KundeValidator(Kunde kunde) {
        fornavn = kunde.getFornavn();
        etternavn = kunde.getEtternavn();
        fakturaadresse = kunde.getFakturaadresse();
        postnummer = kunde.getPostnummer();
    }

    //Metode for validering av alle felt.
    public boolean kundeIsValid(){
        if(fornavnIsValid() && etternavnIsValid() && fakturaAdresseIsValid() && postnummerIsValid()){
            return true;
        }
        return false;
    }

    //Metoder for validering av enkelte felt.
    public boolean fornavnIsValid() {

        if (Pattern.matches(navnRegex, fornavn)) {
            return true;
        }
        return false;
    }

    public boolean etternavnIsValid() {
        if (Pattern.matches(navnRegex, etternavn)) {
            return true;
        }
        return false;
    }

    public boolean fakturaAdresseIsValid() {
        if (Pattern.matches(fakturaadresseRegex, fakturaadresse)) {
            return true;
        }
        return false;
    }

    public boolean postnummerIsValid() {
        if (Pattern.matches(postnummerRegex, postnummer)) {
            return true;
        }
        return false;
    }


}
