package com.rtbeb.controller.forsikring;

import com.rtbeb.controller.helper.FieldStyler;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.base.forsikring.Bolig.Bolig;
import com.rtbeb.model.base.forsikring.Bolig.Bolig.Boligtype;
import com.rtbeb.model.base.forsikring.Bolig.Bolig.Byggemateriale;
import com.rtbeb.model.base.forsikring.Bolig.Bolig.Standard;
import com.rtbeb.model.base.forsikring.Bolig.Innboforsikring;
import com.rtbeb.model.validation.BoligValidator;
import com.rtbeb.model.validation.ForsikringValidator;
import com.rtbeb.model.validation.InnboForsikringValidator;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrerInnboForsikringController extends RegistrerForsikringController implements Initializable {

    Innboforsikring.Innbotype innbotype;

    public RegistrerInnboForsikringController(Kunde kunde, Innboforsikring.Innbotype innbotype){
        super(kunde);
        this.innbotype = innbotype;
    }

    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtPostnummer;
    @FXML
    private TextField txtByggeår;
    @FXML
    private ChoiceBox<Boligtype> cBoxBoligtype;
    @FXML
    private ChoiceBox<Byggemateriale> cBoxByggemateriale;
    @FXML
    private ChoiceBox<Standard> cBoxStandard;
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

            //Lukk stage hvis forsikring blir registrert OK.
            Stage stage = (Stage) btnNeste.getScene().getWindow();
            stage.close();
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
        Boligtype boligtype = cBoxBoligtype.getSelectionModel().getSelectedItem();
        Byggemateriale byggemateriale = cBoxByggemateriale.getSelectionModel().getSelectedItem();
        Standard standard = cBoxStandard.getSelectionModel().getSelectedItem();
        String størrelse = txtStørrelse.getText();
        int forsikringssbeløpBygning = Integer.parseInt(txtForsikringsbeløpBygning.getText());
        int forsikringsbeløpInnbo = Integer.parseInt(txtForsikringsbeløpInnbo.getText());

        Bolig bolig = new Bolig(adresse, postnummer, byggeår, boligtype,
                byggemateriale, standard, størrelse);

        return new Innboforsikring(this.innbotype, forsikringspremie, forsikringsbeløp, betingelser,
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Henter lovlige verdier for Boligtype, Byggemateriale og Standard og setter disse inn i ChoiceBox-ene.
        cBoxBoligtype.getItems().setAll(Boligtype.values());
        cBoxByggemateriale.getItems().setAll(Byggemateriale.values());
        cBoxStandard.getItems().setAll(Standard.values());

        TextField[] numericFields = {txtPostnummer,txtByggeår,txtStørrelse, txtForsikringsbeløpBygning,
                txtForsikringsbeløpInnbo, txtForsikringspremie, txtForsikringsbeløp};
        addNumericListeners(numericFields);
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

        if(!InnboForsikringValidator.forsikringsbeløpBygningIsValid(forsikringsbeløpBygning)){
            FieldStyler.setInvalidStyle(txtForsikringsbeløpBygning);
        } else{
            FieldStyler.setValidStyle(txtForsikringsbeløpBygning);
            updateForsikringsbeløp();
        }

    }

    /**
     * Metoden oppdaterer forsikringsbeløpet slik at det blir satt til summen av forsikringsbeløpet for innbo og bygning.
     */
    private void updateForsikringsbeløp(){

        String forsikringbeløpInnbo = txtForsikringsbeløpInnbo.getText();
        String forsikringsbeløpBygning = txtForsikringsbeløpBygning.getText();

        try{

        int forsikringbeløpInnboParsed = Integer.parseInt(forsikringbeløpInnbo);
        int forsikringsbeløpBygningParsed = Integer.parseInt(forsikringsbeløpBygning);

        int forsikringsbeløp = forsikringbeløpInnboParsed + forsikringsbeløpBygningParsed;
        txtForsikringsbeløp.setText(String.valueOf(forsikringsbeløp));

        } catch(NumberFormatException e){ /*Dette oppstår kun hvis et av feltene ikke er fylt ut.*/}
    }

    @FXML
    private void forsikringsbeløpInnboChanged(){

        String forsikringsbeløpInnbo = txtForsikringsbeløpInnbo.getText();

        if(!InnboForsikringValidator.forsikringsbeløpInnboIsValid(forsikringsbeløpInnbo)){
            FieldStyler.setInvalidStyle(txtForsikringsbeløpInnbo);
        } else{
            FieldStyler.setValidStyle(txtForsikringsbeløpInnbo);
            updateForsikringsbeløp();
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


    @FXML void betingelserChanged(){
        String betingelser = txtBetingelser.getText();

        if(!ForsikringValidator.forsikringsbetingelserIsValid(betingelser)){
            FieldStyler.setInvalidStyle(txtBetingelser);
        } else{
            FieldStyler.setValidStyle(txtBetingelser);
        }
    }


}
