package com.rtbeb.controller.registrering;

import com.rtbeb.controller.helper.FXMLStyler;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.forsikring.Bolig.Innboforsikring;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroller for valg av forsikringen som skal registreres.
 * @author Rany Tarek Bouorm - s236210
 */
public class RegistrerNyForsikringController implements Initializable {

    private Kunde kunde;

    public RegistrerNyForsikringController(Kunde valgtkunde){
        this.kunde = valgtkunde;
    }

    @FXML
    private Label lblTitle;

    @FXML
    private void båtForsikringButtonClicked(ActionEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistrerBåtforsikring.fxml"));
            RegistrerBåtforsikringController controller = new RegistrerBåtforsikringController(kunde);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            FXMLStyler.addDefaultStyleSheet(scene);
            Stage stage = (Stage) lblTitle.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void innboforsikringButtonClicked(ActionEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistrerInnboforsikring.fxml"));
            RegistrerInnboForsikringController controller = new RegistrerInnboForsikringController(kunde, Innboforsikring.Brukstype.HELÅRSBOLIG);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            FXMLStyler.addDefaultStyleSheet(scene);
            Stage stage = (Stage) lblTitle.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void fritidsboligforsikringButtonClicked(ActionEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistrerInnboforsikring.fxml"));
            RegistrerInnboForsikringController controller = new RegistrerInnboForsikringController(kunde, Innboforsikring.Brukstype.FRITIDSBOLIG);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            FXMLStyler.addDefaultStyleSheet(scene);
            Stage stage = (Stage) lblTitle.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void reiseforsikringButtonClicked(ActionEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistrerReiseforsikring.fxml"));
            RegistrerReiseforsikringController controller = new RegistrerReiseforsikringController(kunde);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            FXMLStyler.addDefaultStyleSheet(scene);
            Stage stage = (Stage) lblTitle.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
