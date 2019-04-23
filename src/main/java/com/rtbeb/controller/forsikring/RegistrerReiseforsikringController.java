package com.rtbeb.controller.forsikring;

import com.rtbeb.controller.helper.FieldStyler;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.base.forsikring.Reise.Reiseforsikring;
import com.rtbeb.model.validation.ForsikringValidator;
import com.rtbeb.model.validation.ReiseforsikringValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrerReiseforsikringController extends RegistrerForsikringController implements Initializable {

    public RegistrerReiseforsikringController(Kunde kunde){
        super(kunde);
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

    @FXML
    private void betingelserChanged(InputEvent event) {
        String betingelser = txtBetingelser.getText();

        if(!ForsikringValidator.forsikringsbetingelserIsValid(betingelser)){
            FieldStyler.setInvalidStyle(txtBetingelser);
        } else{
            FieldStyler.setValidStyle(txtBetingelser);
        }
    }

    @FXML
    private void forsikringsbeløpChanged(InputEvent event) {
        String forsikringsbeløp = txtForsikringsbeløp.getText();

        if(!ForsikringValidator.forsikringsbelopIsValid(forsikringsbeløp)){
            FieldStyler.setInvalidStyle(txtForsikringsbeløp);
        } else{
            FieldStyler.setValidStyle(txtForsikringsbeløp);
        }
    }

    @FXML
    private void forsikringspremieChanged(InputEvent event) {
        String forsikringspremie = txtForsikringspremie.getText();

        if(!ForsikringValidator.forsikringspremieIsValid(forsikringspremie)){
            FieldStyler.setInvalidStyle(txtForsikringspremie);
        } else{
            FieldStyler.setValidStyle(txtForsikringspremie);
        }

    }

    @FXML
    private void forsikringsområdeChanged(InputEvent event){
        String forsikringsområde = txtForsikringsområde.getText();

        if(!ReiseforsikringValidator.forsikringsområdeIsValid(forsikringsområde)){
            FieldStyler.setInvalidStyle(txtForsikringsområde);
        } else{
            FieldStyler.setValidStyle(txtForsikringsområde);
        }
    }

    @FXML
    private void forsikringssumChanged(InputEvent event){
        String forsikringssum = txtForsikringssum.getText();

        if(!ReiseforsikringValidator.forsikringssumIsValid(forsikringssum)){
            FieldStyler.setInvalidStyle(txtForsikringssum);
        } else{
            FieldStyler.setValidStyle(txtForsikringssum);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TextField[] numericFields = {txtForsikringspremie, txtForsikringsbeløp, txtForsikringssum};
        addNumericListeners(numericFields);
    }

}
