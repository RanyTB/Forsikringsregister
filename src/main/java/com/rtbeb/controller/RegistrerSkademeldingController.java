package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Skademelding;
import com.rtbeb.model.validation.SkademeldingValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistrerSkademeldingController implements Initializable {

    Kunde valgtKunde;
    SkademeldingValidator skademeldingValidator;

    RegistrerSkademeldingController(Kunde valgtKunde){
        this.valgtKunde = valgtKunde;
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
    private Button btnRegistrerSkademelding;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bindLabelsWithCustomerInfo();

    }

    @FXML
    private void bindLabelsWithCustomerInfo(){
        //Binder TextFields til kundens datafelt.
        lblForsikringsnummer.textProperty().bind(valgtKunde.forsikringsnummerProperty().asString());
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
        if(skademeldingValidator.textIsValid(typeSkade)){
            txtTypeSkade.setStyle("-fx-border-color: green");
        } else {
            txtTypeSkade.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void beskrivelseChanged(InputEvent event){
        String beskrivelse = txtBeskrivelse.getText();
        if(skademeldingValidator.textOgTallIsValid(beskrivelse)){
            txtBeskrivelse.setStyle("-fx-border-color: green");
        } else {
            txtBeskrivelse.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void vitneChanged(InputEvent event){
        String vitner = txtVitner.getText();
        if(skademeldingValidator.textOgTallIsValid(vitner)){
            txtVitner.setStyle("-fx-border-color: green");
        } else {
            txtVitner.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void takseringChanged(InputEvent event){
        String takseringAvSkaden = txtTakseringAvSkaden.getText();
        if(skademeldingValidator.tallIsValid(takseringAvSkaden)){
            txtTakseringAvSkaden.setStyle("-fx-border-color: green");
        } else {
            txtTakseringAvSkaden.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void utbetaltChanged(InputEvent event){
        String utbetaltErstatningsbeløp = txtUtbetaltErstatningsbeløp.getText();
        if(skademeldingValidator.tallIsValid(utbetaltErstatningsbeløp)){
            txtUtbetaltErstatningsbeløp.setStyle("-fx-border-color: green");
        } else {
            txtUtbetaltErstatningsbeløp.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void registrerSkademeldingClicked(ActionEvent event){
        //Gjør om til LocalDate objekt
        LocalDate date = datePicker.getValue();
        String typeSkade = txtTypeSkade.getText();
        String beskrivelse = txtBeskrivelse.getText();
        String vitner = txtVitner.getText();
        String takseringAvSkaden = txtTakseringAvSkaden.getText();
        String utbetaltErstatningsbeløp = txtUtbetaltErstatningsbeløp.getText();

        Skademelding skademelding = new Skademelding(date, typeSkade,
                beskrivelse, vitner, takseringAvSkaden, utbetaltErstatningsbeløp);

        if (skademeldingValidator.skademeldingIsValid(skademelding)){

            valgtKunde.addSkademelding(skademelding);
            Stage thisStage = (Stage) btnRegistrerSkademelding.getScene().getWindow();
            thisStage.close();

        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ugyldig input");
            alert.setTitle("Feil ved registrering");
            alert.showAndWait();
        }

    }

    @FXML
    private void backButtonClicked(ActionEvent event){
        Stage thisStage = (Stage) backButton.getScene().getWindow();
        thisStage.close();
    }

}
