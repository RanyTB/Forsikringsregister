package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.validation.KundeValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RedigerKundeController implements Initializable {

    //Kundeobjektet oppe for redigering
    Kunde kunde;

    //Konstruktør setter kundeobjektet.
    public RedigerKundeController(Kunde kunde){
        this.kunde = kunde;
    }

    @FXML
    TextField txtFornavn;
    @FXML
    TextField txtEtternavn;
    @FXML
    TextField txtAdresse;
    @FXML
    TextField txtPostnummer;

    @FXML
    Button btnRedigerKunde;
    @FXML
    Button btnLukkVindu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtFornavn.setText(kunde.getFornavn());
        txtEtternavn.setText(kunde.getEtternavn());
        txtAdresse.setText(kunde.getFakturaadresse());
        txtPostnummer.setText(kunde.getPostnummer());
    }


    @FXML
    private void redigerKunde(ActionEvent event){
        String fornavn = txtFornavn.getText();
        String etternavn = txtEtternavn.getText();
        String fakturaadresse = txtAdresse.getText();
        String postnummer = txtPostnummer.getText();

        if (KundeValidator.fornavnIsValid(fornavn) && KundeValidator.etternavnIsValid(etternavn)
        && KundeValidator.fakturaAdresseIsValid(fakturaadresse) && KundeValidator.postnummerIsValid(postnummer)){
            kunde.setFornavn(fornavn);
            kunde.setEtternavn(etternavn);
            kunde.setFakturaadresse(fakturaadresse);
            kunde.setPostnummer(postnummer);

            Stage thisStage = (Stage) btnRedigerKunde.getScene().getWindow();
            thisStage.close();
        } else {

        }

    }

    @FXML
    private void lukkVindu(ActionEvent event){
        Stage thisStage = (Stage) btnLukkVindu.getScene().getWindow();
        thisStage.close();
    }

    //-----------VALIDERING----------//

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

}
