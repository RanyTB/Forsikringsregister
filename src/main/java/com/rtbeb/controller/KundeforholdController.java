package com.rtbeb.controller;

import com.rtbeb.model.base.Forsikring;
import com.rtbeb.model.base.Kunde;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KundeforholdController implements Initializable {

    Kunde valgtKunde;

    KundeforholdController(Kunde valgtKunde){
        this.valgtKunde = valgtKunde;
    }

    @FXML
    private Label lblFornavn;

    @FXML
    private Label lblEtternavn;

    @FXML
    private Label lblForsikringsnummer;

    @FXML
    private Label lblFakturaadresse;

    @FXML
    private Label lblPostnummer;

    @FXML
    private void redigerKundeClicked(){
        System.out.println("Ikke implementert enda");
    }

    @FXML
    private void slettKundeClicked(){
        System.out.println("Ikke implementert enda");
    }


    @FXML
    Button backButton;

    @FXML
    private void backButtonClicked(ActionEvent event){

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

        lblForsikringsnummer.textProperty().bind(valgtKunde.forsikringsnummerProperty().asString());
        lblFornavn.textProperty().bind(valgtKunde.fornavnProperty());
        lblEtternavn.textProperty().bind(valgtKunde.etternavnProperty());
        lblFakturaadresse.textProperty().bind(valgtKunde.fakturaadresseProperty());
        lblPostnummer.textProperty().bind(valgtKunde.postnummerProperty());
    }
}
