package com.rtbeb.model.base;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Eier implements Serializable {
    private static final long serialVersionUID = 1;

    private transient StringProperty fornavn;
    private transient StringProperty etternavn;
    private transient StringProperty fødselsdato;

    public Eier(String fornavn, String etternavn, String fødselsdato) {
        this.fornavn = new SimpleStringProperty(fornavn);
        this.etternavn = new SimpleStringProperty(etternavn);
        this.fødselsdato = new SimpleStringProperty(fødselsdato);
    }

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

    public String getFødselsdato() {
        return fødselsdato.get();
    }

    public StringProperty fødselsdatoProperty() {
        return fødselsdato;
    }

    public void setFødselsdato(String fødselsdato) {
        this.fødselsdato.set(fødselsdato);
    }

    //Egendefinert serialisering
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getFornavn());
        objectOutputStream.writeObject(getEtternavn());
        objectOutputStream.writeObject(getFødselsdato());
    }

    //Egendefinert serialisering
    private void readObject(ObjectInputStream objectInputStream) throws IOException , ClassNotFoundException{
        objectInputStream.defaultReadObject();
        this.fornavn = new SimpleStringProperty((String) objectInputStream.readObject());
        this.etternavn = new SimpleStringProperty((String) objectInputStream.readObject());
        this.fødselsdato = new SimpleStringProperty((String) objectInputStream.readObject());
    }
}
