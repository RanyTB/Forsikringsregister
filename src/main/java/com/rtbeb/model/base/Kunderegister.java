package com.rtbeb.model.base;

import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.base.exception.InvalidKundeException;
import com.rtbeb.model.base.forsikring.Båt.Båt;
import com.rtbeb.model.base.forsikring.Båt.Båtforsikring;
import com.rtbeb.model.base.forsikring.Båt.Eier;
import com.rtbeb.model.base.forsikring.Skademelding;
import com.rtbeb.model.validation.KundeValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Kunderegisteret er en Singleton-klasse som fungerer som systemets register. Registeret inneholder alle
 * kundeobjektene.
 */
public class Kunderegister {

    private final ObservableList<Kunde> kundeliste = FXCollections.observableArrayList();

    private static Kunderegister instance = new Kunderegister();

    public static Kunderegister getInstance(){
        return instance;
    }

    //TODO fjern eller kommenter ut konstruktør når testing er fullført
    private Kunderegister(){

        //Konstruktøren brukes foreløpig bare til testing for å initialisere kunderegisteret med dummy-kunder.

        //Tester innsetting av kunder i kunderegisteret
        Kunde kunde1 = new Kunde("Rany Tarek","Bouorm","Ellen Gleditsch Vei 25","0987");
        Kunde kunde2 = new Kunde("Maimona","Javed","Munkebekken 100","0954");
        kundeliste.addAll(kunde1, kunde2);

        //Tester opprettelse av båtforsikring
        Eier eier = new Eier("Ola", "Nordmann", LocalDate.now().minusYears(10));
        Båt båt = new Båt(eier, "pp4444", "Tekst", "Tekst", "300", "2004", "400hk");
        Båtforsikring båtforsikring = new Båtforsikring(20000, 300000, "roawijrawoj", båt);

        //Tester opprettelse av skademeldinge
        Skademelding skademelding1 = new Skademelding(LocalDate.now(), "Påkjørlse", "Påkjørt fra babord", "Truls tlf 12738492", "50000", "10000");
        Skademelding skademelding2 = new Skademelding(LocalDate.now(), "Brann", "Brann fra sikringsskap", "Linda tlf 89372645", "70000", "50000");

        kunde1.addSkademelding(skademelding1);
        kunde1.addSkademelding(skademelding2);

        Skademelding skademelding3 = new Skademelding(LocalDate.now(), "Påkjørlse", "Påkjørt fra babord", "Truls tlf 12738492", "50000", "0");
        Skademelding skademelding4 = new Skademelding(LocalDate.now(), "Brann", "Brann fra sikringsskap", "Linda tlf 89372645", "70000", "0");

        kunde1.addSkademelding(skademelding3);
        kunde1.addSkademelding(skademelding4);

        //Tester innsetting av båtforsikring i et kundeobjekt.
        try {
            kunde1.addForsikring(båtforsikring);
        } catch (InvalidForsikringException e) {
            e.printStackTrace();
        }

    }

    /**
     * Validerer og setter inn en kunde i kunderegisteret
     * @param kunde Kunden som skal settes inn.
     * @throws InvalidKundeException dersom kunden er ugyldig.
     */
    public void insertKunde(Kunde kunde) throws InvalidKundeException {
        if(KundeValidator.kundeIsValid(kunde)) {
            kundeliste.add(kunde);
        } else{
            throw new InvalidKundeException("Ugyldig kunde");
        }
    }

    /**
     * Sletter en kunde fre kunderegisteret.
     * @param kunde Kunden som skal slettes.
     */
    public void deleteKunde(Kunde kunde){

        kundeliste.remove(kunde);
    }


    /**
     * Brukes for å sette en ny kundeliste. F.eks etter import fra fil.
     * Metoden fjerner alle objekter i kundelisten og legger til de nye objektene.
     * Det kreves dermed ikke at Listeneres binder seg til kundelisten på nytt.
     * @param kundeliste kundelisten som skal settes.
     */
    public void setKundeliste(ArrayList<Kunde> kundeliste){

        //TODO Se over implementasjonen av denne setmetoden.
        /*
        Denne bruker vi til å sette kunderegisteret etter import fra fil.
        Tenker derfor at vi setter alle kunder i en ArrayList når disse leses fra fil, og deretter omgjør til
        observablelist her. */
        //this.kundeliste = FXCollections.observableList(kundeliste);
        this.kundeliste.clear();
        this.kundeliste.addAll(kundeliste);
    }

    public ObservableList<Kunde> getKundeliste(){
        return kundeliste;
    }

}
