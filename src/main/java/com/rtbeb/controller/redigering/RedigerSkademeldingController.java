package com.rtbeb.controller.redigering;

import com.rtbeb.controller.helper.AlertGenerator;
import com.rtbeb.controller.helper.FieldStyler;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.forsikring.Skademelding;
import com.rtbeb.model.validation.SkademeldingValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Kontroller for redigering av skademelding
 * @author Eirik Bøyum - s170002
 */
public class RedigerSkademeldingController implements Initializable {

    private Kunde valgtKunde;
    private Skademelding valgtSkademelding;
    private SkademeldingValidator skademeldingValidator;


    public RedigerSkademeldingController(Kunde valgtKunde, Skademelding valgtSkademelding){
        this.valgtKunde = valgtKunde;
        this.valgtSkademelding = valgtSkademelding;
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
    private Button btnRedigerSkademelding;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblForsikringsnummer.textProperty().bind(valgtKunde.forsikringsnummerProperty().asString());
        datePicker.setValue(valgtSkademelding.getSkademeldingsDato());
        txtTypeSkade.setText(valgtSkademelding.getTypeSkade());
        txtBeskrivelse.setText(valgtSkademelding.getBeskrivelse());
        txtVitner.setText(valgtSkademelding.getVitner());
        txtTakseringAvSkaden.setText(valgtSkademelding.getTakseringAvSkaden());
        txtUtbetaltErstatningsbeløp.setText(valgtSkademelding.getUtbetaltErstatningsbeløp());

    }

    @FXML
    private void dateChanged(ActionEvent event){
        LocalDate date = datePicker.getValue();
        if(skademeldingValidator.dateIsValid(date)){
            FieldStyler.setValidStyle(datePicker);
        } else {
            FieldStyler.setInvalidStyle(datePicker);
        }

    }

    /**
     *
     * Disse metodene validerer i sanntid input fra bruker.
     * Taster bruker inn gyldig input får teltet grønn farge.
     * Taster bruker inn ugyldig input får teltet rød farge.
     */
    @FXML
    private void typeSkadeChanged(InputEvent event){
        String typeSkade = txtTypeSkade.getText();
        if(skademeldingValidator.textIsValid(typeSkade)){
            FieldStyler.setValidStyle(txtTypeSkade);
        } else {
            FieldStyler.setInvalidStyle(txtTypeSkade);
        }
    }

    @FXML
    private void beskrivelseChanged(InputEvent event){
        String beskrivelse = txtBeskrivelse.getText();
        if(skademeldingValidator.textOgTallIsValid(beskrivelse)){
            FieldStyler.setValidStyle(txtBeskrivelse);
        } else {
            FieldStyler.setInvalidStyle(txtBeskrivelse);
        }
    }

    @FXML
    private void vitneChanged(InputEvent event){
        String vitner = txtVitner.getText();
        if(skademeldingValidator.textOgTallIsValid(vitner)){
            FieldStyler.setValidStyle(txtVitner);
        } else {
            FieldStyler.setInvalidStyle(txtVitner);
        }
    }

    @FXML
    private void takseringChanged(InputEvent event){
        String takseringAvSkaden = txtTakseringAvSkaden.getText();
        if(skademeldingValidator.tallIsValid(takseringAvSkaden)){
            FieldStyler.setValidStyle(txtTakseringAvSkaden);
        } else {
            FieldStyler.setInvalidStyle(txtTakseringAvSkaden);
        }
    }

    @FXML
    private void utbetaltChanged(InputEvent event){
        String utbetaltErstatningsbeløp = txtUtbetaltErstatningsbeløp.getText();
        if(skademeldingValidator.tallIsValid(utbetaltErstatningsbeløp)){
            FieldStyler.setValidStyle(txtUtbetaltErstatningsbeløp);
        } else {
            FieldStyler.setInvalidStyle(txtUtbetaltErstatningsbeløp);
        }
    }

    /**
     * Denne metoden henter inn en generert skademelding. Den kaller så på metoden skademeldingIsValid som tar imot en
     * skademelding og tester så alle feltene. Ut i fra dette returneres true / false. Returneres true så oppdateres
     * skademeldingsinformasjonen. Returneres false kommer den opp en feilmelding til kunde som informerer om dette.
     */
    @FXML
    private void redigerSkademeldingClicked(ActionEvent event){
        Skademelding skademelding = generateSkademelding();

        if (skademelding.isValid()){
            valgtSkademelding.setSkademeldingsDato(datePicker.getValue());
            valgtSkademelding.setTypeSkade(txtTypeSkade.getText());
            valgtSkademelding.setBeskrivelse(txtBeskrivelse.getText());
            valgtSkademelding.setVitner(txtVitner.getText());
            valgtSkademelding.setTakseringAvSkaden(txtTakseringAvSkaden.getText());
            valgtSkademelding.setUtbetaltErstatningsbeløp(txtUtbetaltErstatningsbeløp.getText());

            Stage thisStage = (Stage) btnRedigerSkademelding.getScene().getWindow();
            thisStage.close();
        } else {
            AlertGenerator.redigeringsfeilAlert("Kunne ikke redigere skademelding. Sjekk tomme eller rød-markerte felt.");
        }
    }

    /**
     * Genererer skademelding ut i fra brukerens innput i feltene.
     */
    private Skademelding generateSkademelding(){
        //Gjør om til LocalDate objekt
        LocalDate date = datePicker.getValue();
        String typeSkade = txtTypeSkade.getText();
        String beskrivelse = txtBeskrivelse.getText();
        String vitner = txtVitner.getText();
        String takseringAvSkaden = txtTakseringAvSkaden.getText();
        String utbetaltErstatningsbeløp = txtUtbetaltErstatningsbeløp.getText();

        return new Skademelding(date, typeSkade,
                beskrivelse, vitner, takseringAvSkaden, utbetaltErstatningsbeløp);
    }

    @FXML
    private void backButtonClicked(ActionEvent event){
        Stage thisStage = (Stage) backButton.getScene().getWindow();
        thisStage.close();
    }
}
