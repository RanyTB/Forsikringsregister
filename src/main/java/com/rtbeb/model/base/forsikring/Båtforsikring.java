package com.rtbeb.model.base.forsikring;

import javafx.beans.property.*;

public class Båtforsikring extends Forsikring {

    private ObjectProperty<Båt> båt;

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
}
