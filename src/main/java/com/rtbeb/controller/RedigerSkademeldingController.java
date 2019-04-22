package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Skademelding;
import com.rtbeb.model.validation.SkademeldingValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RedigerSkademeldingController implements Initializable {

    Kunde valgtKunde;
    Skademelding valgtSkademelding;
    SkademeldingValidator skademeldingValidator;


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
    private void dateChanged(ActionEvent event){
        LocalDate date = datePicker.getValue();
        if(skademeldingValidator.dateIsValid(date)){
            datePicker.setStyle("-fx-border-color: green");
        } else {
            datePicker.setStyle("-fx-border-color: red");
        }

    }


    @FXML
    private void typeSkadeChanged(InputEvent event){
        String typeSkade = txtTypeSkade.getText();
        if(skademeldingValidator.textIsValid(typeSkade, 20)){
            txtTypeSkade.setStyle("-fx-border-color: green");
        } else {
            txtTypeSkade.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void beskrivelseChanged(InputEvent event){
        String beskrivelse = txtBeskrivelse.getText();
        if(skademeldingValidator.textOgTallIsValid(beskrivelse, 200)){
            txtBeskrivelse.setStyle("-fx-border-color: green");
        } else {
            txtBeskrivelse.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void vitneChanged(InputEvent event){
        String vitner = txtVitner.getText();
        if(skademeldingValidator.textOgTallIsValid(vitner, 100)){
            txtVitner.setStyle("-fx-border-color: green");
        } else {
            txtVitner.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void takseringChanged(InputEvent event){
        String takseringAvSkaden = txtTakseringAvSkaden.getText();
        if(skademeldingValidator.tallIsValid(takseringAvSkaden, 8)){
            txtTakseringAvSkaden.setStyle("-fx-border-color: green");
        } else {
            txtTakseringAvSkaden.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void utbetaltChanged(InputEvent event){
        String utbetaltErstatningsbeløp = txtUtbetaltErstatningsbeløp.getText();
        if(skademeldingValidator.tallIsValid(utbetaltErstatningsbeløp, 8)){
            txtUtbetaltErstatningsbeløp.setStyle("-fx-border-color: green");
        } else {
            txtUtbetaltErstatningsbeløp.setStyle("-fx-border-color: red");
        }
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
