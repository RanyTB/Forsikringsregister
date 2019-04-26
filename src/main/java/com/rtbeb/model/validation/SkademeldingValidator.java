package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Skademelding;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class SkademeldingValidator {
    static String textRegex = "[a-zæøåA-ZÆØÅ\\-\\ ]{2,50}+";
    static String textOgTallRegex = "[a-zøæåA-ZÆØÅ0-9\\-\\ ]{2,100}+";
    static String tallRegex = "\\d{1,8}";

    /*public static boolean textIsValid(String hvaSomSkalValideres, int maksTekstLengde){
        if (hvaSomSkalValideres.length() > maksTekstLengde){
            return false;
        }else {
            return Pattern.matches(textRegex, hvaSomSkalValideres);
        }
    }

    public static boolean textOgTallIsValid(String hvaSomSkalValideres, int maksTekstLengde){
        if (hvaSomSkalValideres.length() > maksTekstLengde){
            return false;
        }else {
            return Pattern.matches(textOgTallRegex, hvaSomSkalValideres);
        }
    }

    public static boolean tallIsValid(String hvaSomSkalValideres, int maksTallLengde){
        if (hvaSomSkalValideres.length() > maksTallLengde){
            return false;
        }else {
            return Pattern.matches(tallRegex, hvaSomSkalValideres);
        }
    }*/
    public static boolean textIsValid(String hvaSomSkalValideres){

        return Pattern.matches(textRegex, hvaSomSkalValideres);

    }

    public static boolean textOgTallIsValid(String hvaSomSkalValideres){

        return Pattern.matches(textOgTallRegex, hvaSomSkalValideres);

    }

    public static boolean tallIsValid(String hvaSomSkalValideres){

        return Pattern.matches(tallRegex, hvaSomSkalValideres);

    }

    public static boolean dateIsValid(LocalDate date){
        if (date == null){
            return false;
        } else if (date.isAfter(LocalDate.now())){
            return false;
        } else {
            return true;
        }
    }

    public static boolean skademeldingIsValid(Skademelding skademelding){
        LocalDate date = skademelding.getSkademeldingsDato();
        String typeSkade = skademelding.getTypeSkade();
        String beskrivelse = skademelding.getBeskrivelse();
        String vitner = skademelding.getVitner();
        String takseringAvSkaden = skademelding.getTakseringAvSkaden();
        String utbetaltErstatningsbeløp = skademelding.getUtbetaltErstatningsbeløp();

        if (dateIsValid(date) && textIsValid(typeSkade) && textIsValid(beskrivelse)
        && textOgTallIsValid(vitner) && tallIsValid(takseringAvSkaden) && tallIsValid(utbetaltErstatningsbeløp)){
            return true;
        }else {
            return false;
        }
    }

}
