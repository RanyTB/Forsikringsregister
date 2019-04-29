package com.rtbeb.model.filemanagement.write;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.forsikring.Bolig.Bolig;
import com.rtbeb.model.base.forsikring.Bolig.Innboforsikring;
import com.rtbeb.model.base.forsikring.Båt.Båtforsikring;
import com.rtbeb.model.base.forsikring.Forsikring;
import com.rtbeb.model.base.forsikring.Reise.Reiseforsikring;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CSVWriteHelper {
    public static String toStringKunde(Kunde kunde){
        return "\"" + kunde.getFornavn() + "," + kunde.getEtternavn() + "," + kunde.getFakturaadresse() + "," +
                kunde.getPostnummer() + "," + kunde.getForsikringsnummer() + "," + kunde.getKundeOpprettelsesDato() + "\",";
    }

    private static String toStringForsirking(Forsikring forsikring){
        return forsikring.getForsikringstype() + "," + forsikring.getForsikringspremie() + "," +
                forsikring.getDatoOpprettet() + "," + forsikring.getForsikringsbeløp() + "," + forsikring.getForsikringsbetingelser();
    }

    private static String toStringBåtforsikring(Båtforsikring båtforsikring){
        String båtEier = båtforsikring.getBåt().getEier().getFornavn() + "," + båtforsikring.getBåt().getEier().getEtternavn()
                + "," + båtforsikring.getBåt().getEier().getFødselsdato();

        String tekstBåtforsikring = båtforsikring.getBåt().getRegistreringsnummer() + "," + båtforsikring.getBåt().getMerke() +
                "," + båtforsikring.getBåt().getModell() + "," + båtforsikring.getBåt().getLengde() + "," +
                båtforsikring.getBåt().getÅrsmodell() + "," + båtforsikring.getBåt().getMotorinfo();

        String tekstForsikring = toStringForsirking(båtforsikring);

        return "\"" + tekstForsikring + "," + tekstBåtforsikring + "," + båtEier + "\",";
    }

    private static String toStringInnboforsikring(Innboforsikring innboforsikring){
        String bolig = "," + innboforsikring.getBolig().getAdresse() + "," + innboforsikring.getBolig().getPostnummer() +
                "," + innboforsikring.getBolig().getByggeår() + "," + innboforsikring.getBolig().getBoligtype() + "," +
                "," + innboforsikring.getBolig().getByggemateriale() + "," + innboforsikring.getBolig().getStandard() +
                "," + innboforsikring.getBolig().getStørrelse();

        String tekstInnboforsikring = "," + innboforsikring.getForsikringssbeløpBygning() + "," + innboforsikring.getForsikringsbeløpInnbo();

        String tekstForsikring = toStringForsirking(innboforsikring);

        return ",\"" + tekstForsikring + "," + tekstInnboforsikring + "," + bolig + "\",";
    }

    private static String toStringReiseforsirking(Reiseforsikring reiseforsikring){
        String tekstReiseforsikring = "," + reiseforsikring.getForsikringsområde() + "," + reiseforsikring.getForsikringssum();

        String tekstForsikring = toStringForsirking(reiseforsikring);

        return ",\"" + tekstReiseforsikring + "," + tekstForsikring + "\"";
    }


    public static String forsirkingshjelper(Forsikring forsikring){

        //Lager en String[] med båtforsikring, innbo, reise

        String[] forsikringsArray = new String[3];



        if (forsikring instanceof Båtforsikring){
            return toStringBåtforsikring((Båtforsikring) forsikring);

        }else if (forsikring instanceof Innboforsikring){
            return toStringInnboforsikring((Innboforsikring) forsikring);

        }else if (forsikring instanceof Reiseforsikring){
            return toStringReiseforsirking((Reiseforsikring) forsikring);

        }/*else {
            return ",,,,,,,,,,,";
        }*/

        String forsikringsDelenAvCSVFilen = forsikringsArray[0] + "," + forsikringsArray[1] + "," + forsikringsArray[2];
        return forsikringsDelenAvCSVFilen;

    }

    private static String håndterForsikringsListe(ObservableList<Forsikring> forsikring){
        //Tester for en tom liste
        //Hvis tom returneres tomme plasser
        if (forsikring.size() > 1){


        }else {
            return "\"\"";
        }
    }

    public static String håndterkundeobjekt(Kunde kunde){
        String kundeTekst = toStringKunde(kunde);
        String forsikringsTekst = håndterForsikringsListe(kunde.getForsikringsListe());
        return kundeTekst;
    }
}
