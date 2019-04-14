package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;
import com.rtbeb.model.base.exception.InvalidKundeException;
import com.rtbeb.model.validation.KundeValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NyKundeController implements Initializable {

    Kunderegister kunderegister = Kunderegister.getInstance();

    @FXML
    TextField txtFornavn;
    @FXML
    TextField txtEtternavn;
    @FXML
    TextField txtAdresse;
    @FXML
    TextField txtPostnummer;

    @FXML
    Button btnLukkVindu;

    @FXML
    private void fornavnChanged(InputEvent event){

        String fornavn = txtFornavn.getText();
        if(!KundeValidator.fornavnIsValid(fornavn)){
            txtFornavn.setStyle("-fx-border-color: red");
        } else{
            txtFornavn.setStyle("-fx-border-color: green");
        }
    }

    @FXML
    private void etternavnChanged(InputEvent event){

        String etternavn = txtEtternavn.getText();
        if(!KundeValidator.etternavnIsValid(etternavn)){
            txtEtternavn.setStyle("-fx-border-color: red");
        } else{
            txtEtternavn.setStyle("-fx-border-color: green");
        }
    }

    @FXML
    private void adresseChanged(InputEvent event){

        String adresse = txtAdresse.getText();
        if(!KundeValidator.fakturaAdresseIsValid(adresse)){
            txtAdresse.setStyle("-fx-border-color: red");
        } else{
            txtAdresse.setStyle("-fx-border-color: green");
        }
    }

    @FXML
    private void postnummerChanged(InputEvent event){

        String postnummer = txtPostnummer.getText();
        if(!KundeValidator.postnummerIsValid(postnummer)){
            txtPostnummer.setStyle("-fx-border-color: red");
        } else{
            txtPostnummer.setStyle("-fx-border-color: green");
        }

    }

    @FXML
    private void opprettKunde(){

        Kunde kunde = new Kunde(txtFornavn.getText(), txtEtternavn.getText(), txtAdresse.getText(), txtPostnummer.getText());

        //Hvis kunde er gyldig, settes kunden inn i kunderegister. Ellers vises feilmelding.
            try {
                kunderegister.insertKunde(kunde);
            } catch (InvalidKundeException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Registreringsfeil");
                alert.setHeaderText("Kunnde ikke registrere kunde:\nSjekk kundedata.");
                alert.show();
            }
    }

    @FXML
    private void lukkVindu(ActionEvent event){

        System.out.println("Button clicked");
        Stage stage = (Stage) btnLukkVindu.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
