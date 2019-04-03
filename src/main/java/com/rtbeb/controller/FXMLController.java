package com.rtbeb.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;

    @FXML
    private TableView<Kunde> tableKunder;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        //Lag ny kunde og test tableview.
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Kunderegister kunderegister = new Kunderegister();

        tableKunder.setItems(kunderegister.getAlleKunder());

        //Kolonne for fornavn
        TableColumn<Kunde,String> fornavnColumn = new TableColumn<>("Fornavn");
        fornavnColumn.setCellValueFactory(new PropertyValueFactory("fornavn"));

        //Kolonne for etternavn
        TableColumn<Kunde,String> etternavnColumn = new TableColumn<>("Etternavn");
        etternavnColumn.setCellValueFactory(new PropertyValueFactory("etternavn"));

        //Kolonne for fakturaadresse
        TableColumn<Kunde,String> fakturaadresseColumn = new TableColumn<>("Fakturaadresse");
        fakturaadresseColumn.setCellValueFactory(new PropertyValueFactory("fakturaadresse"));

        //Kolonne for postnummer
        TableColumn<Kunde,String> postnummerColumn = new TableColumn<>("Postnr");
        postnummerColumn.setCellValueFactory(new PropertyValueFactory("postnummer"));

        //Kolonne for



        tableKunder.getColumns().setAll(fornavnColumn,etternavnColumn, fakturaadresseColumn, postnummerColumn);



    }    
}
