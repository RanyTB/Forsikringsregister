package com.rtbeb.controller.redigering;

import com.rtbeb.controller.helper.AlertGenerator;
import com.rtbeb.controller.helper.FieldStyler;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.forsikring.Skademelding;
import com.rtbeb.model.validation.SkademeldingValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroller for ubetalt erstatning
 * @author Eirik Bøyum - s170002
 */
public class UtbetalErstatningController implements Initializable {

    private Kunde valgtKunde;
    private Skademelding skademelding;
    private SkademeldingValidator skademeldingValidator;

    public UtbetalErstatningController(Kunde valgtKunde, Skademelding skademelding){
        this.valgtKunde = valgtKunde;
        this.skademelding = skademelding;
    }

    @FXML
    private Label lblSkademeldingsnummer;
    @FXML
    private Label lblTakseringAvSkaden;
    @FXML
    private TextField txtUtbetaltErstatningsbeløp;
    @FXML
    private Button btnUtbetalErstatning;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bindLabelsWithCustomerInfo();

    }
    @FXML
    private void utbetaltChanged(InputEvent event){
        String erstatningsBeløp = txtUtbetaltErstatningsbeløp.getText();
        if (skademeldingValidator.tallIsValid(erstatningsBeløp)){
            FieldStyler.setValidStyle(txtUtbetaltErstatningsbeløp);
        }else {
            FieldStyler.setInvalidStyle(txtUtbetaltErstatningsbeløp);
        }
    }

    @FXML
    private void bindLabelsWithCustomerInfo(){
        //Binder TextFields til kundens datafelt.
        lblSkademeldingsnummer.textProperty().bind(skademelding.skadenummerProperty().asString());
        lblTakseringAvSkaden.textProperty().bind(skademelding.takseringAvSkadenProperty());

    }

    /**
     * Metoden sjekker om erstatningsbeløper er gyldig hvis true oppdaterer
     * utbetaltErstatnigsBeløp med gitt verdi. Hvis false blir bruker informert om dette
     */
    @FXML
    private void utbetalErstatningClicked(ActionEvent event){
        String erstatningsBeløp = txtUtbetaltErstatningsbeløp.getText();

        if (skademeldingValidator.tallIsValid(erstatningsBeløp)){
            skademelding.setUtbetaltErstatningsbeløp(erstatningsBeløp);

            Stage thisStage = (Stage) btnUtbetalErstatning.getScene().getWindow();
            thisStage.close();
        } else {
            AlertGenerator.redigeringsfeilAlert("Beløpet er ugyldig.");
        }
    }

    @FXML
    private void backButtonClicked(ActionEvent event){
        Stage thisStage = (Stage) backButton.getScene().getWindow();
        thisStage.close();
    }

}
