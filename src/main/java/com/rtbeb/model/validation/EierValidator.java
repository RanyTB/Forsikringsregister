package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Båt.Eier;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * @author Rany Tarek Bouorm - s236210
 * Validering av Eier klassen. Inkluderer validering av Eier-objekter og validering av enkelte felt i disse
 * for bruk i UI-feedback på inputfelter.
 */
public class EierValidator {

    private static String navnRegex = "[a-zæøåA-ZÆØÅ\\- ]{2,50}";
    private static LocalDate oldestValidDate = LocalDate.of(1900, 1, 1);
    private static LocalDate todaysDate = LocalDate.now();

    /**
     * Sjekker om et Eier-objekt er gyldig i forhold til fastsatte regler.
     * @param eier Eieren som skal sjekkes
     * @return True hvis alle felt i Eier-objektet er gyldig.
     */
    public static boolean EierIsValid(Eier eier){
        String fornavn = eier.getFornavn();
        String etternavn = eier.getEtternavn();
        LocalDate fødselsdato = eier.getFødselsdato();

        return fornavnIsValid(fornavn) && etternavnIsValid(etternavn) && fødselsdatoIsValid(fødselsdato);
    }

    public static boolean fornavnIsValid(String fornavn){
        return Pattern.matches(navnRegex, fornavn);
    }

    public static boolean etternavnIsValid(String etternavn){
        return Pattern.matches(navnRegex, etternavn);
    }

    public static boolean fødselsdatoIsValid(LocalDate fødselsdato){

        return fødselsdato.isAfter(oldestValidDate) && fødselsdato.isBefore(todaysDate);
    }

}
