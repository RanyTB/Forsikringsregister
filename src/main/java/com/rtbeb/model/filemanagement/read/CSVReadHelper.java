package com.rtbeb.model.filemanagement.read;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVReadHelper {

    private ArrayList<Kunde> importertKundeliste = new ArrayList<>();

    public void håndterArray(String[] linje){
        //Kunde har posisjon 1, båt posisjon 2
        String[] kundeArray = linje[1].split(",");
        //String[] kundeArray = Arrays.copyOfRange(linje, 0, 5);
        String[] båtforsikringArray = linje[3].split(",");

        håndterKunde(kundeArray);
        håndterBåtforsikring(båtforsikringArray);
    }

    private LocalDate getDatoFormat(String dato){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dato, dateTimeFormatter);

        return localDate;
    }

    public void håndterKunde(String[] kundeArray){
        String fornavn = kundeArray[0];
        String etternavn = kundeArray[1];
        String fakturaadresse = kundeArray[2];
        String postnummer = kundeArray[3];
        Long forsikringsnummer = Long.parseLong(kundeArray[4]);

        LocalDate opprettelsesDato = getDatoFormat(kundeArray[5]);

        Kunde kunde = new Kunde(fornavn, etternavn, fakturaadresse, postnummer, forsikringsnummer, opprettelsesDato);

        if (kunde.isValid()){
            importertKundeliste.add(kunde);
        }else {
            System.out.println("Feil ved format på kunde");
        }
    }

    public void håndterBåtforsikring(String[] båtforsikringArray){
        String forsikringsType = båtforsikringArray[0];
        int forsikringspremie = Integer.parseInt(båtforsikringArray[1]);
        LocalDate datoOpprettet = getDatoFormat(båtforsikringArray[2]);
        int forsikringsbeløp = Integer.parseInt(båtforsikringArray[3]);





    }

    public void addToRegistry(){
        Kunderegister kunderegister = Kunderegister.getInstance();
        kunderegister.setKundeliste(importertKundeliste);
    }
}
