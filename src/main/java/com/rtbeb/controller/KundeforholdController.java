package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KundeforholdController implements Initializable {

    Kunde kunde;

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    KundeforholdController(Kunde kunde){
        this.kunde = kunde;
    }

    @FXML
    Label lblFornavn;

    @FXML
    Label lblEtternavn;

    @FXML
    Label lblForsikringsnummer;

    @FXML
    Label lblFakturaadresse;

    @FXML
    Label lblPostnummer;

    @FXML
    Button backButton;

    @FXML
    private void handleBackButtonClicked(ActionEvent event){

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/kundevisning.fxml"));
            Scene previousScene = new Scene(root);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(previousScene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblForsikringsnummer.textProperty().bind(kunde.forsikringsnummerProperty().asString());
        lblFornavn.textProperty().bind(kunde.fornavnProperty());
        lblEtternavn.textProperty().bind(kunde.etternavnProperty());
        lblFakturaadresse.textProperty().bind(kunde.fakturaadresseProperty());
        lblPostnummer.textProperty().bind(kunde.postnummerProperty());

    }
}
