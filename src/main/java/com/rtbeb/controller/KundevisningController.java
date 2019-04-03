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
public class KundevisningController implements Initializable {

    /*Deklarerer kunderegister her, da disse blir kjørt før initialize. Foreløpig gjøres dette for
    å teste endring av data, og om dette vil oppdatere tabellen.
     */
    private Kunderegister kunderegister;

    @FXML
    TableColumn<Kunde,Integer> forsikringsnummerColumn;

    @FXML
    TableColumn<Kunde,String> fornavnColumn;

    @FXML
    TableColumn<Kunde,String> etternavnColumn;

    @FXML
    TableColumn<Kunde, String> dateColumn;


    @FXML
    private Label label;

    @FXML
    private TableView<Kunde> tableKunder;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        //Lag ny kunde og test tableview.
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        //Test for om data blir endret i tabellen når de endres i kundeobjektet:
        kunderegister.getAlleKunder().get(0).setFornavn("Test");
        kunderegister.getAlleKunder().get(1).setEtternavn("Test");

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        kunderegister = new Kunderegister();

        tableKunder.setItems(kunderegister.getAlleKunder());

        //Kolonne for forsikringsnummer
        forsikringsnummerColumn.setCellValueFactory(new PropertyValueFactory<>("forsikringsnummer"));
        tableKunder.getSortOrder().add(forsikringsnummerColumn);

        //Kolonne for fornavn
        fornavnColumn.setCellValueFactory(new PropertyValueFactory<>("fornavn"));

        //Kolonne for etternavn
        etternavnColumn.setCellValueFactory(new PropertyValueFactory<>("etternavn"));

        //Kolonne for dato
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("kundeOpprettelsesDato"));




    }
}
