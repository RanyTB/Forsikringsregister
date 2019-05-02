package com.rtbeb.model.base.forsikring.Bolig;

import com.rtbeb.model.base.forsikring.Forsikring;
import com.rtbeb.model.base.forsikring.Validerbar;
import com.rtbeb.model.validation.InnboForsikringValidator;
import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Klasse for innboforsikring.
 * @author Rany Tarek Bouorm - s236210
 */
public class Innboforsikring extends Forsikring implements Serializable, Validerbar {

    private Brukstype brukstype;
    private transient ObjectProperty<Bolig> bolig;
    private transient IntegerProperty forsikringssbeløpBygning;
    private transient IntegerProperty forsikringsbeløpInnbo;

    public Innboforsikring(Brukstype brukstype, int forsikringspremie, int forsikringsbeløp, String betingelser,
                           Bolig bolig, Integer forsikringssbeløpBygning, Integer forsikringsbeløpInnbo) {

        super(brukstype.toString(), forsikringspremie, forsikringsbeløp, betingelser);
        this.brukstype = brukstype;
        this.bolig = new SimpleObjectProperty<>(bolig);
        this.forsikringssbeløpBygning = new SimpleIntegerProperty(forsikringssbeløpBygning);
        this.forsikringsbeløpInnbo = new SimpleIntegerProperty(forsikringsbeløpInnbo);
    }

    /**
     * enum Brukstype brukes i konstruktøren for å skille mellom vanlig innbo og fritidsboligforsikring.
     * Dette slik at samme Controller kan brukes til de forskjellige forsikringstypene.
     */
    public enum Brukstype {
        HELÅRSBOLIG("Hus og Innbo"),
        FRITIDSBOLIG("Fritidsbolig");

        private String forsikringstype;

        Brukstype(String forsikringsstype){
            this.forsikringstype = forsikringsstype;
        }

        //Denne metoden benyttes ved innlesning fra csv fil der formatet er på "Hus og Innbo"
        //og ikke HELÅRSBOLIG. Den tarimot "Hus og Innbo" og finner HELÅRSBOLIG.
        public static Brukstype getBrukstype(String brukstypeSomTekst){
          for (Brukstype brukstype : Brukstype.values()) {
            if (brukstype.forsikringstype.equalsIgnoreCase(brukstypeSomTekst)) {
              return brukstype;
            }
            /*if (brukstype.forsikringstype.equals(brukstypeSomTekst)) {
                  return brukstype;
            }*/
          }
          return null;
        }

        @Override
        public String toString(){
            return forsikringstype;
        }

    }

    public Brukstype getBrukstype() {
        return brukstype;
    }

    public void setBrukstype(Brukstype brukstype) {
        this.brukstype = brukstype;
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
