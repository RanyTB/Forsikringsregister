package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Skademelding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class RegistrerSkademeldingController implements Initializable {

    Kunde valgtKunde;

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
    private void registrerSkademeldingClicked(ActionEvent event){
        //Gjør om til LocalDate objekt
        LocalDate date = datePicker.getValue();

        Skademelding skademelding = new Skademelding(date, txtTypeSkade.getText(),
                txtBeskrivelse.getText(), txtVitner.getText(), Integer.parseInt(txtTakseringAvSkaden.getText()),
                Integer.parseInt(txtUtbetaltErstatningsbeløp.getText()));

        valgtKunde.addSkademelding(skademelding);

        Stage thisStage = (Stage) btnRegistrerSkademelding.getScene().getWindow();
        thisStage.close();
    }

    @FXML
    private void backButtonClicked(ActionEvent event){

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Kundeforhold.fxml"));
            Scene previousScene = new Scene(root);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(previousScene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
