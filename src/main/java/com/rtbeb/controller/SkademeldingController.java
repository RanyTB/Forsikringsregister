package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SkademeldingController implements Initializable {

    Kunde valgtKunde;

    SkademeldingController(Kunde valgtKunde){
        this.valgtKunde = valgtKunde;
    }

    @FXML
    private Label lblForsikringsnummer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindLabelsWithCustomerInfo();
    }

    private void bindLabelsWithCustomerInfo(){
        //Binder TextFields til kundens datafelt.
        lblForsikringsnummer.textProperty().bind(valgtKunde.forsikringsnummerProperty().asString());
    }
}
