package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrerNyForsikringController implements Initializable {

    Kunde kunde;

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
