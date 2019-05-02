package com.rtbeb.controller.redigering;

import com.rtbeb.controller.helper.FieldStyler;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.forsikring.Reise.Reiseforsikring;
import com.rtbeb.model.validation.ForsikringValidator;
import com.rtbeb.model.validation.ReiseforsikringValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroller for redigering av reiseforsikring
 * @author Rany Tarek Bouorm - s236210
 */
public class RedigerReiseforsikringController extends RedigerforsikringController {

    private Reiseforsikring opprinneligforsikring;

    public RedigerReiseforsikringController(Kunde kunde, Reiseforsikring opprinneligforsikring) {
        super(kunde);
        this.opprinneligforsikring = opprinneligforsikring;
    }

    @FXML
    private
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
    private void goToForsikringsdetaljerTab(ActionEvent event) {

        //Får tak i TabPane
        TabPane tabPane = (TabPane) btnNeste.getParent().getParent().getParent();

        //Velger andre tab
        tabPane.getSelectionModel().select(1);
    }

    @FXML
    private void goToReiseTab() {

        //Får tak i TabPane
        TabPane tabPane = (TabPane) btnNeste.getParent().getParent().getParent();

        //Velger første
        tabPane.getSelectionModel().select(0);
    }

    @FXML
    private void lukkVindu(ActionEvent event) {
        Stage stage = (Stage) btnNeste.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void redigerReiseforsikring(ActionEvent event) {

        try {
            Reiseforsikring reiseforsikring = generateReiseforsikring();

            if (reiseforsikring.isValid()) {

                updateOpprinneligforsikring();

                Stage stage = (Stage) btnNeste.getScene().getWindow();
                stage.close();
            } else {
                generateAlert("Kunne ikke redigere forsikring:\nFyll inn alle felt eller sjekk rød-markerte felt.");
            }
        } catch (NumberFormatException e) {

            //Fanger NumberFormatException som oppstår dersom numeriske felt står tomme.
            generateAlert("Kunne ikke redigere forsikring:\nFyll inn alle felt eller sjekk rød-markerte felt.");

        }
    }

    /**
     * @return Returnerer en uvalidert reiseforsikring basert på inputfeltene i registreringsskjemaet.
     * @throws NumberFormatException dersom numeriske felt står tomme.
     */
    private Reiseforsikring generateReiseforsikring() throws NumberFormatException {

        int forsikringspremie = Integer.parseInt(txtForsikringspremie.getText());
        int forsikringsbeløp = Integer.parseInt(txtForsikringsbeløp.getText());
        String forsikringsbetingelser = txtBetingelser.getText();
        String forsikringsområde = txtForsikringsområde.getText();
        int forsikringssum = Integer.parseInt(txtForsikringssum.getText());

        return new Reiseforsikring(forsikringspremie, forsikringsbeløp,
                forsikringsbetingelser, forsikringsområde, forsikringssum);
    }

    private void updateOpprinneligforsikring() {

        opprinneligforsikring.setForsikringspremie(Integer.parseInt(txtForsikringspremie.getText()));
        opprinneligforsikring.setForsikringsbeløp(Integer.parseInt(txtForsikringsbeløp.getText()));
        opprinneligforsikring.setForsikringsbetingelser(txtBetingelser.getText());
        opprinneligforsikring.setForsikringsområde(txtForsikringsområde.getText());
        opprinneligforsikring.setForsikringssum(Integer.parseInt(txtForsikringssum.getText()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initTextFields();

        TextField[] numericFields = {txtForsikringspremie, txtForsikringsbeløp, txtForsikringssum};
        addNumericListeners(numericFields);
    }

    private void initTextFields() {

        txtForsikringspremie.setText(opprinneligforsikring.getForsikringspremie().toString());
        txtForsikringsbeløp.setText(opprinneligforsikring.getForsikringsbeløp().toString());
        txtBetingelser.setText(opprinneligforsikring.getForsikringsbetingelser());
        txtForsikringsområde.setText(opprinneligforsikring.getForsikringsområde());
        txtForsikringssum.setText(opprinneligforsikring.getForsikringssum().toString());
    }

    //-----------VALIDERING---------//

    @FXML
    private void betingelserChanged(InputEvent event) {
        String betingelser = txtBetingelser.getText();

        if (!ForsikringValidator.forsikringsbetingelserIsValid(betingelser)) {
            FieldStyler.setInvalidStyle(txtBetingelser);
        } else {
            FieldStyler.setValidStyle(txtBetingelser);
        }
    }

    @FXML
    private void forsikringsbeløpChanged(InputEvent event) {
        String forsikringsbeløp = txtForsikringsbeløp.getText();

        if (!ForsikringValidator.forsikringsbelopIsValid(forsikringsbeløp)) {
            FieldStyler.setInvalidStyle(txtForsikringsbeløp);
        } else {
            FieldStyler.setValidStyle(txtForsikringsbeløp);
        }
    }

    @FXML
    private void forsikringspremieChanged(InputEvent event) {
        String forsikringspremie = txtForsikringspremie.getText();

        if (!ForsikringValidator.forsikringspremieIsValid(forsikringspremie)) {
            FieldStyler.setInvalidStyle(txtForsikringspremie);
        } else {
            FieldStyler.setValidStyle(txtForsikringspremie);
        }

    }

    @FXML
    private void forsikringsområdeChanged(InputEvent event) {
        String forsikringsområde = txtForsikringsområde.getText();

        if (!ReiseforsikringValidator.forsikringsområdeIsValid(forsikringsområde)) {
            FieldStyler.setInvalidStyle(txtForsikringsområde);
        } else {
            FieldStyler.setValidStyle(txtForsikringsområde);
        }
    }

    @FXML
    private void forsikringssumChanged(InputEvent event) {
        String forsikringssum = txtForsikringssum.getText();

        if (!ReiseforsikringValidator.forsikringssumIsValid(forsikringssum)) {
            FieldStyler.setInvalidStyle(txtForsikringssum);
        } else {
            FieldStyler.setValidStyle(txtForsikringssum);
        }
    }
}
