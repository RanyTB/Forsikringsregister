package com.rtbeb.model.base.forsikring;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Båtforsikring extends Forsikring implements Serializable {
    private static final long serialVersionUID = 1;


    private transient ObjectProperty<Båt> båt;


    public Båtforsikring(Integer årligForsikringspremie, Integer forsikringsbeløp, String forsikringsbetingelser, Båt båt) {

        super("Båtforsikring", årligForsikringspremie, forsikringsbeløp, forsikringsbetingelser);
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
}
