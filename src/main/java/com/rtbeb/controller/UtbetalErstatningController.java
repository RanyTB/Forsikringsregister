package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Skademelding;
import com.rtbeb.model.validation.SkademeldingValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UtbetalErstatningController implements Initializable {

    Kunde valgtKunde;
    Skademelding skademelding;
    SkademeldingValidator skademeldingValidator;

    UtbetalErstatningController(Kunde valgtKunde, Skademelding skademelding){
        this.valgtKunde = valgtKunde;
        this.skademelding = skademelding;
    }

    @FXML
    private Label lblSkademeldingsnummer;
    @FXML
    private Label lblTakseringAvSkaden;
    @FXML
    private TextField txtUtbetaltErstatningsbeløp;
    @FXML
    private Button btnUtbetalErstatning;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bindLabelsWithCustomerInfo();

    }
    @FXML
    private void utbetaltChanged(InputEvent event){
        String erstatningsBeløp = txtUtbetaltErstatningsbeløp.getText();
        if (skademeldingValidator.tallIsValid(erstatningsBeløp)){
            txtUtbetaltErstatningsbeløp.setStyle("-fx-border-color: green");
        }else {
            txtUtbetaltErstatningsbeløp.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void bindLabelsWithCustomerInfo(){
        //Binder TextFields til kundens datafelt.
        lblSkademeldingsnummer.textProperty().bind(skademelding.skadenummerProperty().asString());
        lblTakseringAvSkaden.textProperty().bind(skademelding.takseringAvSkadenProperty());

    }

    @FXML
    private void utbetalErstatningClicked(ActionEvent event){
        String erstatningsBeløp = txtUtbetaltErstatningsbeløp.getText();

        if (skademeldingValidator.tallIsValid(erstatningsBeløp)){
            int beløp = Integer.parseInt(erstatningsBeløp);
            skademelding.setUtbetaltErstatningsbeløp(erstatningsBeløp);
            //Skademelding skademelding = new Skademelding(ubetaltErstatning);
        }
    }

    @FXML
    private void backButtonClicked(ActionEvent event){
        Stage thisStage = (Stage) backButton.getScene().getWindow();
        thisStage.close();
    }

}
