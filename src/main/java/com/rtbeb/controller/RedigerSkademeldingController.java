package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Skademelding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RedigerSkademeldingController implements Initializable {

    Kunde valgtKunde;
    Skademelding valgtSkademelding;

    RedigerSkademeldingController(Kunde valgtKunde, Skademelding valgtSkademelding){
        this.valgtKunde = valgtKunde;
        this.valgtSkademelding = valgtSkademelding;
    }

    @FXML
    private Label lblForsikringsnummer;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField txtTypeSkade;
    @FXML
    private TextArea txtBeskrivelse;
    @FXML
    private TextArea txtVitner;
    @FXML
    private TextField txtTakseringAvSkaden;
    @FXML
    private TextField txtUtbetaltErstatningsbeløp;
    @FXML
    private Button btnRedigerSkademelding;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblForsikringsnummer.textProperty().bind(valgtKunde.forsikringsnummerProperty().asString());
        datePicker.setValue(valgtSkademelding.getSkademeldingsDato());
        txtTypeSkade.setText(valgtSkademelding.getTypeSkade());
        txtBeskrivelse.setText(valgtSkademelding.getBeskrivelse());
        txtVitner.setText(valgtSkademelding.getVitner());
        txtTakseringAvSkaden.setText(Integer.toString(valgtSkademelding.getTakseringAvSkaden()));
        txtUtbetaltErstatningsbeløp.setText(Integer.toString(valgtSkademelding.getUtbetaltErstatningsbeløp()));

    }

    @FXML
    private void redigerSkademeldingClicked(ActionEvent event){
        //Gjør om til LocalDate objekt
        LocalDate date = datePicker.getValue();
        String typeSkade = txtTypeSkade.getText();
        String beskrivelse = txtBeskrivelse.getText();
        String vitner = txtVitner.getText();
        int takseringAvSkaden = Integer.parseInt(txtTakseringAvSkaden.getText());
        int utbetaltErstatningsbeløp = Integer.parseInt(txtUtbetaltErstatningsbeløp.getText());

        valgtSkademelding.setSkademeldingsDato(date);
        valgtSkademelding.setTypeSkade(typeSkade);
        valgtSkademelding.setBeskrivelse(beskrivelse);
        valgtSkademelding.setVitner(vitner);
        valgtSkademelding.setTakseringAvSkaden(takseringAvSkaden);
        valgtSkademelding.setUtbetaltErstatningsbeløp(utbetaltErstatningsbeløp);

        Stage thisStage = (Stage) btnRedigerSkademelding.getScene().getWindow();
        thisStage.close();
    }
}
