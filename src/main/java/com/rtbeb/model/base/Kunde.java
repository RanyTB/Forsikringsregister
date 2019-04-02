package com.rtbeb.model.base;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.util.Date;

public class Kunde {

    private StringProperty fornavn;
    private StringProperty etternavn;
    private StringProperty fakturaadresse;
    private IntegerProperty forsikringsnummer;
    private ObjectProperty<Date> kundeOpprettelsesDato;

    //Observable lister over forsikringer, skademeldinger og ubetalte erstatninger.
    private ObservableList<Forsikring> forsikringer;
    private ObservableList<Skademelding> skademeldinger;
    private ObservableList<UbetaltErstatning> ubetalteErstatninger;


    public Kunde(String fornavn, String etternavn, String fakturaadresse,
                 Integer forsikringnummer,
                 Date kundeOpprettelsesDato) {
        this.fornavn = new SimpleStringProperty(this,"fornavn",fornavn);
        this.etternavn = new SimpleStringProperty(this,"etternavn",etternavn);
        this.fakturaadresse = new SimpleStringProperty(this,"fakturaadresse",fakturaadresse);
        this.forsikringsnummer = new SimpleIntegerProperty(this,"forsikringsnummer", forsikringnummer);
        this.kundeOpprettelsesDato = new SimpleObjectProperty<Date>(this,"kundeOpprettelsesDato", kundeOpprettelsesDato);
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

    public int getForsikringnummer() {
        return forsikringsnummer.get();
    }

    public IntegerProperty forsikringnummerProperty() {
        return forsikringsnummer;
    }

    public void setForsikringnummer(int forsikringnummer) {
        this.forsikringsnummer.set(forsikringnummer);
    }

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

    public Date getKundeOpprettelsesDato() {
        return kundeOpprettelsesDato.get();
    }

    public ObjectProperty<Date> kundeOpprettelsesDatoProperty() {
        return kundeOpprettelsesDato;
    }

    public void setKundeOpprettelsesDato(Date kundeOpprettelsesDato) {
        this.kundeOpprettelsesDato.set(kundeOpprettelsesDato);
    }


}
