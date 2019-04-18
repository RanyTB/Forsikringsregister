package com.rtbeb.model.base;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Skademelding implements Serializable {
    private static final long serialVersionUID = 1;

    private transient ObjectProperty<Date> skademeldingsDato;
    private transient StringProperty typeSkade;
    private transient StringProperty beskrivelse;
    private transient StringProperty vitner;
    private transient IntegerProperty takseringAvSkaden;
    private transient IntegerProperty utbetaltErstatningsbeløp;

    public Skademelding(LocalDate skademeldingsDato, String typeSkade, String beskrivelse, String vitner,
                        String takseringAvSkaden, Integer utbetaltErstatningsbeløp){
        this.skademeldingsDato = new SimpleObjectProperty<>(this, "skademeldingsDato", skademeldingsDato);
        this.typeSkade = new SimpleStringProperty(this, "typeSkade", typeSkade);
        this.beskrivelse = new SimpleStringProperty(this, "beskrivelse", beskrivelse);
        this.vitner = new SimpleStringProperty(this, "vitner", vitner);
        this.takseringAvSkaden = new SimpleIntegerProperty(this, "takseringAvSkaden", takseringAvSkaden);
        this.utbetaltErstatningsbeløp = new SimpleIntegerProperty(this, "utbetaltErstatningsbeløp", utbetaltErstatningsbeløp);

    }

    public Date getSkademeldingsDato(){
        return skademeldingsDato.get();
    }

    public ObjectProperty<Date> skademeldingsDatoProperty(){
        return skademeldingsDato;
    }

    public void setSkademeldingsDato(Date skademeldingsDato) {
        this.skademeldingsDato.set(skademeldingsDato);
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

    public SimpleIntegerProperty takseringAvSkadenProperty() {
        return takseringAvSkaden;
    }

    public String getTakseringAvSkaden() {
        return takseringAvSkaden.get();
    }

    public int getUtbetaltErstatningsbeløp() {
        return utbetaltErstatningsbeløp.get();
    }

    public IntegerProperty utbetaltErstatningsbeløpProperty() {
        return utbetaltErstatningsbeløp;
    }

    public void setUtbetaltErstatningsbeløp(int utbetaltErstatningsbeløp) {
        this.utbetaltErstatningsbeløp.set(utbetaltErstatningsbeløp);
    }

    //Egendefinert serialisering
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getSkademeldingsDato());
        objectOutputStream.writeObject(getTypeSkade());
        objectOutputStream.writeObject(getBeskrivelse());
        objectOutputStream.writeObject(getVitner());
        objectOutputStream.writeObject(getTakseringAvSkaden());
        objectOutputStream.writeObject(getUtbetaltErstatningsbeløp());
    }

    //Egendefinert serialisering
    private void readObject(ObjectInputStream objectInputStream) throws IOException , ClassNotFoundException{
        objectInputStream.defaultReadObject();
        this.skademeldingsDato = new SimpleObjectProperty<>((Date) objectInputStream.readObject());
        this.typeSkade = new SimpleStringProperty((String) objectInputStream.readObject());
        this.beskrivelse = new SimpleStringProperty((String) objectInputStream.readObject());
        this.vitner = new SimpleStringProperty((String) objectInputStream.readObject());
        this.takseringAvSkaden = new IntegerProperty((Integer) objectInputStream.readObject());
        this.utbetaltErstatningsbeløp = new IntegerProperty((Integer) objectInputStream.readObject());
    }
}
