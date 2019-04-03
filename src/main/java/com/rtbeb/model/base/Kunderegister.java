package com.rtbeb.model.base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;

public class Kunderegister{

    ObservableList<Kunde> alleKunder = FXCollections.observableArrayList();

    public Kunderegister(){
        //Brukes foreløpig bare til testing for å initialisere kunderegisteret med kunder.
        Kunde kunde1 = new Kunde("Rany Tarek","Bouorm","Ellen Gleditsch Vei 25","0987" ,12345, new Date());
        Kunde kunde2 = new Kunde("Maimona","Javed","Munkebekken 100","0954" ,12346, new Date());

        alleKunder.addAll(kunde1, kunde2);
    }

    public void setAlleKunder(ArrayList<Kunde> alleKunder){
        /*Se over implementasjonen av denne setmetoden.
        Denne bruker vi til å sette kunderegisteret etter import fra fil.
        Tenker derfor at vi setter alle kunder i en ArrayList når disse leses fra fil, og deretter omgjør til
        observablelist her. */
        this.alleKunder = FXCollections.observableList(alleKunder);
    }

    public ObservableList<Kunde> getAlleKunder(){
        return alleKunder;
    }

}
