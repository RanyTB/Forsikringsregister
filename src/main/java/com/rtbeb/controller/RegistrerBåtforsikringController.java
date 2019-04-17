package com.rtbeb.controller;

import com.rtbeb.model.base.Kunde;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;

public class RegistrerBåtforsikringController {

    Kunde kunde;

    public RegistrerBåtforsikringController(Kunde kunde){
        this.kunde = kunde;
    }

    @FXML
    Button btnNeste;

    @FXML
    private void goToEierTabButton(ActionEvent event){

        //Får tak i TabPane
        TabPane tabPane = (TabPane)btnNeste.getParent().getParent().getParent();

        //Velger andre tab
        tabPane.getSelectionModel().select(1);
    }

}
