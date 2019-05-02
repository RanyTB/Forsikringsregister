package com.rtbeb.model.filemanagement.read;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;
import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.base.forsikring.Bolig.Bolig;
import com.rtbeb.model.base.forsikring.Bolig.Innboforsikring;
import com.rtbeb.model.base.forsikring.Båt.Båt;
import com.rtbeb.model.base.forsikring.Båt.Båtforsikring;
import com.rtbeb.model.base.forsikring.Båt.Eier;
import com.rtbeb.model.base.forsikring.Reise.Reiseforsikring;
import com.rtbeb.model.base.forsikring.Skademelding;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CSVReadHelper {

    private ArrayList<Kunde> importertKundeliste = new ArrayList<>();

    Kunde kunde;

    public void håndterArray(String[] linje){
        //Kunde har posisjon 1, forsikringer posisjon 2 og skademeldinger posisjon 3
        String[] kundeArray = linje[1].split(",");
        håndterKunde(kundeArray);

        /*try {
            String[] båtforsikringArray = linje[3].split("\\|");
            String[] innboforsikringArray = linje[5].split("\\|");
            String[] reiseforsikringsArray = linje[7].split("\\|");
            String[] skademeldingsArray = linje[9].split("\\|");



            splitBåtforsikring(båtforsikringArray);
            splitInnboforsikring(innboforsikringArray);
            splitReiseforsikring(reiseforsikringsArray);
            splitSkademeldinger(skademeldingsArray);
        }catch(Exception e){
               e.printStackTrace();
        }*/

        //Tester om kunde har forsikringer
        if(linje[3].split("\\|").length > 1){
            String[] båtforsikringArray = linje[3].split("\\|");
            splitBåtforsikring(båtforsikringArray);
        }

        if(linje[5].split("\\|").length >1){
            String[] innboforsikringArray = linje[5].split("\\|");
            splitInnboforsikring(innboforsikringArray);
        }

        if (linje[7].split("\\|").length > 1){
            String[] reiseforsikringsArray = linje[7].split("\\|");
            splitReiseforsikring(reiseforsikringsArray);
        }

        //Ved første split i selve metoden for innlesning fra til faller den siste plassen bort hvis kunde ikke har noen skademeldinger.
        //Sjekker for dette tilfellet her.
        if (linje.length == 10){
            if (linje[9].split("\\|").length > 1){
                String[] skademeldingsArray = linje[9].split("\\|");
                splitSkademeldinger(skademeldingsArray);
            }
        }


        //String[] innboforsikringArray = linje[5].split("\\|");
        //String[] reiseforsikringsArray = linje[7].split("\\|");
        //String[] skademeldingsArray = linje[9].split("\\|");


        /*String[] båtforsikringArray = linje[3].split("\\|");
        String[] innboforsikringArray = linje[5].split("\\|");
        String[] reiseforsikringsArray = linje[7].split("\\|");
        String[] skademeldingsArray = linje[9].split("\\|");*/


        /*for (int i = 0; i < skademeldingsArray.length; i++){
            System.out.println("skademeldingsArray: " + i + " skademeldingsArray: " + skademeldingsArray[i] + " end of line");

        }

        for (int i = 0; i < linje.length; i++){
            System.out.println("linjenr: " + i + " innhold: " + linje[i] + " end of line");

        }*/

        //System.out.println(linje[0]);
        /*System.out.println(linje[1]);
        System.out.println(linje[2]);
        System.out.println(linje[3]);
        System.out.println(linje[4]);
        System.out.println(linje[5]);
        System.out.println(linje[6]);*/

        //System.out.println("skademelding: " + skademeldingsArray[0]);



        //håndterKunde(kundeArray);
        /*splitBåtforsikring(båtforsikringArray);
        splitInnboforsikring(innboforsikringArray);
        splitReiseforsikring(reiseforsikringsArray);
        splitSkademeldinger(skademeldingsArray);*/
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

        kunde = new Kunde(fornavn, etternavn, fakturaadresse, postnummer, forsikringsnummer, opprettelsesDato);

        if (kunde.isValid()){
            importertKundeliste.add(kunde);
        }else {
            System.out.println("Feil ved format på kunde");
        }
    }

    private void splitBåtforsikring(String[] båtforsikringsArray){
        for (String båtforsikring : båtforsikringsArray) {
            if (!båtforsikring.equals("")){
                String[] splittetBåtforsikringsArray = båtforsikring.split(",");
                håndterBåtforsikring(splittetBåtforsikringsArray);
            }
        }
    }

    private void håndterBåtforsikring(String[] splittetBåtforsikringsArray){
        try {
            //Forsikrings info
            String forsikringsType = splittetBåtforsikringsArray[0];
            int forsikringspremie = Integer.parseInt(splittetBåtforsikringsArray[1]);
            LocalDate datoOpprettet = getDatoFormat(splittetBåtforsikringsArray[2]);
            //LocalDate datoOpprettet = LocalDate.now();

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
            //LocalDate fødselsdato = LocalDate.now();

            Eier eier = new Eier(fornavn, etternavn, fødselsdato);
            Båt båt = new Båt(eier,registreringsnummer, merke, modell,lengde,årsmodell,motorinfo);
            Båtforsikring båtforsikring = new Båtforsikring(forsikringspremie, forsikringsbeløp, forsikringsbetingelser, båt);

            if (båtforsikring.isValid()){
                //importertKundeliste.add(båtforsikring);
                System.out.println("Riktig format på båtforsikring");

                try {
                    kunde.addForsikring(båtforsikring);
                    System.out.println("Try");
                } catch (InvalidForsikringException e) {
                    e.printStackTrace();
                }

            }else {
                System.out.println("Feil ved format på båtforsikring");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void splitInnboforsikring(String[] innboforsikringArray){
        for (String innboforsikring : innboforsikringArray) {
            if (!innboforsikring.equals("")){
                System.out.println("splitInnboforsikring");
                String[] splittetInnboforsikringssArray = innboforsikring.split(",");
                for (int i = 0; i < splittetInnboforsikringssArray.length; i++) {
                    //System.out.println(splittetInnboforsikringssArray[i] + " nr: " + i);
                }
                håndterInnboforsikring(splittetInnboforsikringssArray);

            }
        }
    }

    private void håndterInnboforsikring(String[] splittetInnboforsikringssArray){
        //Forsikrings info
        Innboforsikring.Brukstype brukstype = Innboforsikring.Brukstype.getBrukstype(splittetInnboforsikringssArray[0]);
        //Innboforsikring.Brukstype brukstype = Innboforsikring.Brukstype.valueOf("HELÅRSBOLIG");

        int forsikringspremie = Integer.parseInt(splittetInnboforsikringssArray[1]);
        //LocalDate datoOpprettet = getDatoFormat(splittetInnboforsikringssArray[2]);
        int forsikringsbeløp = Integer.parseInt(splittetInnboforsikringssArray[3]);
        String forsikringsbetingelser = splittetInnboforsikringssArray[4];


        //Innbo info
        int forsikringssbeløpBygning = Integer.parseInt(splittetInnboforsikringssArray[5]);
        int forsikringsbeløpInnbo = Integer.parseInt(splittetInnboforsikringssArray[6]);

        //Bolig info
        String adresse = splittetInnboforsikringssArray[7];
        String postnummer = splittetInnboforsikringssArray[8];
        String byggeår = splittetInnboforsikringssArray[9];
        Bolig.Boligtype boligtype = Bolig.Boligtype.getBoligtype(splittetInnboforsikringssArray[10]);
        Bolig.Byggemateriale byggemateriale = Bolig.Byggemateriale.getByggmateriale(splittetInnboforsikringssArray[11]);
        Bolig.Standard standard = Bolig.Standard.getStandard(splittetInnboforsikringssArray[12]);
        String størrelse = splittetInnboforsikringssArray[13];

        Bolig bolig = new Bolig(adresse, postnummer, byggeår, boligtype, byggemateriale, standard, størrelse);
        Innboforsikring innboforsikring = new Innboforsikring(brukstype, forsikringspremie, forsikringsbeløp,
                forsikringsbetingelser, bolig, forsikringssbeløpBygning, forsikringsbeløpInnbo);

        System.out.println(innboforsikring);

        if (innboforsikring.isValid()){
            //importertKundeliste.add(innboforsikring);
            System.out.println("Riktig format på innboforsikring");

            try {
                kunde.addForsikring(innboforsikring);
                System.out.println("Try");
            } catch (InvalidForsikringException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Feil ved format på innboforsikring");
        }
    }

    private void splitReiseforsikring(String[] reiseforsikringsArray){
        for (String reiseforsikring : reiseforsikringsArray) {
            if (!reiseforsikring.equals("")){
                String[] splittetReiseforsikringssArray = reiseforsikring.split(",");
                håndterReiseforsikring(splittetReiseforsikringssArray);
            }
        }
    }

    private void håndterReiseforsikring(String[] splittetReiseforsikringssArray){
        //Forsikrings info
        String forsikringsType = splittetReiseforsikringssArray[0];
        int forsikringspremie = Integer.parseInt(splittetReiseforsikringssArray[1]);

        //TODO lage konstruktør som tar imot en dato
        LocalDate datoOpprettet = getDatoFormat(splittetReiseforsikringssArray[2]);
        int forsikringsbeløp = Integer.parseInt(splittetReiseforsikringssArray[3]);
        String forsikringsbetingelser = splittetReiseforsikringssArray[4];

        //Reise info
        String forsikringsområde = splittetReiseforsikringssArray[5];
        int forsikringssum = Integer.parseInt(splittetReiseforsikringssArray[6]);

        Reiseforsikring reiseforsikring = new Reiseforsikring(forsikringspremie, forsikringsbeløp,
                forsikringsbetingelser, forsikringsområde, forsikringssum);

        if (reiseforsikring.isValid()){
            //importertKundeliste.add(reiseforsikring);
            System.out.println("Riktig format på reiseforsikring");
            try {
                kunde.addForsikring(reiseforsikring);
                System.out.println("Try");
            } catch (InvalidForsikringException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Feil ved format på reiseforsikring");
        }
    }

    private void splitSkademeldinger(String[] skademeldingsArray){
        //Splitter opp chunken med skademeldinger til enkelte skademeldinger
        for (String skademelding : skademeldingsArray) {
            if (!skademelding.equals("")){
                String[] splittetSkademelding = skademelding.split(",");
                håndterSkademelding(splittetSkademelding);
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
            System.out.println("Ritkig format på skademelding");

                kunde.addSkademelding(skademelding);
                System.out.println("Try");


        }else {
            System.out.println("Feil ved format på skademelding");
        }

    }

    public void addToRegistry(){
        Kunderegister kunderegister = Kunderegister.getInstance();
        kunderegister.setKundeliste(importertKundeliste);
    }
}