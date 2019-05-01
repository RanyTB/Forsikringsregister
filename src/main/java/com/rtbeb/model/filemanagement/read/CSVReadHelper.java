package com.rtbeb.model.filemanagement.read;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;
import com.rtbeb.model.base.forsikring.Reise.Reiseforsikring;
import com.rtbeb.model.base.forsikring.Skademelding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CSVReadHelper {

    private ArrayList<Kunde> importertKundeliste = new ArrayList<>();
    private ArrayList<Skademelding> importertSkademeldingsliste = new ArrayList<>();

    public void håndterArray(String[] linje){
        //Kunde har posisjon 1, forsikringer posisjon 2 og skademeldinger posisjon 3
        String[] kundeArray = linje[1].split(",");
        String[] båtforsikringArray = linje[3].split("\\|");
        String[] innboforsikringArray = linje[5].split("\\|");
        String[] reiseforsikringsArray = linje[7].split("\\|");
        String[] skademeldingsArray = linje[9].split("\\|");

        for (int i = 0; i < linje.length; i++) {
            System.out.println(linje[i] + " nr" + i);
        }
        //System.out.println(linje[0]);
        /*System.out.println(linje[1]);
        System.out.println(linje[2]);
        System.out.println(linje[3]);
        System.out.println(linje[4]);
        System.out.println(linje[5]);
        System.out.println(linje[6]);*/

        //System.out.println("skademelding: " + skademeldingsArray[0]);



        håndterKunde(kundeArray);
        splitBåtforsikring(båtforsikringArray);
        splitSkademeldinger(skademeldingsArray);
        splitInnboforsikring(innboforsikringArray);
    }

    private LocalDate getDatoFormat(String dato){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dato, dateTimeFormatter);

        return localDate;
    }

    private void håndterKunde(String[] kundeArray){
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

    private void splitBåtforsikring(String[] båtforsikringsArray){
        for (String båtforsikring : båtforsikringsArray) {
            if (!båtforsikringsArray.equals("")){
                String[] splittetBåtforsikringsArray = båtforsikring.split(",");
                håndterBåtforsikring(splittetBåtforsikringsArray);
            }
        }
    }

    private void håndterBåtforsikring(String[] splittetBåtforsikringsArray){
        //Forsikrings info
        String forsikringsType = splittetBåtforsikringsArray[0];
        int forsikringspremie = Integer.parseInt(splittetBåtforsikringsArray[1]);
        LocalDate datoOpprettet = getDatoFormat(splittetBåtforsikringsArray[2]);
        int forsikringsbeløp = Integer.parseInt(splittetBåtforsikringsArray[3]);
        String forsikringsbetingelser = splittetBåtforsikringsArray[4];

        //Båt info
        String registreringsnummer = splittetBåtforsikringsArray[5];
        String merke = splittetBåtforsikringsArray[6];
        String modell = splittetBåtforsikringsArray[7];
        String lengde = splittetBåtforsikringsArray[8];
        String årsmodell = splittetBåtforsikringsArray[9];
        String motorinfo = splittetBåtforsikringsArray[10];

        //Eier info
        String fornavn = splittetBåtforsikringsArray[11];
        String etternavn = splittetBåtforsikringsArray[12];
        LocalDate fødselsdato = getDatoFormat(splittetBåtforsikringsArray[13]);
    }

    private void splitInnboforsikring(String[] innboforsikringArray){
        for (String innboforsikring : innboforsikringArray) {
            if (!innboforsikring.equals("")){
                String[] splittetInnboforsikringssArray = innboforsikring.split(",");
                håndterBåtforsikring(splittetInnboforsikringssArray);
            }
        }
    }

    private void håndterInnboforsikring(String[] splittetInnboforsikringssArray){
        //Forsikrings info
        String forsikringsType = splittetInnboforsikringssArray[0];
        int forsikringspremie = Integer.parseInt(splittetInnboforsikringssArray[1]);
        LocalDate datoOpprettet = getDatoFormat(splittetInnboforsikringssArray[2]);
        int forsikringsbeløp = Integer.parseInt(splittetInnboforsikringssArray[3]);
        String forsikringsbetingelser = splittetInnboforsikringssArray[4];

        //Innbo info
        int forsikringssbeløpBygning = Integer.parseInt(splittetInnboforsikringssArray[5]);
        int forsikringsbeløpInnbo = Integer.parseInt(splittetInnboforsikringssArray[6]);

        //Bolig info
        String adresse = splittetInnboforsikringssArray[7];
        String postnummer = splittetInnboforsikringssArray[8];
        String byggeår = splittetInnboforsikringssArray[9];
        String boligtype = splittetInnboforsikringssArray[10];
        String byggemateriale = splittetInnboforsikringssArray[11];
        String standard = splittetInnboforsikringssArray[12];
        String størrelse = splittetInnboforsikringssArray[13];

    }

    private void splitReiseforsikring(String[] reiseforsikringsArray){
        for (String reiseforsikring : reiseforsikringsArray) {
            if (!reiseforsikring.equals("")){
                String[] splittetReiseforsikringssArray = reiseforsikring.split(",");
                håndterBåtforsikring(splittetReiseforsikringssArray);
            }
        }
    }

    private void håndterReiseforsikring(String[] splittetReiseforsikringssArray){
        //Forsikrings info
        String forsikringsType = splittetReiseforsikringssArray[0];
        int forsikringspremie = Integer.parseInt(splittetReiseforsikringssArray[1]);
        LocalDate datoOpprettet = getDatoFormat(splittetReiseforsikringssArray[2]);
        int forsikringsbeløp = Integer.parseInt(splittetReiseforsikringssArray[3]);
        String forsikringsbetingelser = splittetReiseforsikringssArray[4];

        //Reise info
        String forsikringsområde = splittetReiseforsikringssArray[5];
        int forsikringssum = Integer.parseInt(splittetReiseforsikringssArray[6]);

        //Reiseforsikring reiseforsikring = new Reiseforsikring();
    }

    private void splitSkademeldinger(String[] skademeldingsArray){
        //Splitter opp chunken med skademeldinger til enkelte skademeldinger
        for (String skademelding : skademeldingsArray) {
            if (!skademelding.equals("")){
                String[] splittetSkademelding = skademelding.split(",");
                håndterSkademelding(splittetSkademelding);
                //System.out.println("skademelding: " + enkeltSkademelding);
            }
        }
    }
    private void håndterSkademelding(String[] splittetSkademelding){
        LocalDate skademeldingsDato = getDatoFormat(splittetSkademelding[0]);

        long skadenummer = Long.parseLong(splittetSkademelding[1]);
        String typeSkade = splittetSkademelding[2];
        String beskrivelse = splittetSkademelding[3];
        String vitner = splittetSkademelding[4];
        String takseringAvSkaden = splittetSkademelding[5];
        String utbetaltErstatningsbeløp = splittetSkademelding[6];

        Skademelding skademelding = new Skademelding(skademeldingsDato, skadenummer, typeSkade, beskrivelse, vitner,
                takseringAvSkaden, utbetaltErstatningsbeløp);

        if (skademelding.isValid()){
            importertSkademeldingsliste.add(skademelding);
        }else {
            System.out.println("Feil ved format på skademelding");
        }

    }

    public void addToRegistry(){
        Kunderegister kunderegister = Kunderegister.getInstance();
        kunderegister.setKundeliste(importertKundeliste);
    }
}
