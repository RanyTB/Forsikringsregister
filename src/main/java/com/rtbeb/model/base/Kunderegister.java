package com.rtbeb.model.base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class Kunderegister{

    private ObservableList<Kunde> kundeliste = FXCollections.observableArrayList();

    //Singleton klasse med privat konstruktør. Instanse hentes med getInstance();
    private static Kunderegister instance = new Kunderegister();

    private Kunderegister(){

        //TODO
        //Konstruktøren brukes foreløpig bare til testing for å initialisere kunderegisteret med dummy-kunder.
        LocalDate dato1 = LocalDate.now();
        LocalDate dato2 = LocalDate.now();

        Kunde kunde1 = new Kunde("Rany Tarek","Bouorm","Ellen Gleditsch Vei 25","0987" ,12345, dato1);
        Kunde kunde2 = new Kunde("Maimona","Javed","Munkebekken 100","0954" ,12346, dato2);

        kundeliste.addAll(kunde1, kunde2);
    }

    public static Kunderegister getInstance(){
        return instance;
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
