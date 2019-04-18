package com.rtbeb.model.base;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Skademelding {
    private transient ObjectProperty<LocalDate> skademeldingsdato;
    private transient StringProperty typeSkade;
    private transient StringProperty beskrivelse;
    private transient StringProperty vitner;
    private transient StringProperty takseringAvSkaden;
    private transient IntegerProperty utbetaltErstatningsbeløp;
    
}
