package com.rtbeb.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class KundeforholdController {

    //Kontrolleren har en tilbakeknapp, og holder på forrige Scene i denne variabelen.
    Scene previousScene;

    void setPreviousScene(Scene scene){
        this.previousScene = scene;
    }

    @FXML
    Button backButton;

    @FXML
    private void handleBackButtonClicked(ActionEvent event){

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/kundevisning.fxml"));
            Scene previousScene = new Scene(root);

/*          Bruker en node for å få tak i vinduet.
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();*/

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(previousScene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
