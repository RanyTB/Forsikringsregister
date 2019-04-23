package com.rtbeb.controller.forsikring.redigering;

import com.rtbeb.controller.helper.FieldStyler;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.forsikring.Bolig.Bolig;
import com.rtbeb.model.base.forsikring.Bolig.Innboforsikring;
import com.rtbeb.model.validation.BoligValidator;
import com.rtbeb.model.validation.ForsikringValidator;
import com.rtbeb.model.validation.InnboForsikringValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RedigerInnboforsikringController extends RedigerforsikringController {

    Innboforsikring opprinneligInnboforsikring;

    public RedigerInnboforsikringController(Kunde kunde, Innboforsikring innboforsikring) {
        super(kunde);
        this.opprinneligInnboforsikring = innboforsikring;
    }

    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtPostnummer;
    @FXML
    private TextField txtByggeår;
    @FXML
    private ChoiceBox<Bolig.Boligtype> cBoxBoligtype;
    @FXML
    private ChoiceBox<Bolig.Byggemateriale> cBoxByggemateriale;
    @FXML
    private ChoiceBox<Bolig.Standard> cBoxStandard;
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
    private void redigerInnboforsikring(ActionEvent event) {

        //Oppretter ny forsikring for validering.
        Innboforsikring redigertInnboforsikring = generateInnboforsikring();

        //Hvis forsikringen er gyldig, oppdateres den opprinnelige forsikringen.
        if (InnboForsikringValidator.innboForsikringIsValid(redigertInnboforsikring)){
            updateOpprinneligforsikring();
        }
    }

    /**
     * Oppdaterer opprinnelig forsikring til de redigerte feltene.
     */
    private void updateOpprinneligforsikring(){
        opprinneligInnboforsikring.getBolig().setAdresse( txtAdresse.getText() );
        opprinneligInnboforsikring.getBolig().setPostnummer( txtPostnummer.getText() );
        opprinneligInnboforsikring.getBolig().setByggeår( txtByggeår.getText() );
        opprinneligInnboforsikring.getBolig().setBoligtype( cBoxBoligtype.getValue() );
        opprinneligInnboforsikring.getBolig().setByggemateriale( cBoxByggemateriale.getValue() );
        opprinneligInnboforsikring.getBolig().setStandard( cBoxStandard.getValue() );
        opprinneligInnboforsikring.getBolig().setStørrelse( txtStørrelse.getText() );
        opprinneligInnboforsikring.setForsikringspremie( Integer.parseInt(txtForsikringspremie.getText()) );
        opprinneligInnboforsikring.setForsikringsbeløp( Integer.parseInt(txtForsikringsbeløp.getText()) );
        opprinneligInnboforsikring.setForsikringsbetingelser( txtBetingelser.getText() );
        opprinneligInnboforsikring.setForsikringsbeløpBygning( Integer.parseInt(txtForsikringsbeløpBygning.getText()) );
        opprinneligInnboforsikring.setForsikringsbeløpInnbo( Integer.parseInt(txtForsikringsbeløpInnbo.getText()) );
    }

    /**
     * Generer en innboforsikring basert på de fylte felt.
     * @return en uvalidert Innboforsikring
     * @throws NumberFormatException dersom numeriske felt står tomme.
     */
    private Innboforsikring generateInnboforsikring() throws NumberFormatException{

        int forsikringspremie = Integer.parseInt(txtForsikringspremie.getText());
        int forsikringsbeløp = Integer.parseInt(txtForsikringsbeløp.getText());
        String betingelser = txtBetingelser.getText();
        String adresse = txtAdresse.getText();
        String postnummer = txtPostnummer.getText();
        String byggeår = txtByggeår.getText();
        Bolig.Boligtype boligtype = cBoxBoligtype.getSelectionModel().getSelectedItem();
        Bolig.Byggemateriale byggemateriale = cBoxByggemateriale.getSelectionModel().getSelectedItem();
        Bolig.Standard standard = cBoxStandard.getSelectionModel().getSelectedItem();
        String størrelse = txtStørrelse.getText();
        int forsikringssbeløpBygning = Integer.parseInt(txtForsikringsbeløpBygning.getText());
        int forsikringsbeløpInnbo = Integer.parseInt(txtForsikringsbeløpInnbo.getText());

        Bolig bolig = new Bolig(adresse, postnummer, byggeår, boligtype,
                byggemateriale, standard, størrelse);

        return new Innboforsikring(opprinneligInnboforsikring.getBrukstype(), forsikringspremie, forsikringsbeløp, betingelser,
                bolig, forsikringssbeløpBygning,forsikringsbeløpInnbo);
    }

    @FXML
    private void goToForsikringsdetaljerTab(ActionEvent event) {

        //Får tak i TabPane
        TabPane tabPane = (TabPane) btnNeste.getParent().getParent().getParent();

        //Velger andre tab
        tabPane.getSelectionModel().select(1);
    }

    @FXML
    private void goToBoligTab(ActionEvent event) {

        //Får tak i TabPane
        TabPane tabPane = (TabPane) btnNeste.getParent().getParent().getParent();

        //Velger andre tab
        tabPane.getSelectionModel().select(0);
    }

    @FXML
    private void lukkVindu(ActionEvent event) {
        Stage stage = (Stage) btnNeste.getScene().getWindow();
        stage.close();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Fyller alle tekstfelt med data fra forsikringen som skal redigeres.
        initTextFields();

        //Henter lovlige verdier for Brukstype, Byggemateriale og Standard og setter disse inn i ChoiceBox-ene.
        cBoxBoligtype.getItems().setAll(Bolig.Boligtype.values());
        cBoxByggemateriale.getItems().setAll(Bolig.Byggemateriale.values());
        cBoxStandard.getItems().setAll(Bolig.Standard.values());

        TextField[] numericFields = {txtPostnummer, txtByggeår, txtStørrelse, txtForsikringsbeløpBygning,
                txtForsikringsbeløpInnbo, txtForsikringspremie, txtForsikringsbeløp};
        addNumericListeners(numericFields);
    }

    /**
     * Fyller alle tekstfelt med data fra forsikringen som skal redigeres.
     */
    private void initTextFields(){
        txtAdresse.setText(opprinneligInnboforsikring.getBolig().getAdresse());
        txtPostnummer.setText(opprinneligInnboforsikring.getBolig().getPostnummer());
        txtByggeår.setText(opprinneligInnboforsikring.getBolig().getByggeår());
        cBoxBoligtype.getSelectionModel().select(opprinneligInnboforsikring.getBolig().getBoligtype());
        cBoxByggemateriale.getSelectionModel().select(opprinneligInnboforsikring.getBolig().getByggemateriale());
        cBoxStandard.getSelectionModel().select(opprinneligInnboforsikring.getBolig().getStandard());
        txtStørrelse.setText(opprinneligInnboforsikring.getBolig().getStørrelse());
        txtForsikringspremie.setText(opprinneligInnboforsikring.getForsikringspremie().toString());
        txtBetingelser.setText(opprinneligInnboforsikring.getForsikringsbetingelser());
        txtForsikringsbeløpBygning.setText(opprinneligInnboforsikring.getForsikringssbeløpBygning().toString());
        txtForsikringsbeløpInnbo.setText(opprinneligInnboforsikring.getForsikringsbeløpInnbo().toString());
        updateForsikringsbeløp();
    }


    //------------VALIDERING---------------//
    @FXML
    private void adresseChanged(InputEvent event) {
        String adresse = txtAdresse.getText();

        if (!BoligValidator.adresseIsValid(adresse)) {
            FieldStyler.setInvalidStyle(txtAdresse);
        } else {
            FieldStyler.setValidStyle(txtAdresse);
        }
    }

    @FXML
    private void postnummerChanged() {
        String postnummer = txtPostnummer.getText();

        if (!BoligValidator.postnummerIsValid(postnummer)) {
            FieldStyler.setInvalidStyle(txtPostnummer);
        } else {
            FieldStyler.setValidStyle(txtPostnummer);
        }
    }

    @FXML
    private void byggeårChanged() {
        String byggeår = txtByggeår.getText();

        if (!BoligValidator.byggeårIsValid(byggeår)) {
            FieldStyler.setInvalidStyle(txtByggeår);
        } else {
            FieldStyler.setValidStyle(txtByggeår);
        }
    }

    @FXML
    private void størrelseChanged() {
        String størrelse = txtStørrelse.getText();

        if (!BoligValidator.størrelseIsValid(størrelse)) {
            FieldStyler.setInvalidStyle(txtStørrelse);
        } else {
            FieldStyler.setValidStyle(txtStørrelse);
        }
    }

    @FXML
    private void forsikringsbeløpBygningChanged() {

        String forsikringsbeløpBygning = txtForsikringsbeløpBygning.getText();

        if (!InnboForsikringValidator.forsikringsbeløpBygningIsValid(forsikringsbeløpBygning)) {
            FieldStyler.setInvalidStyle(txtForsikringsbeløpBygning);
        } else {
            FieldStyler.setValidStyle(txtForsikringsbeløpBygning);
            updateForsikringsbeløp();
        }

    }

    /**
     * Metoden oppdaterer forsikringsbeløpet slik at det blir satt til summen av forsikringsbeløpet for innbo og bygning.
     */
    private void updateForsikringsbeløp() {

        String forsikringbeløpInnbo = txtForsikringsbeløpInnbo.getText();
        String forsikringsbeløpBygning = txtForsikringsbeløpBygning.getText();

        try {

            int forsikringbeløpInnboParsed = Integer.parseInt(forsikringbeløpInnbo);
            int forsikringsbeløpBygningParsed = Integer.parseInt(forsikringsbeløpBygning);

            int forsikringsbeløp = forsikringbeløpInnboParsed + forsikringsbeløpBygningParsed;
            txtForsikringsbeløp.setText(String.valueOf(forsikringsbeløp));

        } catch (NumberFormatException e) { /*Dette oppstår kun hvis et av feltene ikke er fylt ut.*/}
    }

    @FXML
    private void forsikringsbeløpInnboChanged() {

        String forsikringsbeløpInnbo = txtForsikringsbeløpInnbo.getText();

        if (!InnboForsikringValidator.forsikringsbeløpInnboIsValid(forsikringsbeløpInnbo)) {
            FieldStyler.setInvalidStyle(txtForsikringsbeløpInnbo);
        } else {
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
