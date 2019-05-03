package com.rtbeb.model.filemanagement.write;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.forsikring.Bolig.Innboforsikring;
import com.rtbeb.model.base.forsikring.Båt.Båtforsikring;
import com.rtbeb.model.base.forsikring.Forsikring;
import com.rtbeb.model.base.forsikring.Reise.Reiseforsikring;
import com.rtbeb.model.base.forsikring.Skademelding;
import javafx.collections.ObservableList;

/**
 *  @author Eirik Bøyum
 *  Denne klassen formaterer kundeobjektet til csv format for skriving til fil.
 *  håndterkundeobjekt tar imot et kunde objekt for så systematisk hente ut tilhørende informasjon.
 *  Metoden returnerer så en komplett String av kunde som i CSVSaveStrategy skrives til fil.
 */
public class CSVFormatter {
    public static String toCSVStringKunde(Kunde kunde){
        return kunde.getFornavn() + ";" + kunde.getEtternavn() + ";" + kunde.getFakturaadresse() + ";" +
                kunde.getPostnummer() + ";" + kunde.getForsikringsnummer() + ";" + kunde.getKundeOpprettelsesDato();
    }

    private static String toCSVStringForsirking(Forsikring forsikring){
        return forsikring.getForsikringstype() + ";" + forsikring.getForsikringspremie() + ";" +
                forsikring.getDatoOpprettet() + ";" + forsikring.getForsikringsbeløp() + ";" + forsikring.getForsikringsbetingelser();
    }

    private static String toCSVStringBåtforsikring(Båtforsikring båtforsikring){
        String båtEier = båtforsikring.getBåt().getEier().getFornavn() + ";" + båtforsikring.getBåt().getEier().getEtternavn()
                + ";" + båtforsikring.getBåt().getEier().getFødselsdato();

        String tekstBåtforsikring = båtforsikring.getBåt().getRegistreringsnummer() + ";" + båtforsikring.getBåt().getMerke() +
                ";" + båtforsikring.getBåt().getModell() + ";" + båtforsikring.getBåt().getLengde() + ";" +
                båtforsikring.getBåt().getÅrsmodell() + ";" + båtforsikring.getBåt().getMotorinfo();

        String tekstForsikring = toCSVStringForsirking(båtforsikring);

        return tekstForsikring + ";" + tekstBåtforsikring + ";" + båtEier;
    }

    private static String toCSVStringInnboforsikring(Innboforsikring innboforsikring){
        String bolig = innboforsikring.getBolig().getAdresse() + ";" + innboforsikring.getBolig().getPostnummer() +
                ";" + innboforsikring.getBolig().getByggeår() + ";" + innboforsikring.getBolig().getBoligtype() +
                ";" + innboforsikring.getBolig().getByggemateriale() + ";" + innboforsikring.getBolig().getStandard() +
                ";" + innboforsikring.getBolig().getStørrelse();

        String tekstInnboforsikring =innboforsikring.getForsikringssbeløpBygning() + ";" + innboforsikring.getForsikringsbeløpInnbo();

        String tekstForsikring = toCSVStringForsirking(innboforsikring);

        return tekstForsikring + ";" + tekstInnboforsikring + ";" + bolig;
    }

    private static String toCSVStringReiseforsirking(Reiseforsikring reiseforsikring){
        String tekstReiseforsikring =reiseforsikring.getForsikringsområde() + ";" + reiseforsikring.getForsikringssum();

        String tekstForsikring = toCSVStringForsirking(reiseforsikring);

        return tekstForsikring + ";" + tekstReiseforsikring;
    }

    private static String toCSVStringSkademelding(Skademelding skademelding){
        return skademelding.getSkademeldingsDato() + ";" + skademelding.getSkadenummer() + ";" + skademelding.getTypeSkade() +
                ";" + skademelding.getBeskrivelse() + ";" + skademelding.getVitner() + ";" + skademelding.getTakseringAvSkaden() +
                ";" + skademelding.getUtbetaltErstatningsbeløp();
    }

    //Metoden tar imot kundens forsirkingsliste. Listen iteres gjennom of resultatet testets.
    private static String håndterForsikringsListe(ObservableList<Forsikring> forsikringsListe){
        String båtforsikringsTekst = "";
        String innboforsikringsTekst = "";
        String reiseforsikringsTekst = "";

        //Tester for en tom liste
        //Hvis tom returneres tomme plasser
        if (forsikringsListe.size() > 0){

            for (Forsikring forsikring : forsikringsListe) {

                if (forsikring instanceof Båtforsikring){

                    båtforsikringsTekst += "|" + toCSVStringBåtforsikring((Båtforsikring) forsikring) + "|";

                }else if (forsikring instanceof Innboforsikring){

                    innboforsikringsTekst += "|" + toCSVStringInnboforsikring((Innboforsikring) forsikring) + "|";

                }else if (forsikring instanceof Reiseforsikring){

                    reiseforsikringsTekst += "|" + toCSVStringReiseforsirking((Reiseforsikring) forsikring) + "|";
                }
            }
        }

        return båtforsikringsTekst + "\" ; \"" + innboforsikringsTekst + "\" ; \"" + reiseforsikringsTekst;
    }

    private static String håndterSkademeldinger(ObservableList<Skademelding> skademeldingsListe){
        String skademeldingsTekst = "";
        if (skademeldingsListe.size() > 0){
            for (Skademelding skademelding : skademeldingsListe) {
                skademeldingsTekst += "|" + toCSVStringSkademelding(skademelding);
            }
        }
        return skademeldingsTekst;
    }

    public static String håndterkundeobjekt(Kunde kunde){
        String kundeTekst = toCSVStringKunde(kunde);
        String forsikringsTekst = håndterForsikringsListe(kunde.getForsikringsListe());
        String skademeldingsTekst = håndterSkademeldinger(kunde.getSkademeldinger());
        return "\"" + kundeTekst + "\" ; \"" + skademeldingsTekst + "\" ; \"" + forsikringsTekst + "\"";

    }
}
