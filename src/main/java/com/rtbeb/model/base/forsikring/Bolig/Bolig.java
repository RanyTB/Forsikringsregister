package com.rtbeb.model.base.forsikring.Bolig;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Bolig implements Serializable {

    StringProperty adresse;
    StringProperty postnummer;
    StringProperty byggeår;
    StringProperty boligtype;
    StringProperty byggemateriale;
    StringProperty standard;
    StringProperty størrelse;

    public Bolig(String adresse, String postnummer, String byggeår, String boligtype,
                 String byggemateriale, String standard, String størrelse) {

        this.adresse = new SimpleStringProperty(adresse);
        this.postnummer = new SimpleStringProperty(postnummer);
        this.byggeår = new SimpleStringProperty(byggeår);
        this.boligtype = new SimpleStringProperty(boligtype);
        this.byggemateriale = new SimpleStringProperty(byggemateriale);
        this.standard = new SimpleStringProperty(standard);
        this.størrelse = new SimpleStringProperty(størrelse);
    }

    public String getAdresse() {
        return adresse.get();
    }

    public StringProperty adresseProperty() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public String getPostnummer() {
        return postnummer.get();
    }

    public StringProperty postnummerProperty() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer.set(postnummer);
    }

    public String getByggeår() {
        return byggeår.get();
    }

    public StringProperty byggeårProperty() {
        return byggeår;
    }

    public void setByggeår(String byggeår) {
        this.byggeår.set(byggeår);
    }

    public String getBoligtype() {
        return boligtype.get();
    }

    public StringProperty boligtypeProperty() {
        return boligtype;
    }

    public void setBoligtype(String boligtype) {
        this.boligtype.set(boligtype);
    }

    public String getByggemateriale() {
        return byggemateriale.get();
    }

    public StringProperty byggematerialeProperty() {
        return byggemateriale;
    }

    public void setByggemateriale(String byggemateriale) {
        this.byggemateriale.set(byggemateriale);
    }

    public String getStandard() {
        return standard.get();
    }

    public StringProperty standardProperty() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard.set(standard);
    }

    public String getStørrelse() {
        return størrelse.get();
    }

    public StringProperty størrelseProperty() {
        return størrelse;
    }

    public void setStørrelse(String størrelse) {
        this.størrelse.set(størrelse);
    }


    //Egendefinert serialisering
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getAdresse());
        objectOutputStream.writeObject(getByggeår());
        objectOutputStream.writeObject(getPostnummer());
        objectOutputStream.writeObject(getByggeår());
        objectOutputStream.writeObject(getBoligtype());
        objectOutputStream.writeObject(getByggemateriale());
        objectOutputStream.writeObject(getStandard());
        objectOutputStream.writeObject(getStørrelse());
    }

    //Egendefinert serialisering
    private void readObject(ObjectInputStream objectInputStream) throws IOException , ClassNotFoundException{
        objectInputStream.defaultReadObject();
        this.adresse = new SimpleStringProperty( (String) objectInputStream.readObject());
        this.postnummer = new SimpleStringProperty( (String) objectInputStream.readObject());
        this.byggeår = new SimpleStringProperty( (String) objectInputStream.readObject());
        this.boligtype = new SimpleStringProperty( (String) objectInputStream.readObject());
        this.byggemateriale = new SimpleStringProperty( (String) objectInputStream.readObject());
        this.standard = new SimpleStringProperty( (String) objectInputStream.readObject());
        this.størrelse = new SimpleStringProperty( (String) objectInputStream.readObject());
    }
}
