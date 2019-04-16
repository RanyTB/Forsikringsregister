package com.rtbeb.model.base;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Båt {

    private ObjectProperty<Eier> eier;
    private StringProperty registreringsnummer;
    private StringProperty merke;
    private StringProperty modell;
    private StringProperty lengde;
    private StringProperty årsmodell;
    private StringProperty motorInfo;

    public Båt(Eier eier,
               String registreringsnummer,
               String merke, String modell,
               String lengde, String årsmodell,
               String motorInfo){
        this.eier = new SimpleObjectProperty<>(eier);
        this.registreringsnummer = new SimpleStringProperty(registreringsnummer);
        this.merke = new SimpleStringProperty(merke);
        this.modell = new SimpleStringProperty(modell);
        this.lengde = new SimpleStringProperty(lengde);
        this.årsmodell = new SimpleStringProperty(årsmodell);
        this.motorInfo = new SimpleStringProperty(motorInfo);
    }

    public Eier getEier() {
        return eier.get();
    }

    public ObjectProperty<Eier> eierProperty() {
        return eier;
    }

    public void setEier(Eier eier) {
        this.eier.set(eier);
    }

    public String getRegistreringsnummer() {
        return registreringsnummer.get();
    }

    public StringProperty registreringsnummerProperty() {
        return registreringsnummer;
    }

    public void setRegistreringsnummer(String registreringsnummer) {
        this.registreringsnummer.set(registreringsnummer);
    }

    public String getMerke() {
        return merke.get();
    }

    public StringProperty merkeProperty() {
        return merke;
    }

    public void setMerke(String merke) {
        this.merke.set(merke);
    }

    public String getModell() {
        return modell.get();
    }

    public StringProperty modellProperty() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell.set(modell);
    }

    public String getLengde() {
        return lengde.get();
    }

    public StringProperty lengdeProperty() {
        return lengde;
    }

    public void setLengde(String lengde) {
        this.lengde.set(lengde);
    }

    public String getÅrsmodell() {
        return årsmodell.get();
    }

    public StringProperty årsmodellProperty() {
        return årsmodell;
    }

    public void setÅrsmodell(String årsmodell) {
        this.årsmodell.set(årsmodell);
    }

    public String getMotorInfo() {
        return motorInfo.get();
    }

    public StringProperty motorInfoProperty() {
        return motorInfo;
    }

    public void setMotorInfo(String motorInfo) {
        this.motorInfo.set(motorInfo);
    }
}
