package com.rtbeb.model.base;

import com.rtbeb.model.base.exception.InvalidKundeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KunderegisterTest {

    //Henter instanse av kunderegisteret
    private Kunderegister kunderegister = Kunderegister.getInstance();

    @Test
    void getInstance() {
        assertTrue(Kunderegister.getInstance() instanceof Kunderegister);
    }

    @Test
    void insertKunde() {

       int sizeBefore = kunderegister.getKundeliste().size();

       //Tester innsetting av gyldig kunde
        try {
            kunderegister.insertKunde(getValidKunde());
        } catch (InvalidKundeException ignored) {}

        int sizeAfter = kunderegister.getKundeliste().size();
        assertEquals(sizeAfter, sizeBefore + 1);


        //Setter ny sizeBefore og tester innsetting av ugyldig kunde
        sizeBefore = sizeAfter;
        try {
            kunderegister.insertKunde(getInvalidKunde());
        } catch (InvalidKundeException ignored) {}

        sizeAfter = kunderegister.getKundeliste().size();
        assertEquals(sizeAfter, sizeBefore);
    }

    Kunde getValidKunde(){
        return new Kunde("Ole", "Hansen", "Ellen Gleditsch Vei 25", "0987");
    }

    Kunde getInvalidKunde(){
        return new Kunde("O4", "Olsen", "Hei,.-", "123");
    }

    //TODO implementer disse
    @Test
    void deleteKunde() {
    }

    @Test
    void setKundeliste() {
    }

    @Test
    void getKundeliste() {
    }
}