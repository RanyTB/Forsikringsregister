package com.rtbeb.model.validation;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class SkademeldingValidator {
    static String textRegex = "[a-zæøåA-ZÆØÅ\\-\\ ]+";
    static String textOgTallRegex = "[a-zøæåA-ZÆØÅ0-9\\-\\ ]+";
    static String tallRegex = "\\d*";

    public static boolean textIsValid(String hvaSomSkalValideres, int maksTekstLengde){
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
    }

    public static boolean dateIsValid(LocalDate date){
        if (date.isAfter(LocalDate.now())){
            return false;
        } else {
            return true;
        }
    }

}
