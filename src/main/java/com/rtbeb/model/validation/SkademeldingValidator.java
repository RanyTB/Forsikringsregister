package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Skademelding;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 *  @author Eirik Bøyum
 *  Validator for skademelding.
 *  Regex for tillat input
 */
public class SkademeldingValidator {
    private static String textRegex = "[a-zæøåA-ZÆØÅ\\-,-. ]{2,50}+";
    private static String textTallRegex = "[a-zøæåA-ZÆØÅ0-9\\-\\n\\t@,.+:! ]{2,100}";
    private static String tallRegex = "\\d{1,8}";


    public static boolean textIsValid(String hvaSomSkalValideres){

        return Pattern.matches(textRegex, hvaSomSkalValideres);

    }

    public static boolean textOgTallIsValid(String hvaSomSkalValideres){

        return Pattern.matches(textTallRegex, hvaSomSkalValideres);

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

    //Siste sjekk for skademelding
    public static boolean skademeldingIsValid(Skademelding skademelding){
        LocalDate date = skademelding.getSkademeldingsDato();
        String typeSkade = skademelding.getTypeSkade();
        String beskrivelse = skademelding.getBeskrivelse();
        String vitner = skademelding.getVitner();
        String takseringAvSkaden = skademelding.getTakseringAvSkaden();
        String utbetaltErstatningsbeløp = skademelding.getUtbetaltErstatningsbeløp();

        if (dateIsValid(date) && textIsValid(typeSkade) && textOgTallIsValid(beskrivelse)
        && textOgTallIsValid(vitner) && tallIsValid(takseringAvSkaden) && tallIsValid(utbetaltErstatningsbeløp)){
            return true;
        }else {
            return false;
        }
    }

}
