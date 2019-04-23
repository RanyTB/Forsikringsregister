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

    private transient ObjectProperty<Bolig> bolig;
    private transient IntegerProperty forsikringssbeløpBygning;
    private transient IntegerProperty forsikringsbeløpInnbo;

    public Innboforsikring(Innbotype forsikringtype,int forsikringspremie, int forsikringsbeløp, String betingelser,
                           Bolig bolig, Integer forsikringssbeløpBygning, Integer forsikringsbeløpInnbo) {

        super(forsikringtype.toString(), forsikringspremie, forsikringsbeløp, betingelser);
        this.bolig = new SimpleObjectProperty<>(bolig);
        this.forsikringssbeløpBygning = new SimpleIntegerProperty(forsikringssbeløpBygning);
        this.forsikringsbeløpInnbo = new SimpleIntegerProperty(forsikringsbeløpInnbo);
    }

    /**
     * enum Innbotype brukes kun i konstruktøren for å skille mellom vanlig innbo og fritidsboligforsikring.
     * Dette slik at samme Controller kan brukes til de forskjellige forsikringstypene.
     */
    public enum Innbotype{
        HELÅRSBOLIG("Hus og Innbo"),
        FRITIDSBOLIG("Fritidsbolig");

        private String forsikringstype;

        private Innbotype(String forsikringsstype){
            this.forsikringstype = forsikringsstype;
        }

        @Override
        public String toString(){
            return forsikringstype;
        }

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

    public int getForsikringssbeløpBygning() {
        return forsikringssbeløpBygning.get();
    }

    public IntegerProperty forsikringssbeløpBygningProperty() {
        return forsikringssbeløpBygning;
    }

    public void setForsikringssbeløpBygning(int forsikringssbeløpBygning) {
        this.forsikringssbeløpBygning.set(forsikringssbeløpBygning);
    }

    public int getForsikringsbeløpInnbo() {
        return forsikringsbeløpInnbo.get();
    }

    public IntegerProperty forsikringsbeløpInnboProperty() {
        return forsikringsbeløpInnbo;
    }

    public void setForsikringsbeløpInnbo(int forsikringsbeløpInnbo) {
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
