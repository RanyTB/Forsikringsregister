package com.rtbeb.model.base.forsikring.Reise;

import com.rtbeb.model.base.forsikring.Forsikring;
import com.rtbeb.model.base.forsikring.Validerbar;
import com.rtbeb.model.validation.ReiseforsikringValidator;
import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Reiseforsikringsklasse.
 * @author Rany Tarek Bouorm - s236210
 */
public class Reiseforsikring extends Forsikring implements Serializable, Validerbar {

    private transient StringProperty forsikringsområde;
    private transient IntegerProperty forsikringssum;

    public Reiseforsikring(Integer årligForsikringspremie, Integer forsikringsbeløp,
                           String forsikringsbetingelser, String forsikringsområde, int forsikringssum) {

        super("Reiseforsikring",årligForsikringspremie, forsikringsbeløp,forsikringsbetingelser);
        this.forsikringsområde = new SimpleStringProperty(forsikringsområde);
        this.forsikringssum = new SimpleIntegerProperty(forsikringssum);
    }

    public String getForsikringsområde() {
        return forsikringsområde.get();
    }

    public StringProperty forsikringsområdeProperty() {
        return forsikringsområde;
    }

    public void setForsikringsområde(String forsikringsområde) {
        this.forsikringsområde.set(forsikringsområde);
    }

    public Integer getForsikringssum() {
        return forsikringssum.get();
    }

    public IntegerProperty forsikringssumProperty() {
        return forsikringssum;
    }

    public void setForsikringssum(Integer forsikringssum) {
        this.forsikringssum.set(forsikringssum);
    }

    //Egendefinert serialisering.
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getForsikringsområde());
        objectOutputStream.writeObject(getForsikringssum());
    }

    //Egendefinert serialisering
    private void readObject(ObjectInputStream objectInputStream) throws IOException , ClassNotFoundException{
        objectInputStream.defaultReadObject();
        this.forsikringsområde = new SimpleStringProperty((String) objectInputStream.readObject());
        this.forsikringssum = new SimpleIntegerProperty((Integer) objectInputStream.readObject());
    }

    @Override
    public boolean isValid() {
        return ReiseforsikringValidator.reiseforsikringIsValid(this);
    }
}
