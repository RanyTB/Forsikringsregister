package com.rtbeb.model.filemanagement.write;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.forsikring.Bolig.Bolig;
import com.rtbeb.model.base.forsikring.Bolig.Innboforsikring;
import com.rtbeb.model.base.forsikring.Båt.Båtforsikring;
import com.rtbeb.model.base.forsikring.Forsikring;
import com.rtbeb.model.base.forsikring.Reise.Reiseforsikring;
import com.rtbeb.model.base.forsikring.Skademelding;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CSVWriteHelper {
    public static String toStringKunde(Kunde kunde){
        return kunde.getFornavn() + "," + kunde.getEtternavn() + "," + kunde.getFakturaadresse() + "," +
                kunde.getPostnummer() + "," + kunde.getForsikringsnummer() + "," + kunde.getKundeOpprettelsesDato();
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

        return tekstForsikring + "," + tekstBåtforsikring + "," + båtEier;
    }

    private static String toStringInnboforsikring(Innboforsikring innboforsikring){
        String bolig = innboforsikring.getBolig().getAdresse() + "," + innboforsikring.getBolig().getPostnummer() +
                "," + innboforsikring.getBolig().getByggeår() + "," + innboforsikring.getBolig().getBoligtype() + "," +
                "," + innboforsikring.getBolig().getByggemateriale() + "," + innboforsikring.getBolig().getStandard() +
                "," + innboforsikring.getBolig().getStørrelse();

        String tekstInnboforsikring =innboforsikring.getForsikringssbeløpBygning() + "," + innboforsikring.getForsikringsbeløpInnbo();

        String tekstForsikring = toStringForsirking(innboforsikring);

        return tekstForsikring + "," + tekstInnboforsikring + "," + bolig;
    }

    private static String toStringReiseforsirking(Reiseforsikring reiseforsikring){
        String tekstReiseforsikring =reiseforsikring.getForsikringsområde() + "," + reiseforsikring.getForsikringssum();

        String tekstForsikring = toStringForsirking(reiseforsikring);

        return tekstForsikring + "," + tekstReiseforsikring;
    }

    private static String toStringSkademelding(Skademelding skademelding){
        return skademelding.getSkademeldingsDato() + "," + skademelding.getSkadenummer() + "," + skademelding.getTypeSkade() +
                "," + skademelding.getBeskrivelse() + "," + skademelding.getVitner() + "," + skademelding.getTakseringAvSkaden() +
                "," + skademelding.getUtbetaltErstatningsbeløp();
    }


    public static String forsikringshjelper(Forsikring forsikring){

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

    private static String håndterForsikringsListe(ObservableList<Forsikring> forsikringsListe){
        String forsikringsTekst = "";

        /*ArrayList<String> båtforsikringer = new ArrayList<>();
        ArrayList<String> innboforsikringer = new ArrayList<>();
        ArrayList<String> reiseforsikringer = new ArrayList<>();*/

        String båtforsikringsTekst = "";
        String innboforsikringsTekst = "";
        String reiseforsikringsTekst = "";

        //Tester for en tom liste
        //Hvis tom returneres tomme plasser
        if (forsikringsListe.size() > 0){
            System.out.println("if test");

            for (Forsikring forsikring : forsikringsListe) {
                System.out.println("Forløkke");

                if (forsikring instanceof Båtforsikring){
                    System.out.println("Båt");

                    båtforsikringsTekst += "|" + toStringBåtforsikring((Båtforsikring) forsikring) + "|";
                    //båtforsikringer.add(toStringBåtforsikring((Båtforsikring) forsikring));

                }else if (forsikring instanceof Innboforsikring){
                    System.out.println("Innbo");

                    innboforsikringsTekst += "|" + toStringInnboforsikring((Innboforsikring) forsikring) + "|";
                    //innboforsikringer.add(toStringInnboforsikring((Innboforsikring) forsikring));

                }else if (forsikring instanceof Reiseforsikring){
                    System.out.println("Reise");

                    reiseforsikringsTekst += "|" + toStringReiseforsirking((Reiseforsikring) forsikring) + "|";
                    //reiseforsikringer.add(toStringReiseforsirking((Reiseforsikring) forsikring));
                }
            }
        }
        System.out.println("Båtforsikring: " + båtforsikringsTekst);


        return båtforsikringsTekst + "\" , \"" + innboforsikringsTekst + "\" , \"" + reiseforsikringsTekst;
    }

    private static String håndterSkademeldinger(ObservableList<Skademelding> skademeldingsListe){
        String skademeldingsTekst = "";
        if (skademeldingsListe.size() > 0){
            for (Skademelding skademelding : skademeldingsListe) {
                skademeldingsTekst += "|" + toStringSkademelding(skademelding);
            }
        }
        return skademeldingsTekst;
    }

    public static String håndterkundeobjekt(Kunde kunde){
        String kundeTekst = toStringKunde(kunde);
        String forsikringsTekst = håndterForsikringsListe(kunde.getForsikringsListe());
        String skademeldingsTekst = håndterSkademeldinger(kunde.getSkademeldinger());
        return "\"" + kundeTekst + "\" , \"" + forsikringsTekst + "\" , \"" + skademeldingsTekst + "\"";
    }
}
