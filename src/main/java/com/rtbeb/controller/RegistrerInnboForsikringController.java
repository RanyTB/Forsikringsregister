package com.rtbeb.controller;

import com.rtbeb.controller.helper.FieldStyler;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.base.forsikring.Bolig.Bolig;
import com.rtbeb.model.base.forsikring.Bolig.Innboforsikring;
import com.rtbeb.model.validation.BoligValidator;
import com.rtbeb.model.validation.ForsikringValidator;
import com.rtbeb.model.validation.InnboForsikringValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrerInnboForsikringController extends RegistrerForsikringController implements Initializable {


    public RegistrerInnboForsikringController(Kunde kunde){
        super(kunde);
    }

    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtPostnummer;
    @FXML
    private TextField txtByggeår;
    @FXML
    private ChoiceBox<?> txtBoligtype;
    @FXML
    private ChoiceBox<?> txtByggemateriale;
    @FXML
    private ChoiceBox<?> txtStandard;
    @FXML
    private TextField txtStørrelse;
    @FXML
    private TextField txtForsikringspremie;
    @FXML
    private TextField txtForsikringsbeløp;
    @FXML
    private TextArea txtBetingelser;
    @FXML
    private TextField txtForsikringsbeløpBygning;
    @FXML
    private TextField txtForsikringsbeløpInnbo;
    @FXML
    private Button btnNeste;

    @FXML
    void registrerInnboforsikring(ActionEvent event){

        try {
            Innboforsikring forsikring = generateInnboforsikring();
            kunde.addForsikring(forsikring);
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

    private Innboforsikring generateInnboforsikring() throws NumberFormatException{

        int forsikringspremie = Integer.parseInt(txtForsikringspremie.getText());
        int forsikringsbeløp = Integer.parseInt(txtForsikringsbeløp.getText());
        String betingelser = txtBetingelser.getText();
        String adresse = txtAdresse.getText();
        String postnummer = txtPostnummer.getText();
        String byggeår = txtByggeår.getText();
        /*String boligtype = txtBoligtype.getSelectionModel().getSelectedItem().toString();
        String byggemateriale = txtByggemateriale.getSelectionModel().getSelectedItem().toString();
        String standard = txtStandard.getSelectionModel().getSelectedItem().toString();      */

        String boligtype = "test";
        String byggemateriale = "test";
        String standard = "test";
        String størrelse = txtStørrelse.getText();
        int forsikringssbeløpBygning = Integer.parseInt(txtForsikringsbeløpBygning.getText());
        int forsikringsbeløpInnbo = Integer.parseInt(txtForsikringsbeløpInnbo.getText());

        Bolig bolig = new Bolig(adresse, postnummer, byggeår, boligtype,
                byggemateriale, standard, størrelse);

        return new Innboforsikring(forsikringspremie, forsikringsbeløp, betingelser,
                bolig, forsikringssbeløpBygning,forsikringsbeløpInnbo);
    }

    @FXML
    void goToForsikringsdetaljerTab(ActionEvent event) {

        //Får tak i TabPane
        TabPane tabPane = (TabPane)btnNeste.getParent().getParent().getParent();

        //Velger andre tab
        tabPane.getSelectionModel().select(1);
    }

    @FXML
    void goToBoligTab(ActionEvent event) {

        //Får tak i TabPane
        TabPane tabPane = (TabPane)btnNeste.getParent().getParent().getParent();

        //Velger andre tab
        tabPane.getSelectionModel().select(0);
    }

    @FXML
    void lukkVindu(ActionEvent event) {
        Stage stage = (Stage) btnNeste.getScene().getWindow();
        stage.close();

    }


    //------------VALIDERING---------------//
    @FXML
    private void adresseChanged(InputEvent event){
        String adresse = txtAdresse.getText();

        if(!BoligValidator.adresseIsValid(adresse)){
            FieldStyler.setInvalidStyle(txtAdresse);
        } else{
            FieldStyler.setValidStyle(txtAdresse);
        }
    }

    @FXML
    private void postnummerChanged(){
        String postnummer = txtPostnummer.getText();

        if(!BoligValidator.postnummerIsValid(postnummer)){
            FieldStyler.setInvalidStyle(txtPostnummer);
        } else{
            FieldStyler.setValidStyle(txtPostnummer);
        }
    }

    @FXML
    private void byggeårChanged(){
        String byggeår = txtByggeår.getText();

        if(!BoligValidator.byggeårIsValid(byggeår)){
            FieldStyler.setInvalidStyle(txtByggeår);
        } else{
            FieldStyler.setValidStyle(txtByggeår);
        }
    }

    @FXML
    private void størrelseChanged(){
        String størrelse = txtStørrelse.getText();

        if(!BoligValidator.størrelseIsValid(størrelse)){
            FieldStyler.setInvalidStyle(txtStørrelse);
        } else{
            FieldStyler.setValidStyle(txtStørrelse);
        }
    }

    @FXML
    private void forsikringsbeløpBygningChanged(){

        String forsikringsbeløpBygning = txtForsikringsbeløpBygning.getText();

        if(!InnboForsikringValidator.forsikringssbeløpBygningIsValid(forsikringsbeløpBygning)){
            FieldStyler.setInvalidStyle(txtForsikringsbeløpBygning);
        } else{
            FieldStyler.setValidStyle(txtForsikringsbeløpBygning);
        }

    }
    @FXML
    private void forsikringsbeløpInnboChanged(){

        String forsikringsbeløpInnbo = txtForsikringsbeløpInnbo.getText();

        if(!InnboForsikringValidator.forsikringsbeløpInnboIsValid(forsikringsbeløpInnbo)){
            FieldStyler.setInvalidStyle(txtForsikringsbeløpInnbo);
        } else{
            FieldStyler.setValidStyle(txtForsikringsbeløpInnbo);
        }
    }
    @FXML
    private void forsikringspremieChanged(InputEvent event){
        String forsikringspremie = txtForsikringspremie.getText();

        if(!ForsikringValidator.forsikringspremieIsValid(forsikringspremie)){
            FieldStyler.setInvalidStyle(txtForsikringspremie);
        } else{
            FieldStyler.setValidStyle(txtForsikringspremie);
        }
    }

    @FXML
    private void forsikringsbeløpChanged(InputEvent event){
        String forsikringsbeløp = txtForsikringsbeløp.getText();

        if(!ForsikringValidator.forsikringsbelopIsValid(forsikringsbeløp)){
            FieldStyler.setInvalidStyle(txtForsikringsbeløp);
        } else{
            FieldStyler.setValidStyle(txtForsikringsbeløp);
        }

    }

    @FXML void betingelserChanged(){
        String betingelser = txtBetingelser.getText();

        if(!ForsikringValidator.forsikringsbetingelserIsValid(betingelser)){
            FieldStyler.setInvalidStyle(txtBetingelser);
        } else{
            FieldStyler.setValidStyle(txtBetingelser);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TextField[] numericFields = {txtPostnummer,txtByggeår,txtStørrelse, txtForsikringsbeløpBygning,
                txtForsikringsbeløpInnbo, txtForsikringspremie, txtForsikringsbeløp};
        addNumericListeners(numericFields);
    }

}
