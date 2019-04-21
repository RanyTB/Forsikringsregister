package com.rtbeb.model.base.forsikring;

import com.rtbeb.model.base.Eier;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Båt implements Serializable {
    private static final long serialVersionUID = 1;

    private transient ObjectProperty<Eier> eier;
    private transient StringProperty registreringsnummer;
    private transient StringProperty merke;
    private transient StringProperty modell;
    private transient StringProperty lengde;
    private transient StringProperty årsmodell;
    private transient StringProperty motorinfo;

    public Båt(Eier eier,
               String registreringsnummer,
               String merke, String modell,
               String lengde, String årsmodell,
               String motorinfo){
        this.eier = new SimpleObjectProperty<>(eier);
        this.registreringsnummer = new SimpleStringProperty(registreringsnummer);
        this.merke = new SimpleStringProperty(merke);
        this.modell = new SimpleStringProperty(modell);
        this.lengde = new SimpleStringProperty(lengde);
        this.årsmodell = new SimpleStringProperty(årsmodell);
        this.motorinfo = new SimpleStringProperty(motorinfo);
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

    public String getMotorinfo() {
        return motorinfo.get();
    }

    public StringProperty motorinfoProperty() {
        return motorinfo;
    }

    public void setMotorinfo(String motorinfo) {
        this.motorinfo.set(motorinfo);
    }

    //Egendefinert serialisering
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEier());
        objectOutputStream.writeObject(getRegistreringsnummer());
        objectOutputStream.writeObject(getMerke());
        objectOutputStream.writeObject(getModell());
        objectOutputStream.writeObject(getLengde());
        objectOutputStream.writeObject(getÅrsmodell());
        objectOutputStream.writeObject(getMotorinfo());
    }

    //Egendefinert serialisering
    private void readObject(ObjectInputStream objectInputStream) throws IOException , ClassNotFoundException{
        objectInputStream.defaultReadObject();
        this.eier = new SimpleObjectProperty<>((Eier) objectInputStream.readObject());
        this.registreringsnummer = new SimpleStringProperty((String) objectInputStream.readObject());
        this.merke = new SimpleStringProperty((String) objectInputStream.readObject());
        this.modell = new SimpleStringProperty((String) objectInputStream.readObject());
        this.lengde = new SimpleStringProperty ((String) objectInputStream.readObject());
        this.årsmodell = new SimpleStringProperty((String) objectInputStream.readObject());
        this.motorinfo = new SimpleStringProperty((String) objectInputStream.readObject());
    }
}
