package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrerNyForsikringController {

    Kunde kunde;

    public RegistrerNyForsikringController(Kunde valgtkunde){
        this.kunde = valgtkunde;
    }

    @FXML
    private Label lblTitle;

    @FXML
    private void båtForsikringButtonClicked(){

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
}
