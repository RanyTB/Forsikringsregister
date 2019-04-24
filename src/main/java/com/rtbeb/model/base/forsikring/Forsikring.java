package com.rtbeb.model.base.forsikring;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public abstract class Forsikring implements Serializable {
    private static final long serialVersionUID = 1;


    private transient StringProperty forsikringstype;
    private transient IntegerProperty forsikringspremie;
    private transient ObjectProperty<LocalDate> datoOpprettet;
    private transient IntegerProperty forsikringsbelop;
    private transient StringProperty forsikringsbetingelser;



    public Forsikring(String forsikringstype, Integer forsikringspremie, Integer forsikringsbelop, String forsikringsbetingelser) {
        this.forsikringstype = new SimpleStringProperty(forsikringstype);
        this.forsikringspremie = new SimpleIntegerProperty(forsikringspremie);
        this.datoOpprettet = new SimpleObjectProperty<>(LocalDate.now());
        this.forsikringsbelop = new SimpleIntegerProperty(forsikringsbelop);
        this.forsikringsbetingelser = new SimpleStringProperty(forsikringsbetingelser);
    }

    public String getForsikringstype() {
        return forsikringstype.get();
    }

    public StringProperty forsikringstypeProperty() {
        return forsikringstype;
    }

    public void setForsikringstype(String forsikringstype) {
        this.forsikringstype.set(forsikringstype);
    }

    public Integer getForsikringspremie() {
        return forsikringspremie.get();
    }

    public IntegerProperty forsikringspremieProperty() {
        return forsikringspremie;
    }

    public void setForsikringspremie(Integer forsikringspremie) {
        this.forsikringspremie.set(forsikringspremie);
    }

    public LocalDate getDatoOpprettet() {
        return datoOpprettet.get();
    }

    public ObjectProperty<LocalDate> datoOpprettetProperty() {
        return datoOpprettet;
    }

    public void setDatoOpprettet(LocalDate datoOpprettet) {
        this.datoOpprettet.set(datoOpprettet);
    }

    public int getForsikringsbelop() {
        return forsikringsbelop.get();
    }

    public IntegerProperty forsikringsbelopProperty() {
        return forsikringsbelop;
    }

    public void setForsikringsbelop(int forsikringsbelop) {
        this.forsikringsbelop.set(forsikringsbelop);
    }

    public String getForsikringsbetingelser() {
        return forsikringsbetingelser.get();
    }

    public StringProperty forsikringsbetingelserProperty() {
        return forsikringsbetingelser;
    }

    public void setForsikringsbetingelser(String forsikringsbetingelser) {
        this.forsikringsbetingelser.set(forsikringsbetingelser);
    }

    //Egendefinert serialisering
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getForsikringstype());
        objectOutputStream.writeObject(getForsikringspremie());
        objectOutputStream.writeObject(getDatoOpprettet());
        objectOutputStream.writeObject(getForsikringsbelop());
        objectOutputStream.writeObject(getForsikringsbetingelser());
    }

    //Egendefinert serialisering
    private void readObject(ObjectInputStream objectInputStream) throws IOException , ClassNotFoundException{
        objectInputStream.defaultReadObject();
        this.forsikringstype = new SimpleStringProperty((String) objectInputStream.readObject());
        this.forsikringspremie = new SimpleIntegerProperty((Integer) objectInputStream.readObject());
        this.datoOpprettet = new SimpleObjectProperty<>((LocalDate) objectInputStream.readObject());
        this.forsikringsbelop = new SimpleIntegerProperty((Integer) objectInputStream.readObject());
        this.forsikringsbetingelser = new SimpleStringProperty((String) objectInputStream.readObject());

    }

}
