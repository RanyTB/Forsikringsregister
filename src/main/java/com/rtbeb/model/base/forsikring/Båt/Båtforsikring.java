package com.rtbeb.model.base.forsikring.Båt;

import com.rtbeb.model.base.forsikring.Forsikring;
import com.rtbeb.model.base.forsikring.Validerbar;
import com.rtbeb.model.validation.BåtforsikringValidator;
import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Klasse for båtforsikring.
 * @author Rany Tarek Bouorm - s236210
 */
public class Båtforsikring extends Forsikring implements Serializable, Validerbar {
    private static final long serialVersionUID = 1;


    private transient ObjectProperty<Båt> båt;

    /**
     * Standard konstruktør. Setter dato opprettet til dagens dato.
     */
    public Båtforsikring(Integer årligForsikringspremie, Integer forsikringsbeløp, String forsikringsbetingelser, Båt båt) {

        super("Båtforsikring", årligForsikringspremie, forsikringsbeløp, forsikringsbetingelser);
        this.båt = new SimpleObjectProperty<>(båt);
    }

    /**
     * Konstruktør med muligheten for å sette dato opprettet. Brukes til innlesing fra fil.
     */
    public Båtforsikring(Integer årligForsikringspremie, LocalDate datoOpprettet, Integer forsikringsbeløp, String forsikringsbetingelser, Båt båt) {

        super("Båtforsikring", årligForsikringspremie, datoOpprettet, forsikringsbeløp, forsikringsbetingelser);
        this.båt = new SimpleObjectProperty<>(båt);
    }

    public Båt getBåt() {
        return båt.get();
    }

    public ObjectProperty<Båt> båtProperty() {
        return båt;
    }

    public void setBåt(Båt båt) {
        this.båt.set(båt);
    }

    //Egendefinert serialisering
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getBåt());
    }

    //Egendefinert serialisering
    private void readObject(ObjectInputStream objectInputStream) throws IOException , ClassNotFoundException{
        objectInputStream.defaultReadObject();
        this.båt = new SimpleObjectProperty<>((Båt) objectInputStream.readObject());
    }

    /**
     * Validerer dette objektet etter satte valideringsregler.
     * @return Returnerer true hvis gyldig.
     */
    @Override
    public boolean isValid(){
        return BåtforsikringValidator.BåtforsikringIsValid(this);
    }
}
