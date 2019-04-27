package com.rtbeb.model.base.forsikring;

import com.rtbeb.model.validation.SkademeldingValidator;
import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Eirik bøyum
 */
public class Skademelding implements Serializable, Validerbar {
    private static final long serialVersionUID = 1;

    //Counteren sørger for threadsafe utdeling av skadenummer til nye skademeldinger med metoden getAndIncrement();
    private transient static final AtomicInteger skadenummerCounter = new AtomicInteger(10000);

    private transient ObjectProperty<LocalDate> skademeldingsDato;
    private transient LongProperty skadenummer;
    private transient StringProperty typeSkade;
    private transient StringProperty beskrivelse;
    private transient StringProperty vitner;
    private transient StringProperty takseringAvSkaden;
    private transient StringProperty utbetaltErstatningsbeløp;

    public Skademelding(LocalDate skademeldingsDato, String typeSkade, String beskrivelse, String vitner,
                        String takseringAvSkaden, String utbetaltErstatningsbeløp){
        this.skademeldingsDato = new SimpleObjectProperty<>(this, "skademeldingsDato", skademeldingsDato);
        this.skadenummer = new SimpleLongProperty(this, "skadenummer", skadenummerCounter.getAndIncrement());
        this.typeSkade = new SimpleStringProperty(this, "typeSkade", typeSkade);
        this.beskrivelse = new SimpleStringProperty(this, "beskrivelse", beskrivelse);
        this.vitner = new SimpleStringProperty(this, "vitner", vitner);
        this.takseringAvSkaden = new SimpleStringProperty(this, "takseringAvSkaden", takseringAvSkaden);
        this.utbetaltErstatningsbeløp = new SimpleStringProperty(this, "utbetaltErstatningsbeløp", utbetaltErstatningsbeløp);

    }

    public LocalDate getSkademeldingsDato(){
        return skademeldingsDato.get();
    }

    public ObjectProperty<LocalDate> skademeldingsDatoProperty(){
        return skademeldingsDato;
    }

    public void setSkademeldingsDato(LocalDate skademeldingsDato) {
        this.skademeldingsDato.set(skademeldingsDato);
    }

    public long getSkadenummer() {
        return skadenummer.get();
    }

    public LongProperty skadenummerProperty() {
        return skadenummer;
    }

    public void setSkadenummer(long skadenummer) {
        this.skadenummer.set(skadenummer);
    }

    public String getTypeSkade() {
        return typeSkade.get();
    }

    public StringProperty typeSkadeProperty() {
        return typeSkade;
    }

    public void setTypeSkade(String typeSkade) {
        this.typeSkade.set(typeSkade);
    }

    public String getBeskrivelse() {
        return beskrivelse.get();
    }

    public StringProperty beskrivelseProperty() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse.set(beskrivelse);
    }

    public String getVitner() {
        return vitner.get();
    }

    public StringProperty vitnerProperty() {
        return vitner;
    }

    public void setVitner(String vitner) {
        this.vitner.set(vitner);
    }

    public void setTakseringAvSkaden(String takseringAvSkaden) {
        this.takseringAvSkaden.set(takseringAvSkaden);
    }

    public StringProperty takseringAvSkadenProperty() {
        return takseringAvSkaden;
    }

    public String getTakseringAvSkaden() {
        return takseringAvSkaden.get();
    }

    public String getUtbetaltErstatningsbeløp() {
        return utbetaltErstatningsbeløp.get();
    }

    public StringProperty utbetaltErstatningsbeløpProperty() {
        return utbetaltErstatningsbeløp;
    }

    public void setUtbetaltErstatningsbeløp(String utbetaltErstatningsbeløp) {
        this.utbetaltErstatningsbeløp.set(utbetaltErstatningsbeløp);
    }

    //Egendefinert serialisering
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getSkademeldingsDato());
        objectOutputStream.writeObject(getSkadenummer());
        objectOutputStream.writeObject(getTypeSkade());
        objectOutputStream.writeObject(getBeskrivelse());
        objectOutputStream.writeObject(getVitner());
        objectOutputStream.writeObject(getTakseringAvSkaden());
        objectOutputStream.writeObject(getUtbetaltErstatningsbeløp());
    }

    //Egendefinert serialisering
    private void readObject(ObjectInputStream objectInputStream) throws IOException , ClassNotFoundException{
        objectInputStream.defaultReadObject();
        this.skademeldingsDato = new SimpleObjectProperty<>((LocalDate) objectInputStream.readObject());
        this.skadenummer = new SimpleLongProperty((Long) objectInputStream.readObject());
        this.typeSkade = new SimpleStringProperty((String) objectInputStream.readObject());
        this.beskrivelse = new SimpleStringProperty((String) objectInputStream.readObject());
        this.vitner = new SimpleStringProperty((String) objectInputStream.readObject());
        this.takseringAvSkaden = new SimpleStringProperty((String) objectInputStream.readObject());
        this.utbetaltErstatningsbeløp = new SimpleStringProperty((String) objectInputStream.readObject());
    }

    @Override
    public boolean isValid() {
        return SkademeldingValidator.skademeldingIsValid(this);
    }
}
