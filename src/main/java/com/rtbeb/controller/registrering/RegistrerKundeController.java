package com.rtbeb.controller.registrering;

import com.rtbeb.controller.helper.AlertGenerator;
import com.rtbeb.controller.helper.FieldStyler;
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

/**
 * Kontroller for registrering av nye kunder.
 * @author Rany Tarek Bouorm - s236210
 */
public class RegistrerKundeController implements Initializable {

    private Kunderegister kunderegister = Kunderegister.getInstance();

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
    private void opprettKunde(ActionEvent event){

        Kunde kunde = new Kunde(txtFornavn.getText(), txtEtternavn.getText(), txtAdresse.getText(), txtPostnummer.getText());

        //Hvis kunde er gyldig, settes kunden inn i kunderegister. Ellers vises feilmelding.
            try {
                kunderegister.insertKunde(kunde);
            } catch (InvalidKundeException e) {
                AlertGenerator.registreringsfeilAlert("Kunne ikke registrere kunden:\nSjekk kundedata.");
            }
    }

    @FXML
    private void lukkVindu(ActionEvent event){

        Stage stage = (Stage) btnLukkVindu.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    //--------------VALIDERING---------------//

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
