package com.rtbeb.model.base;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Kunde implements Serializable {
    private static final long serialVersionUID = 1;
    /*Kundeklassen bruker properties, som fungerer som en wrapper for klassene med muligheten
    for å binde listeners. Dette gjør at en TableView automatisk vil oppdatere properties
     som får nye verdier.*/
    private transient StringProperty fornavn;
    private transient StringProperty etternavn;
    private transient StringProperty fakturaadresse;
    private transient StringProperty postnummer;
    private transient IntegerProperty forsikringsnummer;
    private transient ObjectProperty<LocalDate> kundeOpprettelsesDato;

    //TODO Implementer disse
    //Observable lister over forsikringer, skademeldinger og ubetalte erstatninger.
    //private ObservableList<Forsikring> forsikringer;
    //private ObservableList<Skademelding> skademeldinger;

    //private ObservableList<UbetaltErstatning> ubetalteErstatninger;

    public Kunde(String fornavn, String etternavn, String fakturaadresse, String postnummer,
                 Integer forsikringsnummer,
                 LocalDate kundeOpprettelsesDato) {
        this.fornavn = new SimpleStringProperty(this,"fornavn",fornavn);
        this.etternavn = new SimpleStringProperty(this,"etternavn",etternavn);
        this.fakturaadresse = new SimpleStringProperty(this,"fakturaadresse",fakturaadresse);
        this.postnummer = new SimpleStringProperty(this, "postnummer", postnummer);
        this.forsikringsnummer = new SimpleIntegerProperty(this,"forsikringsnummer", forsikringsnummer);
        this.kundeOpprettelsesDato = new SimpleObjectProperty<>(this,"kundeOpprettelsesDato", kundeOpprettelsesDato);
    }

    public String getFornavn() {
        return fornavn.get();
    }

    public StringProperty fornavnProperty() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn.set(fornavn);
    }

    public String getEtternavn() {
        return etternavn.get();
    }

    public StringProperty etternavnProperty() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn.set(etternavn);
    }

    public String getFakturaadresse() {
        return fakturaadresse.get();
    }

    public StringProperty fakturaadresseProperty() {
        return fakturaadresse;
    }

    public void setFakturaadresse(String fakturaadresse) {
        this.fakturaadresse.set(fakturaadresse);
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

    public int getForsikringsnummer() {
        return forsikringsnummer.get();
    }

    public IntegerProperty forsikringsnummerProperty() {
        return forsikringsnummer;
    }

    public void setForsikringsnummer(int forsikringsnummer) {
        this.forsikringsnummer.set(forsikringsnummer);
    }


    /*TODO Implementer disse

    public ObservableList<Forsikring> getForsikringer() {
        return forsikringer;
    }

    public void setForsikringer(ObservableList<Forsikring> forsikringer) {
        this.forsikringer = forsikringer;
    }

    public ObservableList<Skademelding> getSkademeldinger() {
        return skademeldinger;
    }

    public void setSkademeldinger(ObservableList<Skademelding> skademeldinger) {
        this.skademeldinger = skademeldinger;
    }

    public ObservableList<UbetaltErstatning> getUbetalteErstatninger() {
        return ubetalteErstatninger;
    }

    public void setUbetalteErstatninger(ObservableList<UbetaltErstatning> ubetalteErstatninger) {
        this.ubetalteErstatninger = ubetalteErstatninger;
    }
    ----------------------------------------------------------------------------------------------*/

    public LocalDate getKundeOpprettelsesDato() {
        return kundeOpprettelsesDato.get();
    }

    public ObjectProperty<LocalDate> kundeOpprettelsesDatoProperty() {
        return kundeOpprettelsesDato;
    }

    public void setKundeOpprettelsesDato(LocalDate kundeOpprettelsesDato) {
        this.kundeOpprettelsesDato.set(kundeOpprettelsesDato);
    }

    /*private void writeObject (ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(fornavn.get());
        objectOutputStream.writeObject(etternavn.get());
        objectOutputStream.writeObject(fakturaadresse.get());
        objectOutputStream.writeObject(postnummer.get());
        objectOutputStream.writeObject(forsikringsnummer.get());
        objectOutputStream.writeObject(kundeOpprettelsesDato.get());
    }*/


    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getFornavn());
        objectOutputStream.writeObject(getEtternavn());
        objectOutputStream.writeObject(getFakturaadresse());
        objectOutputStream.writeObject(getPostnummer());
        objectOutputStream.writeObject(getForsikringsnummer());
        objectOutputStream.writeObject(getKundeOpprettelsesDato());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException , ClassNotFoundException{
        objectInputStream.defaultReadObject();
        fornavn = new SimpleStringProperty((String) objectInputStream.readObject());
        etternavn = new SimpleStringProperty((String) objectInputStream.readObject());
        fakturaadresse = new SimpleStringProperty((String) objectInputStream.readObject());
        postnummer = new SimpleStringProperty((String) objectInputStream.readObject());
        forsikringsnummer = new SimpleIntegerProperty((Integer) objectInputStream.readObject());
        kundeOpprettelsesDato = new SimpleObjectProperty<LocalDate>((LocalDate) objectInputStream.readObject());
    }

}
