package com.rtbeb.model.base;

import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.base.exception.InvalidSkademeldingException;
import com.rtbeb.model.base.forsikring.Forsikring;
import com.rtbeb.model.base.forsikring.Skademelding;
import com.rtbeb.model.base.forsikring.Validerbar;
import com.rtbeb.model.validation.KundeValidator;
import javafx.beans.Observable;
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

/**
 * Klasse for Kunde. Brukes som hovedobjektet i registreringssystemet. Kunden er objektet som
 * innehar alle forsikringer og skademeldinger.
 * Klassen bruker properties, som fungerer som en wrapper for feltene, med muligheten
 * for å binde listeners. Dette gjør at en TableView automatisk vil oppdatere properties
 * som får nye verdier.
 * @author Rany Tarek Bouorm - s236210
 */

public class Kunde implements Serializable, Validerbar {
    private static final long serialVersionUID = 1;

    //Counteren sørger for threadsafe utdeling av forsikringsnummer til nye kunder med metoden getAndIncrement();
    private transient static final AtomicInteger forsikringnummerCounter = new AtomicInteger(100000);
    private transient StringProperty fornavn;
    private transient StringProperty etternavn;
    private transient StringProperty fakturaadresse;
    private transient StringProperty postnummer;
    private transient IntegerProperty forsikringsnummer;
    private transient ObjectProperty<LocalDate> kundeOpprettelsesDato;

    private transient ObservableList<Forsikring> forsikringsListe = FXCollections.observableArrayList();

    /*Skademeldingsliste med extractor. Dette sørger for at update event blir trigget når utbetalt erstatningsbeløp blir endret.*/
    private transient ObservableList<Skademelding> skademeldinger = FXCollections.observableArrayList(skademelding ->
            new Observable[] {skademelding.utbetaltErstatningsbeløpProperty(), skademelding.takseringAvSkadenProperty()});

    public Kunde(String fornavn, String etternavn, String fakturaadresse, String postnummer) {
        this.fornavn = new SimpleStringProperty(this,"fornavn",fornavn);
        this.etternavn = new SimpleStringProperty(this,"etternavn",etternavn);
        this.fakturaadresse = new SimpleStringProperty(this,"fakturaadresse",fakturaadresse);
        this.postnummer = new SimpleStringProperty(this, "postnummer", postnummer);
        this.forsikringsnummer = new SimpleIntegerProperty(this,"forsikringsnummer", forsikringnummerCounter.getAndIncrement());
        this.kundeOpprettelsesDato = new SimpleObjectProperty<>(this,"kundeOpprettelsesDato", LocalDate.now());
    }

    /**
     * Konstruktøren tillater innlesing av eksisterende kunder. Forsikringsnummer og kundeOpprettelsesDato settes i konstruktøren.
     */
    public Kunde(String fornavn, String etternavn, String fakturaadresse, String postnummer, int forsikringsnummer, LocalDate datoOpprettet) {
        this.fornavn = new SimpleStringProperty(this,"fornavn",fornavn);
        this.etternavn = new SimpleStringProperty(this,"etternavn",etternavn);
        this.fakturaadresse = new SimpleStringProperty(this,"fakturaadresse",fakturaadresse);
        this.postnummer = new SimpleStringProperty(this, "postnummer", postnummer);
        this.forsikringsnummer = new SimpleIntegerProperty(this,"forsikringsnummer", forsikringsnummer);
        this.kundeOpprettelsesDato = new SimpleObjectProperty<>(this,"kundeOpprettelsesDato", datoOpprettet);
        System.out.println("Legger inn kunde med forsikringsnummer " + forsikringsnummer);
        updateForsikringsNummerCounter(forsikringsnummer);
    }

    /**
     * Oppdaterer forsikringsnummer telleren til høyeste nummeret lagt inn + 1.
     * @param innlastetForsikringsnummer Forsikringsnummeret for kunden som settes inn i registeret.
     */
    private void updateForsikringsNummerCounter(int innlastetForsikringsnummer){
        //Sett counteren til høyeste forsikringsnummer + 1 når nye kunder opprettes.
        if(innlastetForsikringsnummer >= forsikringnummerCounter.get()){
            forsikringnummerCounter.set(innlastetForsikringsnummer + 1);
            System.out.println("Counter oppdateres til " + innlastetForsikringsnummer + 1);
        }
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

    public Integer getForsikringsnummer() {
        return forsikringsnummer.get();
    }

    public IntegerProperty forsikringsnummerProperty() {
        return forsikringsnummer;
    }

    public LocalDate getKundeOpprettelsesDato() {
        return kundeOpprettelsesDato.get();
    }

    public ObjectProperty<LocalDate> kundeOpprettelsesDatoProperty() {
        return kundeOpprettelsesDato;
    }


    //--------------------KUNDEINFO END---------------------//



    //--------------------FORSIKRINGER---------------------//

    public ObservableList<Forsikring> getForsikringsListe() {
        return forsikringsListe;
    }
    public ArrayList<Forsikring> getForsikringsListeAsArrayList(){

        return new ArrayList<>(getForsikringsListe());
    }


    public void addForsikring(Forsikring forsikring) throws InvalidForsikringException {

        if(forsikring.isValid()){
            this.forsikringsListe.add(forsikring);
        } else{
            throw new InvalidForsikringException("Ugyldig forsikringsparametre i forsikring: " + forsikring.getForsikringstype() );
        }
    }

    public void slettForsikring(Forsikring forsikring){
        forsikringsListe.remove(forsikring);
    }

    //--------------------FORSIKRINGER END-----------------//


    //--------------------SKADEMELDINGER---------------------//
    public ObservableList<Skademelding> getSkademeldinger() {
        return skademeldinger;
    }

    public ArrayList<Skademelding> getSkademeldingerAsArrayList(){
        return new ArrayList<>(getSkademeldinger());
    }

    public void addSkademelding(Skademelding skademelding) throws InvalidSkademeldingException {
        if(skademelding.isValid()){
            this.skademeldinger.add(skademelding);
        } else {
            throw new InvalidSkademeldingException("Ugyldige verdier i skademelding");
        }
    }

    private void addSkademeldingerFromArray(ArrayList<Skademelding> list){
        this.skademeldinger.addAll(list);
    }

    public void slettSkademelding(Skademelding skademelding){
        this.skademeldinger.remove(skademelding);
    }

    //--------------------SKADEMELDINGER END---------------------//


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
        this.forsikringsnummer = new SimpleIntegerProperty((Integer) objectInputStream.readObject());
        this.kundeOpprettelsesDato = new SimpleObjectProperty<>((LocalDate) objectInputStream.readObject());
        this.forsikringsListe = FXCollections.observableArrayList( (ArrayList<Forsikring>) objectInputStream.readObject());

        //Setter opp skademeldingslisten med extractor for å støtte funksjonalitet for ubetalte erstatninger.
        this.skademeldinger = FXCollections.observableList( (ArrayList<Skademelding>) objectInputStream.readObject(), skademelding ->
                new Observable[] {skademelding.utbetaltErstatningsbeløpProperty(), skademelding.takseringAvSkadenProperty()} );

        //Sett counteren til høyeste forsikringsnummer + 1 når nye kunder opprettes.
        updateForsikringsNummerCounter( this.forsikringsnummer.getValue());
    }

    @Override
    public boolean isValid() {
        return KundeValidator.kundeIsValid(this);
    }
}
