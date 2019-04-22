package com.rtbeb.controller;

import com.rtbeb.controller.helper.FieldStyler;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.base.forsikring.Reiseforsikring;
import com.rtbeb.model.validation.ForsikringValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrerReiseforsikringController implements Initializable {

    Kunde kunde;

    public RegistrerReiseforsikringController(Kunde kunde){
        this.kunde = kunde;
    }

    @FXML
    Button btnNeste;


    @FXML
    private TextField txtForsikringspremie;

    @FXML
    private TextField txtForsikringsbeløp;

    @FXML
    private TextArea txtBetingelser;

    @FXML
    private TextField txtForsikringsområde;

    @FXML
    private TextField txtForsikringssum;

    @FXML
    private void goToForsikringsdetaljerTab(ActionEvent event){

        //Får tak i TabPane
        TabPane tabPane = (TabPane)btnNeste.getParent().getParent().getParent();

        //Velger andre tab
        tabPane.getSelectionModel().select(1);
    }

    @FXML
    private void goToReiseTab(){

        //Får tak i TabPane
        TabPane tabPane = (TabPane)btnNeste.getParent().getParent().getParent();

        //Velger første
        tabPane.getSelectionModel().select(0);
    }

    @FXML
    private void lukkVindu(ActionEvent event){
        Stage stage = (Stage) btnNeste.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void registrerReiseforsikring(ActionEvent event){

        try {
            Reiseforsikring reiseforsikring = generateReiseforsikring();
            kunde.addForsikring(reiseforsikring);
        } catch (InvalidForsikringException|NumberFormatException e) {
            generateAlert("Kunne ikke registrere forsikring:\nFyll inn alle felt eller sjekk rød-markerte felt.");
        }
    }

    private void generateAlert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Registreringsfeil");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private Reiseforsikring generateReiseforsikring() throws NumberFormatException{

        int forsikringspremie = Integer.parseInt(txtForsikringspremie.getText());
        int forsikringsbeløp = Integer.parseInt(txtForsikringsbeløp.getText());
        String forsikringsbetingelser = txtBetingelser.getText();
        String forsikringsområde = txtForsikringsområde.getText();
        int forsikringssum = Integer.parseInt(txtForsikringssum.getText());

        return new Reiseforsikring(forsikringspremie, forsikringsbeløp,
                forsikringsbetingelser, forsikringsområde, forsikringssum);
    }

    //-----------VALIDERING---------//

    //TODO implementer validering.

    @FXML
    void betingelserChanged(InputEvent event) {
        String betingelser = txtBetingelser.getText();

        if(!ForsikringValidator.forsikringsbetingelserIsValid(betingelser)){
            FieldStyler.setInvalidStyle(txtBetingelser);
        } else{
            FieldStyler.setValidStyle(txtBetingelser);
        }
    }

    @FXML
    void forsikringsbeløpChanged(InputEvent event) {
        String forsikringsbeløp = txtForsikringsbeløp.getText();

        if(!ForsikringValidator.forsikringsbelopIsValid(forsikringsbeløp)){
            FieldStyler.setInvalidStyle(txtForsikringsbeløp);
        } else{
            FieldStyler.setValidStyle(txtForsikringsbeløp);
        }
    }

    @FXML
    void forsikringspremieChanged(InputEvent event) {
        String forsikringspremie = txtForsikringspremie.getText();

        if(!ForsikringValidator.forsikringspremieIsValid(forsikringspremie)){
            FieldStyler.setInvalidStyle(txtForsikringspremie);
        } else{
            FieldStyler.setValidStyle(txtForsikringspremie);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNumericListeneres();
    }

    private void addNumericListeneres(){
        //Listener for å hindre at ikke-numeriske characters blir skrevet i felt.
        txtForsikringspremie.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtForsikringspremie.setText(oldValue);

            }
        });

        //Listener for å hindre at ikke-numeriske characters blir skrevet i felt.
        txtForsikringsbeløp.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtForsikringsbeløp.setText(oldValue);

            }
        });

        //Listener for å hindre at ikke-numeriske characters blir skrevet i felt.
        txtForsikringssum.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtForsikringssum.setText(oldValue);

            }
        });
    }
}
