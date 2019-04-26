package com.rtbeb.controller.forsikring.redigering;

import com.rtbeb.controller.helper.FieldStyler;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.base.forsikring.Båt.Båt;
import com.rtbeb.model.base.forsikring.Båt.Båtforsikring;
import com.rtbeb.model.base.forsikring.Båt.Eier;
import com.rtbeb.model.validation.BåtValidator;
import com.rtbeb.model.validation.BåtforsikringValidator;
import com.rtbeb.model.validation.EierValidator;
import com.rtbeb.model.validation.ForsikringValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class RedigerBåtforsikringController extends RedigerforsikringController {

    Kunde kunde;
    Båtforsikring opprinneligforsikring;

    public RedigerBåtforsikringController(Kunde kunde, Båtforsikring opprinneligforsikring){
        super(kunde);
        this.opprinneligforsikring = opprinneligforsikring;
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
    private void redigerBåtforsikring(ActionEvent event){


        try {
            Båtforsikring redigertBåtforsikring = generateBåtforsikring();

            if( redigertBåtforsikring.isValid() ) {
                updateOpprinneligforsikring();
            } else{
                generateAlert("Kunne ikke redigere forsikring:\nFyll inn alle felt eller sjekk rød-markerte felt.");
            }
            //Lukk stage hvis forsikring blir registrert OK.
            Stage stage = (Stage) btnNeste.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            generateAlert("Kunne ikke redigere forsikring:\nFyll inn alle felt eller sjekk rød-markerte felt.");
        }
    }

    /**
     * @return Returnerer en båtforsikring basert på innfylte felt. Forsikringen returneres uvalidert.
     */
    private Båtforsikring generateBåtforsikring() throws NumberFormatException {
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

    private void updateOpprinneligforsikring(){
        opprinneligforsikring.getBåt().setRegistreringsnummer( txtRegistreringsnummer.getText() );
        opprinneligforsikring.getBåt().setMerke( txtMerke.getText() );
        opprinneligforsikring.getBåt().setModell( txtModell.getText() );
        opprinneligforsikring.getBåt().setLengde( txtLengde.getText() );
        opprinneligforsikring.getBåt().setÅrsmodell( txtÅrsmodell.getText() );
        opprinneligforsikring.getBåt().setMotorinfo( txtMotorinfo.getText() );
        opprinneligforsikring.getBåt().getEier().setFornavn( txtFornavn.getText() );
        opprinneligforsikring.getBåt().getEier().setEtternavn(txtEtternavn.getText() );
        opprinneligforsikring.getBåt().getEier().setFødselsdato( dateFødselsdato.getValue() );
        opprinneligforsikring.setForsikringspremie( Integer.parseInt(txtForsikringspremie.getText()) );
        opprinneligforsikring.setForsikringsbeløp( Integer.parseInt(txtForsikringsbeløp.getText()) );
        opprinneligforsikring.setForsikringsbetingelser( txtBetingelser.getText() );
    }

    private void generateAlert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Registreringsfeil");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initFields();

        TextField[] numericFields = {txtForsikringspremie, txtForsikringsbeløp, txtLengde, txtÅrsmodell};
        addNumericListeners(numericFields);
    }

    /**
     * Setter opp tekstfelt i dette viewet til data fra den opprinnelige forsikringen.
     */
    private void initFields(){
        txtRegistreringsnummer.setText( opprinneligforsikring.getBåt().getRegistreringsnummer() );
        txtMerke.setText( opprinneligforsikring.getBåt().getMerke() );
        txtModell.setText( opprinneligforsikring.getBåt().getModell() );
        txtLengde.setText( opprinneligforsikring.getBåt().getLengde() );
        txtÅrsmodell.setText( opprinneligforsikring.getBåt().getÅrsmodell() );
        txtMotorinfo.setText( opprinneligforsikring.getBåt().getMotorinfo() );
        txtFornavn.setText( opprinneligforsikring.getBåt().getEier().getFornavn() );
        txtEtternavn.setText( opprinneligforsikring.getBåt().getEier().getEtternavn() );
        dateFødselsdato.setValue( opprinneligforsikring.getBåt().getEier().getFødselsdato() );
        txtForsikringspremie.setText( opprinneligforsikring.getForsikringspremie().toString() );
        txtForsikringsbeløp.setText( opprinneligforsikring.getForsikringsbeløp().toString() );
        txtBetingelser.setText( opprinneligforsikring.getForsikringsbetingelser() );

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
