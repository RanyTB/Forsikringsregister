package com.rtbeb.model.base;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Eier {

    StringProperty fornavn;
    StringProperty etternavn;
    StringProperty fodselsdato;

    public Eier(String fornavn, String etternavn, String fodselsdato) {
        this.fornavn = new SimpleStringProperty(fornavn);
        this.etternavn = new SimpleStringProperty(etternavn);
        this.fodselsdato = new SimpleStringProperty(fodselsdato);
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

    public String getFodselsdato() {
        return fodselsdato.get();
    }

    public StringProperty fodselsdatoProperty() {
        return fodselsdato;
    }

    public void setFodselsdato(String fodselsdato) {
        this.fodselsdato.set(fodselsdato);
    }
}
