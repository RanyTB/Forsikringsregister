package com.rtbeb.controller.redigering;

import com.rtbeb.controller.helper.AlertGenerator;
import com.rtbeb.controller.helper.FieldStyler;
import com.rtbeb.controller.redigering.helper.RedigerforsikringHelper;
import com.rtbeb.model.base.Kunde;
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

/**
 * Kontroller for redigering av Kunde.
 * @author Rany Tarek Bouorm - s236210
 */
public class RedigerKundeController implements Initializable {

    //Kundeobjektet oppe for redigering
    private Kunde opprinneligKunde;

    //Konstruktør setter kundeobjektet.
    public RedigerKundeController(Kunde kunde){
        this.opprinneligKunde = kunde;
    }

    @FXML
    private
    TextField txtFornavn;
    @FXML
    private
    TextField txtEtternavn;
    @FXML
    private
    TextField txtAdresse;
    @FXML
    private
    TextField txtPostnummer;
    @FXML
    private
    Button btnRedigerKunde;
    @FXML
    private
    Button btnLukkVindu;



    @FXML
    private void redigerKunde(ActionEvent event){

        Kunde redigertKunde = generateKunde();

        if ( redigertKunde.isValid() ){

            updateOpprinneligKunde();

            //Lukk vindu.
            Stage thisStage = (Stage) btnRedigerKunde.getScene().getWindow();
            thisStage.close();

        } else {
            AlertGenerator.redigeringsfeilAlert("Kunne ikke redigere kunde:\nFyll inn alle felt eller sjekk rød-markerte felt.");
        }
    }

    private Kunde generateKunde(){
        String fornavn = txtFornavn.getText();
        String etternavn = txtEtternavn.getText();
        String fakturaadresse = txtAdresse.getText();
        String postnummer = txtPostnummer.getText();

        return new Kunde(fornavn,etternavn,fakturaadresse,postnummer);
    }

    private void updateOpprinneligKunde(){
        opprinneligKunde.setFornavn( txtFornavn.getText() );
        opprinneligKunde.setEtternavn( txtEtternavn.getText() );
        opprinneligKunde.setFakturaadresse( txtAdresse.getText() );
        opprinneligKunde.setPostnummer( txtPostnummer.getText() );
    }

    @FXML
    private void lukkVindu(ActionEvent event){
        Stage thisStage = (Stage) btnLukkVindu.getScene().getWindow();
        thisStage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initTextFields();
    }

    private void initTextFields() {
        txtFornavn.setText(opprinneligKunde.getFornavn());
        txtEtternavn.setText(opprinneligKunde.getEtternavn());
        txtAdresse.setText(opprinneligKunde.getFakturaadresse());
        txtPostnummer.setText(opprinneligKunde.getPostnummer());
    }
    //-----------VALIDERING----------//

    @FXML
    private void fornavnChanged(InputEvent event){
        String fornavn = txtFornavn.getText();

        if(!KundeValidator.fornavnIsValid(fornavn)){
            FieldStyler.setInvalidStyle(txtFornavn);
        } else{
            FieldStyler.setValidStyle(txtFornavn);
        }
    }

    @FXML
    private void etternavnChanged(InputEvent event){
        String etternavn = txtEtternavn.getText();

        if(!KundeValidator.etternavnIsValid(etternavn)){
            FieldStyler.setInvalidStyle(txtEtternavn);
        } else{
            FieldStyler.setValidStyle(txtEtternavn);
        }
    }

    @FXML
    private void adresseChanged(InputEvent event){
        String adresse = txtAdresse.getText();

        if(!KundeValidator.fakturaAdresseIsValid(adresse)){
            FieldStyler.setInvalidStyle(txtAdresse);
        } else{
            FieldStyler.setValidStyle(txtAdresse);
        }
    }
    @FXML
    private void postnummerChanged(InputEvent event){
        String postnummer = txtPostnummer.getText();

        if(!KundeValidator.postnummerIsValid(postnummer)){
            FieldStyler.setInvalidStyle(txtPostnummer);
        } else{
            FieldStyler.setValidStyle(txtPostnummer);
        }
    }

}
