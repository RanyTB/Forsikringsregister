package com.rtbeb.model.base;

import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.base.exception.InvalidSkademeldingException;
import com.rtbeb.model.base.forsikring.Forsikring;
import com.rtbeb.model.validation.ForsikringValidator;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Kunde implements Serializable {
    private static final long serialVersionUID = 1;
    /*Kundeklassen bruker properties, som fungerer som en wrapper for klassene med muligheten
    for å binde listeners. Dette gjør at en TableView automatisk vil oppdatere properties
     som får nye verdier.*/

    //Counteren sørger for threadsafe utdeling av forsikringsnummer til nye kunder med metoden getAndIncrement();
    private transient static final AtomicInteger forsikringnummerCounter = new AtomicInteger(100000);
    private transient StringProperty fornavn;
    private transient StringProperty etternavn;
    private transient StringProperty fakturaadresse;
    private transient StringProperty postnummer;
    private transient LongProperty forsikringsnummer;
    private transient ObjectProperty<LocalDate> kundeOpprettelsesDato;

    //TODO Eirik: implementer lagring og lesing av denne:
    private transient ObservableList<Forsikring> forsikringsListe = FXCollections.observableArrayList();

    //TODO Implementer observable-lister over skademeldinger og ubetalte erstatninger.
    private transient ObservableList<Skademelding> skademeldinger = FXCollections.observableArrayList();
    //private ObservableList<UbetaltErstatning> ubetalteErstatninger;

    public Kunde(String fornavn, String etternavn, String fakturaadresse, String postnummer) {
        this.fornavn = new SimpleStringProperty(this,"fornavn",fornavn);
        this.etternavn = new SimpleStringProperty(this,"etternavn",etternavn);
        this.fakturaadresse = new SimpleStringProperty(this,"fakturaadresse",fakturaadresse);
        this.postnummer = new SimpleStringProperty(this, "postnummer", postnummer);
        this.forsikringsnummer = new SimpleLongProperty(this,"forsikringsnummer", forsikringnummerCounter.getAndIncrement());
        this.kundeOpprettelsesDato = new SimpleObjectProperty<>(this,"kundeOpprettelsesDato", LocalDate.now());
    }

    //TODO Bruk denne for opprettelse av kunder ved fillesing fra csv.
    public Kunde(String fornavn, String etternavn, String fakturaadresse, String postnummer, long forsikringsnummer, LocalDate datoOpprettet) {
        this.fornavn = new SimpleStringProperty(this,"fornavn",fornavn);
        this.etternavn = new SimpleStringProperty(this,"etternavn",etternavn);
        this.fakturaadresse = new SimpleStringProperty(this,"fakturaadresse",fakturaadresse);
        this.postnummer = new SimpleStringProperty(this, "postnummer", postnummer);
        this.forsikringsnummer = new SimpleLongProperty(this,"forsikringsnummer", forsikringnummerCounter.getAndIncrement());
        this.kundeOpprettelsesDato = new SimpleObjectProperty<>(this,"kundeOpprettelsesDato", LocalDate.now());
    }

    //----------------KUNDEINFO-----------------------//

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

    public long getForsikringsnummer() {
        return forsikringsnummer.get();
    }

    public LongProperty forsikringsnummerProperty() {
        return forsikringsnummer;
    }

    public void setForsikringsnummer(long forsikringsnummer) {
        this.forsikringsnummer.set(forsikringsnummer);
    }

    public LocalDate getKundeOpprettelsesDato() {
        return kundeOpprettelsesDato.get();
    }

    public ObjectProperty<LocalDate> kundeOpprettelsesDatoProperty() {
        return kundeOpprettelsesDato;
    }

    public void setKundeOpprettelsesDato(LocalDate kundeOpprettelsesDato) {
        this.kundeOpprettelsesDato.set(kundeOpprettelsesDato);
    }

    //--------------------KUNDEINFO END---------------------//



    //--------------------FORSIKRINGER---------------------//

    public ObservableList<Forsikring> getForsikringsListe() {
        return forsikringsListe;
    }
    public ArrayList<Forsikring> getForsikringsListeAsArrayList(){
        return new ArrayList<Forsikring>(getForsikringsListe());
    }

    public void setForsikringsListe(ObservableList<Forsikring> forsikringsListe) {
        this.forsikringsListe = forsikringsListe;
    }

    public void addForsikring(Forsikring forsikring) throws InvalidForsikringException {
        if(ForsikringValidator.ForsikringIsValid(forsikring)){
            this.forsikringsListe.add(forsikring);
        } else{
            throw new InvalidForsikringException("Ugyldig forsikring");
        }
    }

    //--------------------FORSIKRINGER END-----------------//


    //--------------------SKADEMELDINGER---------------------//
    public ObservableList<Skademelding> getSkademeldinger() {
        return skademeldinger;
    }

    public ArrayList<Skademelding> getSkademeldingerAsArrayList(){
        return new ArrayList<Skademelding>(getSkademeldinger());
    }

    public void setSkademeldinger(ObservableList<Skademelding> skademeldinger) {
        this.skademeldinger = skademeldinger;
    }

    public void addSkademelding(Skademelding skademelding){
        this.skademeldinger.add(skademelding);

    }

    public void slettSkademelding(Skademelding skademelding){
        this.skademeldinger.remove(skademelding);
    }

    //--------------------SKADEMELDINGER END---------------------//


    /*
    public ObservableList<UbetaltErstatning> getUbetalteErstatninger() {
        return ubetalteErstatninger;
    }

    public void setUbetalteErstatninger(ObservableList<UbetaltErstatning> ubetalteErstatninger) {
        this.ubetalteErstatninger = ubetalteErstatninger;
    }
    ----------------------------------------------------------------------------------------------*/


    //Egendefinert serialisering
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getFornavn());
        objectOutputStream.writeObject(getEtternavn());
        objectOutputStream.writeObject(getFakturaadresse());
        objectOutputStream.writeObject(getPostnummer());
        objectOutputStream.writeObject(getForsikringsnummer());
        objectOutputStream.writeObject(getKundeOpprettelsesDato());
        objectOutputStream.writeObject(getForsikringsListeAsArrayList());
        objectOutputStream.writeObject(getSkademeldingerAsArrayList());
    }

    //Egendefinert serialisering
    private void readObject(ObjectInputStream objectInputStream) throws IOException , ClassNotFoundException{
        objectInputStream.defaultReadObject();
        this.fornavn = new SimpleStringProperty((String) objectInputStream.readObject());
        this.etternavn = new SimpleStringProperty((String) objectInputStream.readObject());
        this.fakturaadresse = new SimpleStringProperty((String) objectInputStream.readObject());
        this.postnummer = new SimpleStringProperty((String) objectInputStream.readObject());
        this.forsikringsnummer = new SimpleLongProperty((Long) objectInputStream.readObject());
        this.kundeOpprettelsesDato = new SimpleObjectProperty<LocalDate>((LocalDate) objectInputStream.readObject());
        this.forsikringsListe = FXCollections.observableArrayList( (ArrayList<Forsikring>) objectInputStream.readObject());
        this.skademeldinger = FXCollections.observableArrayList( (ArrayList<Skademelding>) objectInputStream.readObject());
    }

}
