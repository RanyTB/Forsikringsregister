package com.rtbeb.model.validation;

import com.rtbeb.model.base.Kunde;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KundeValidatorTest {

    @Test
    void kundeIsValid() {
        Kunde kunde = new Kunde("Ole", "Hansen", "Jacobine Ryes vei 2", "0987");
        assertTrue(KundeValidator.kundeIsValid(kunde));

        Kunde kunde2 = new Kunde("Ole,", "Hansen", "Test:", "232");
        assertFalse(KundeValidator.kundeIsValid(kunde2));
    }

    @Test
    void fornavnIsValid() {
        assertTrue(KundeValidator.fornavnIsValid("Ole"));
        assertFalse(KundeValidator.fornavnIsValid("O"));
        assertFalse(KundeValidator.fornavnIsValid("rjwoiajroa2"));
    }

    @Test
    void etternavnIsValid() {
        assertTrue(KundeValidator.fornavnIsValid("Hansen"));
        assertFalse(KundeValidator.fornavnIsValid("e"));
        assertFalse(KundeValidator.fornavnIsValid("awrawara2"));
    }

    @Test
    void fakturaAdresseIsValid() {
        assertTrue(KundeValidator.fakturaAdresseIsValid("Alexander Kiellands Plass 2"));
        assertFalse(KundeValidator.fakturaAdresseIsValid("Alexander,Kiellands Plass 2"));
    }

    @Test
    void postnummerIsValid() {
        assertTrue(KundeValidator.postnummerIsValid("0001"));
        assertFalse(KundeValidator.postnummerIsValid("123"));
        assertFalse(KundeValidator.postnummerIsValid("12345"));
    }
}