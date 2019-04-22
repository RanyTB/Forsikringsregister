package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Båt.Eier;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Validering av Eier klassen. Inkluderer validering av Eier-objekter og validering av enkelte felt i disse
 * for bruk i UI-feedback på inputfelter.
 */
public class EierValidator {

    static String navnRegex = "[a-zæøåA-ZÆØÅ\\- ]{2,50}";
    static LocalDate oldestValidDate = LocalDate.of(1900, 01, 01);
    static LocalDate todaysDate = LocalDate.now();

    public static boolean EierIsValid(Eier eier){
        String fornavn = eier.getFornavn();
        String etternavn = eier.getEtternavn();
        LocalDate fødselsdato = eier.getFødselsdato();

        if(fornavnIsValid(fornavn) && etternavnIsValid(etternavn) && fødselsdatoIsValid(fødselsdato)){
            return true;
        }
        return false;
    }

    public static boolean fornavnIsValid(String fornavn){
        if (Pattern.matches(navnRegex, fornavn)) {
            return true;
        }
        return false;
    }

    public static boolean etternavnIsValid(String etternavn){
        if(Pattern.matches(navnRegex, etternavn)){
            return true;
        }
        return false;
    }

    public static boolean fødselsdatoIsValid(LocalDate fødselsdato){

      if(fødselsdato.isAfter(oldestValidDate) && fødselsdato.isBefore(todaysDate)){
          return true;
      }
      return false;
    }

}
