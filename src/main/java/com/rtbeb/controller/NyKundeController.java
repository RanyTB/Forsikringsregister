package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;
import com.rtbeb.model.validation.KundeValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private void opprettKunde(){

        Kunde kunde = new Kunde(txtFornavn.getText(), txtEtternavn.getText(), txtAdresse.getText(), txtPostnummer.getText());

        KundeValidator validator = new KundeValidator(kunde);

        //Hvis kunde er gyldig, settes kunden inn i kunderegister. Ellers farges felt med rød border.
        if(validator.kundeIsValid()){
            kunderegister.insertKunde(kunde);
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Registreringsfeil");
            alert.setHeaderText("Kunden kunne ikke registreres. \nRett opp i markerte felt");
            alert.show();

            if (!validator.fornavnIsValid()) {
                txtFornavn.setStyle("-fx-border-color: red");
                txtFornavn.clear();
            }
            if (!validator.etternavnIsValid()) {
                txtEtternavn.setStyle("-fx-border-color: red");
                txtEtternavn.clear();
            }
            if (!validator.fakturaAdresseIsValid()) {
                txtAdresse.setStyle("-fx-border-color: red");
                txtAdresse.clear();
            }
            if (!validator.postnummerIsValid()) {
                txtPostnummer.setStyle("-fx-border-color: red");
                txtPostnummer.clear();
            }
        }
    }

    @FXML
    private void lukkVindu(ActionEvent event){
        System.out.println("Button clicked");
        Stage stage = (Stage) txtFornavn.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
