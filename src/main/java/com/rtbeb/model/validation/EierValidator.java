package com.rtbeb.model.validation;

import com.rtbeb.model.base.Eier;

import java.util.regex.Pattern;

public class EierValidator {

    static String navnRegex = "[a-zæøåA-ZÆØÅ\\-\\ ]{2,50}";
    static String fødselsdatoRegex = "^(0[1-9]|[12]\\d|3[01])(0[1-9]|1[12])(0?[1-9]|[1-9]\\d)$";

    static boolean EierIsValid(Eier eier){
        String fornavn = eier.getFornavn();
        String etternavn = eier.getEtternavn();
        String fødselsdato = eier.getFødselsdato();

        if(fornavnIsValid(fornavn) && etternavnIsValid(etternavn) && fødselsdatoIsValid(fødselsdato)){
            return true;
        }
        return false;
    }

    static boolean fornavnIsValid(String fornavn){
        if (Pattern.matches(navnRegex, fornavn)) {
            return true;
        }
        return false;
    }

    static boolean etternavnIsValid(String etternavn){
        if(Pattern.matches(navnRegex, etternavn)){
            return true;
        }
        return false;
    }

    static boolean fødselsdatoIsValid(String fødselsdato){
        if(Pattern.matches(fødselsdatoRegex, fødselsdato)){
            return true;
        }
        return false;
    }

}
