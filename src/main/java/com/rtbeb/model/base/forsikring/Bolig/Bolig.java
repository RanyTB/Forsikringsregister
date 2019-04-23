package com.rtbeb.model.base.forsikring.Bolig;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Bolig implements Serializable {

    private transient StringProperty adresse;
    private transient StringProperty postnummer;
    private transient StringProperty byggeår;
    private transient ObjectProperty<Boligtype> boligtype;
    private transient ObjectProperty<Byggemateriale> byggemateriale;
    private transient ObjectProperty<Standard> standard;
    private transient StringProperty størrelse;

    public Bolig(String adresse, String postnummer, String byggeår, Boligtype boligtype,
                 Byggemateriale byggemateriale, Standard standard, String størrelse) {

        this.adresse = new SimpleStringProperty(adresse);
        this.postnummer = new SimpleStringProperty(postnummer);
        this.byggeår = new SimpleStringProperty(byggeår);
        this.boligtype = new SimpleObjectProperty<Boligtype>(boligtype);
        this.byggemateriale = new SimpleObjectProperty<Byggemateriale>(byggemateriale);
        this.standard = new SimpleObjectProperty<Standard>(standard);
        this.størrelse = new SimpleStringProperty(størrelse);
    }

    /**
     * Lovlige boligtype-verdier.
     */
    public enum Boligtype{
        LEILIGHET("Leilighet"),
        ENEBOLIG("Enebolig"),
        REKKEHUS("Rekkehus"),
        TOMANNSBOLIG("Tomannsbolig"),
        GÅRDSBRUK("Gårdsbruk"),
        ANNET("Annet");

        private final String navn;

        private Boligtype(String navn){
            this.navn = navn;
        }

        @Override
        public String toString(){
            return navn;
        }
    }

    /**
     * Lovlige Byggemateriale-verdier.
     */
    public enum Byggemateriale{
        TRE("Tre"),
        BETONG("Betong"),
        ANNET("Annet");

        private String navn;

        private Byggemateriale(String navn){
            this.navn = navn;
        }

        @Override
        public String toString(){
            return navn;
        }
    }

    /**
     * Lovlige Standard-verdier.
     */
    public enum Standard{
        LAV("Lav"),
        MIDDELS("Middels"),
        HØY("Høy"),
        NY("Ny bolig");

        private String navn;

        private Standard(String navn){
            this.navn = navn;
        }

        @Override
        public String toString(){
            return navn;
        }

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

    public Boligtype getBoligtype() {
        return boligtype.get();
    }

    public ObjectProperty<Boligtype> boligtypeProperty() {
        return boligtype;
    }

    public void setBoligtype(Boligtype boligtype) {
        this.boligtype.set(boligtype);
    }

    public Byggemateriale getByggemateriale() {
        return byggemateriale.get();
    }

    public ObjectProperty<Byggemateriale> byggematerialeProperty() {
        return byggemateriale;
    }

    public void setByggemateriale(Byggemateriale byggemateriale) {
        this.byggemateriale.set(byggemateriale);
    }

    public Standard getStandard() {
        return standard.get();
    }

    public ObjectProperty<Standard> standardProperty() {
        return standard;
    }

    public void setStandard(Standard standard) {
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
        this.boligtype = new SimpleObjectProperty<>( (Boligtype) objectInputStream.readObject());
        this.byggemateriale = new SimpleObjectProperty<>( (Byggemateriale) objectInputStream.readObject());
        this.standard = new SimpleObjectProperty<>( (Standard) objectInputStream.readObject());
        this.størrelse = new SimpleStringProperty( (String) objectInputStream.readObject());
    }
}
