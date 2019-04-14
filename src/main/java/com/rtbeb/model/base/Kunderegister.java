package com.rtbeb.model.base;

import com.rtbeb.model.base.exception.InvalidKundeException;
import com.rtbeb.model.validation.KundeValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;

public class Kunderegister{

    private ObservableList<Kunde> kundeliste = FXCollections.observableArrayList();

    //Singleton klasse med privat konstruktør. Instanse hentes med getInstance();
    private static Kunderegister instance = new Kunderegister();

    public static Kunderegister getInstance(){
        return instance;
    }

    private Kunderegister(){

        //TODO fjern konstruktør når testing er fullført
        //Konstruktøren brukes foreløpig bare til testing for å initialisere kunderegisteret med dummy-kunder.

        Kunde kunde1 = new Kunde("Rany Tarek","Bouorm","Ellen Gleditsch Vei 25","0987");
        Kunde kunde2 = new Kunde("Maimona","Javed","Munkebekken 100","0954");

        kundeliste.addAll(kunde1, kunde2);
    }

    public void insertKunde(Kunde kunde) throws InvalidKundeException {
        if(KundeValidator.kundeIsValid(kunde)) {
            kundeliste.add(kunde);
        } else{
            throw new InvalidKundeException("Ugyldig kunde");
        }
    }

    public void deleteKunde(Kunde kunde){

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
        this.kundeliste = FXCollections.observableList(kundeliste);
    }

    public ObservableList<Kunde> getKundeliste(){
        return kundeliste;
    }

}
