package com.rtbeb.model.base;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Eier {

    StringProperty fornavn;
    StringProperty etternavn;
    StringProperty fødselsdato;

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
}
