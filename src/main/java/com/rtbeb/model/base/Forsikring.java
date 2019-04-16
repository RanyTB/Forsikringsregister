package com.rtbeb.model.base;

import javafx.beans.property.*;

import java.time.LocalDate;

public abstract class Forsikring {

    public StringProperty forsikringstype;
    private IntegerProperty forsikringspremie;
    private ObjectProperty<LocalDate> datoOpprettet;
    private IntegerProperty forsikringsbelop;
    private StringProperty forsikringsbetingelser;

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
}
