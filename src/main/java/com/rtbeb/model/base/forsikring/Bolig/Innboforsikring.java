package com.rtbeb.model.base.forsikring.Bolig;

import com.rtbeb.model.base.forsikring.Forsikring;
import com.rtbeb.model.base.forsikring.Validerbar;
import com.rtbeb.model.validation.InnboForsikringValidator;
import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Innboforsikring extends Forsikring implements Serializable, Validerbar {

    private Innboforsikringstype innboforsikringstype;
    private transient ObjectProperty<Bolig> bolig;
    private transient IntegerProperty forsikringssbeløpBygning;
    private transient IntegerProperty forsikringsbeløpInnbo;

    public Innboforsikring(Innboforsikringstype forsikringtype, int forsikringspremie, int forsikringsbeløp, String betingelser,
                           Bolig bolig, Integer forsikringssbeløpBygning, Integer forsikringsbeløpInnbo) {

        super(forsikringtype.toString(), forsikringspremie, forsikringsbeløp, betingelser);
        this.innboforsikringstype = forsikringtype;
        this.bolig = new SimpleObjectProperty<>(bolig);
        this.forsikringssbeløpBygning = new SimpleIntegerProperty(forsikringssbeløpBygning);
        this.forsikringsbeløpInnbo = new SimpleIntegerProperty(forsikringsbeløpInnbo);
    }

    /**
     * enum Innboforsikringstype brukes i konstruktøren for å skille mellom vanlig innbo og fritidsboligforsikring.
     * Dette slik at samme Controller kan brukes til de forskjellige forsikringstypene.
     */
    public enum Innboforsikringstype {
        HELÅRSBOLIG("Hus og Innbo"),
        FRITIDSBOLIG("Fritidsbolig");

        private String forsikringstype;

        private Innboforsikringstype(String forsikringsstype){
            this.forsikringstype = forsikringsstype;
        }

        @Override
        public String toString(){
            return forsikringstype;
        }

    }

    public Innboforsikringstype getInnboforsikringstype() {
        return innboforsikringstype;
    }

    public void setInnboforsikringstype(Innboforsikringstype innboforsikringstype) {
        this.innboforsikringstype = innboforsikringstype;
    }

    public Bolig getBolig() {
        return bolig.get();
    }

    public ObjectProperty<Bolig> boligProperty() {
        return bolig;
    }

    public void setBolig(Bolig bolig) {
        this.bolig.set(bolig);
    }

    public Integer getForsikringssbeløpBygning() {
        return forsikringssbeløpBygning.get();
    }

    public IntegerProperty forsikringsbeløpBygningProperty() {
        return forsikringssbeløpBygning;
    }

    public void setForsikringsbeløpBygning(Integer forsikringssbeløpBygning) {
        this.forsikringssbeløpBygning.set(forsikringssbeløpBygning);
    }

    public Integer getForsikringsbeløpInnbo() {
        return forsikringsbeløpInnbo.get();
    }

    public IntegerProperty forsikringsbeløpInnboProperty() {
        return forsikringsbeløpInnbo;
    }

    public void setForsikringsbeløpInnbo(Integer forsikringsbeløpInnbo) {
        this.forsikringsbeløpInnbo.set(forsikringsbeløpInnbo);
    }

    //Egendefinert serialisering
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getBolig());
        objectOutputStream.writeObject(getForsikringssbeløpBygning());
        objectOutputStream.writeObject(getForsikringsbeløpInnbo());
    }

    //Egendefinert serialisering
    private void readObject(ObjectInputStream objectInputStream) throws IOException , ClassNotFoundException{
        objectInputStream.defaultReadObject();
        this.bolig = new SimpleObjectProperty<>( (Bolig) objectInputStream.readObject());
        this.forsikringssbeløpBygning = new SimpleIntegerProperty( (Integer) objectInputStream.readObject());
        this.forsikringsbeløpInnbo = new SimpleIntegerProperty( (Integer) objectInputStream.readObject());

    }

    @Override
    public boolean isValid() {
        return InnboForsikringValidator.innboForsikringIsValid(this);
    }

}
