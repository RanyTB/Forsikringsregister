package com.rtbeb.controller;

import com.rtbeb.model.base.Eier;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.base.forsikring.Båt;
import com.rtbeb.model.base.forsikring.Båtforsikring;
import com.rtbeb.model.validation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistrerBåtforsikringController {

    Kunde kunde;

    public RegistrerBåtforsikringController(Kunde kunde){
        this.kunde = kunde;
    }

    @FXML
    private TextField txtRegistreringsnummer;
    @FXML
    private TextField txtMerke;
    @FXML
    private TextField txtModell;
    @FXML
    private TextField txtLengde;
    @FXML
    private TextField txtÅrsmodell;
    @FXML
    private TextField txtMotorinfo;
    @FXML
    private TextField txtFornavn;
    @FXML
    private TextField txtEtternavn;
    @FXML
    private DatePicker dateFødselsdato;
    @FXML
    private TextField txtForsikringspremie;
    @FXML
    private TextField txtForsikringsbeløp;
    @FXML
    private TextArea txtBetingelser;

    @FXML
    private Button btnNeste;

    @FXML
    private void goToEierTab(ActionEvent event){

        //Får tak i TabPane
        TabPane tabPane = (TabPane)btnNeste.getParent().getParent().getParent();

        //Velger andre tab
        tabPane.getSelectionModel().select(1);
    }

    @FXML
    private void goToForsikringsdetaljerTab(ActionEvent event){

        //Får tak i TabPane
        TabPane tabPane = (TabPane)btnNeste.getParent().getParent().getParent();

        //Velger andre tab
        tabPane.getSelectionModel().select(2);
    }

    @FXML
    private void goToBåtTab(ActionEvent event){

        //Får tak i TabPane
        TabPane tabPane = (TabPane)btnNeste.getParent().getParent().getParent();

        //Velger andre tab
        tabPane.getSelectionModel().select(0);
    }

    @FXML
    private void lukkVindu(ActionEvent event){
        Stage stage = (Stage) btnNeste.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void registrerBåtforsikring(ActionEvent event){

        Båtforsikring båtforsikring = generateBåtforsikring();
        try {
            kunde.addForsikring(båtforsikring);
        } catch (InvalidForsikringException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Registreringsfeil");
            alert.setHeaderText("Kunne ikke registrere forsikring:\nSjekk markerte felt.");
            alert.show();
        }

    }

    private Båtforsikring generateBåtforsikring(){
        String fornavn = txtFornavn.getText();
        String etternavn = txtEtternavn.getText();
        LocalDate fødselsDato = dateFødselsdato.getValue();
        String registreringsnummer = txtRegistreringsnummer.getText();
        String merke = txtMerke.getText();
        String modell = txtModell.getText();
        String lengde = txtLengde.getText();
        String årsmodell = txtÅrsmodell.getText();
        String motorinfo = txtMotorinfo.getText();
        int forsikringspremie = Integer.parseInt(txtForsikringspremie.getText());
        int forsikringsbeløp = Integer.parseInt(txtForsikringsbeløp.getText());
        String betingelser = txtBetingelser.getText();

        Eier eier = new Eier(fornavn,etternavn,fødselsDato);
        Båt båt = new Båt(eier,registreringsnummer, merke, modell,lengde,årsmodell,motorinfo);
        Båtforsikring båtforsikring = new Båtforsikring(forsikringspremie,forsikringsbeløp,betingelser,båt);

        return båtforsikring;
    }

    //TODO fjern denne hvis fortsatt ikke brukt.
        private String getFødselsdatoAsString(){
        LocalDate date = dateFødselsdato.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYY");
        String fødselsdato = date.format(formatter);

        return fødselsdato;
    }

    //-----------VALIDERING--------------------//

    @FXML
    private void registreringsnummerChanged(InputEvent event){
        String registreringsnummer = txtRegistreringsnummer.getText();

        if(!BåtValidator.registreringsnummerIsValid(registreringsnummer)){
            FieldStyler.setInvalidStyle(txtRegistreringsnummer);
        } else{
            FieldStyler.setValidStyle(txtRegistreringsnummer);
        }
    }

    @FXML
    private void merkeChanged(InputEvent event){
        String merke = txtMerke.getText();

        if(!BåtValidator.merkeIsValid(merke)){
            FieldStyler.setInvalidStyle(txtMerke);
        } else{
            FieldStyler.setValidStyle(txtMerke);
        }
    }

    @FXML
    private void modellChanged(InputEvent event){
        String modell = txtModell.getText();

        if(!BåtValidator.modellIsValid(modell)) {
            FieldStyler.setInvalidStyle(txtModell);
        } else{
            FieldStyler.setValidStyle(txtModell);
        }
    }

    @FXML
    private void lengdeChanged(InputEvent event){
        String lengde = txtLengde.getText();

        if(!BåtValidator.lengdeIsValid(lengde)){
            FieldStyler.setInvalidStyle(txtLengde);
        } else{
            FieldStyler.setValidStyle(txtLengde);
        }
    }

    @FXML
    private void årsmodellChanged(InputEvent event){
        String årsmodell = txtÅrsmodell.getText();

        if(!BåtValidator.årsmodellIsValid(årsmodell)){
            FieldStyler.setInvalidStyle(txtÅrsmodell);
        } else{
            FieldStyler.setValidStyle(txtÅrsmodell);
        }
    }

    @FXML
    private void motortypeChanged(InputEvent event){
        String motorinfo = txtMotorinfo.getText();

        if(!BåtValidator.motorinfoIsValid(motorinfo)){
            FieldStyler.setInvalidStyle(txtMotorinfo);
        } else{
            FieldStyler.setValidStyle(txtMotorinfo);
        }
    }

    @FXML
    private void fornavnChanged(InputEvent event){
        String fornavn = txtFornavn.getText();

        if(!EierValidator.fornavnIsValid(fornavn)){
            FieldStyler.setInvalidStyle(txtFornavn);
        } else{
            FieldStyler.setValidStyle(txtFornavn);
        }
    }

    @FXML
    private void etternavnChanged(InputEvent event){
        String etternavn = txtEtternavn.getText();

        if(!EierValidator.etternavnIsValid(etternavn)){
            FieldStyler.setInvalidStyle(txtEtternavn);
        } else{
            FieldStyler.setValidStyle(txtEtternavn);
        }
    }

    @FXML
    private void fødselsdatoChanged(ActionEvent event){
        LocalDate fødselsdato = dateFødselsdato.getValue();

        if(!EierValidator.fødselsdatoIsValid(fødselsdato)){
            FieldStyler.setInvalidStyle(dateFødselsdato);
        } else{
            FieldStyler.setValidStyle(dateFødselsdato);
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

}
