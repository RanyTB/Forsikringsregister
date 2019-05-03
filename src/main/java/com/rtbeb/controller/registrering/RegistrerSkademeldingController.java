package com.rtbeb.controller.registrering;

import com.rtbeb.controller.helper.AlertGenerator;
import com.rtbeb.controller.helper.FieldStyler;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.exception.InvalidSkademeldingException;
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
 * Kontroller for registrering av Skademelding.
 * @author Eirik Bøyum
 */
public class RegistrerSkademeldingController implements Initializable {

    private Kunde valgtKunde;
    private SkademeldingValidator skademeldingValidator;

    public RegistrerSkademeldingController(Kunde valgtKunde){
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

    /**
     *
     * Disse metodene validerer i sanntid input fra bruker.
     * Taster bruker inn gyldig input får teltet grønn farge.
     * Taster bruker inn ugyldig input får teltet rød farge.
     */
    @FXML
    private void dateChanged(ActionEvent event){
        LocalDate date = datePicker.getValue();
        if(skademeldingValidator.dateIsValid(date)){
            FieldStyler.setValidStyle(datePicker);
        } else {
            FieldStyler.setInvalidStyle(datePicker);
        }

    }

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
     * Denne metoden henter inn en generert skademelding. Den prøver så å putte skademeldingen inn i skademelding lista.
     * addSkademelding kaster en feilmelding hvis dette ikke går og bruker blir informert om dette.
     */
    @FXML
    private void registrerSkademeldingClicked(ActionEvent event){
        Skademelding skademelding = generateSkademelding();

        try {
            valgtKunde.addSkademelding(skademelding);
        } catch (InvalidSkademeldingException e) {
            AlertGenerator.registreringsfeilAlert("Feil ved registrering.\nFyll inn alle felt eller sjekk rød-markerte felt.");
        }
        Stage thisStage = (Stage) btnRegistrerSkademelding.getScene().getWindow();
        thisStage.close();
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

        Skademelding skademelding = new Skademelding(date, typeSkade,
                beskrivelse, vitner, takseringAvSkaden, utbetaltErstatningsbeløp);

        return skademelding;
    }

    @FXML
    private void backButtonClicked(ActionEvent event){
        Stage thisStage = (Stage) backButton.getScene().getWindow();
        thisStage.close();
    }

}
