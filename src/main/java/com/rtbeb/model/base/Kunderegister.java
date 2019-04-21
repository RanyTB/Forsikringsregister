package com.rtbeb.model.base;

import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.base.exception.InvalidKundeException;
import com.rtbeb.model.base.forsikring.Båt;
import com.rtbeb.model.base.forsikring.Båtforsikring;
import com.rtbeb.model.base.forsikring.Eier;
import com.rtbeb.model.validation.KundeValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Kunderegister {

    //private static final long serialVersionUID = 1;

    private ObservableList<Kunde> kundeliste = FXCollections.observableArrayList();

    //Singleton klasse med privat konstruktør. Instanse hentes med getInstance();
    private static Kunderegister instance = new Kunderegister();

    public static Kunderegister getInstance(){
        return instance;
    }

    private Kunderegister(){

        //TODO fjern konstruktør når testing er fullført
        //Konstruktøren brukes foreløpig bare til testing for å initialisere kunderegisteret med dummy-kunder.

        //Tester innsetting av kunder i kunderegisteret
        Kunde kunde1 = new Kunde("Rany Tarek","Bouorm","Ellen Gleditsch Vei 25","0987");
        Kunde kunde2 = new Kunde("Maimona","Javed","Munkebekken 100","0954");
        kundeliste.addAll(kunde1, kunde2);

        //Tester opprettelse av båtforsikring
        Eier eier = new Eier("Ola", "Nordmann", LocalDate.now());
        Båt båt = new Båt(eier, "4444", "Tekst", "Tekst", "300", "2004", "400hk");
        Båtforsikring båtforsikring = new Båtforsikring(20000, 300000, "roawijrawoj", båt);

        //Tester innsetting av båtforsikring i et kundeobjekt.
        try {
            kunde1.addForsikring(båtforsikring);
        } catch (InvalidForsikringException e) {
            e.printStackTrace();
        }

    }

    public void insertKunde(Kunde kunde) throws InvalidKundeException {
        if(KundeValidator.kundeIsValid(kunde)) {
            kundeliste.add(kunde);
        } else{
            throw new InvalidKundeException("Ugyldig kunde");
        }
    }

    public void deleteKunde(Kunde kunde){

        //TODO Fjern iterator, bruk remove().
        Iterator<Kunde> iterator = kundeliste.iterator();

        while(iterator.hasNext()){
            Kunde currentKunde = iterator.next();
            if (currentKunde == kunde){
                iterator.remove();
                break;
            }
        }
    }


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
