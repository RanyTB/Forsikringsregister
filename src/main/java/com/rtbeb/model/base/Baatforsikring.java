package com.rtbeb.model.base;

import javafx.beans.property.*;

public class Baatforsikring extends Forsikring {

    private ObjectProperty<Eier> eier;
    private StringProperty registreringsnummer;
    private StringProperty merke;
    private StringProperty modell;
    private StringProperty lengde;
    private StringProperty aarsmodell;
    private StringProperty motorInfo;

    public Baatforsikring(Integer aarligForsikringspremie,
                          Integer forsikringsbelop, String forsikringsbetingelser,
                          Eier eier, String registreringsnummer,
                          String merke, String modell,
                          String lengde, String aarsmodell,
                          String motorInfo) {

        super("Båtforsikring", aarligForsikringspremie, forsikringsbelop, forsikringsbetingelser);

        this.eier = new SimpleObjectProperty<>(eier);
        this.registreringsnummer = new SimpleStringProperty(registreringsnummer);
        this.merke = new SimpleStringProperty(merke);
        this.modell = new SimpleStringProperty(modell);
        this.lengde = new SimpleStringProperty(lengde);
        this.aarsmodell = new SimpleStringProperty(aarsmodell);
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

    public String getAarsmodell() {
        return aarsmodell.get();
    }

    public StringProperty aarsmodellProperty() {
        return aarsmodell;
    }

    public void setAarsmodell(String aarsmodell) {
        this.aarsmodell.set(aarsmodell);
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
