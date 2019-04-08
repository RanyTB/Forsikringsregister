package com.rtbeb.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private void openFile(){
        //TODO implementer FileChooser her
    }

    @FXML
    private void saveFile(){
        //TODO implementer FileChooser her
    }


    @FXML
    private void testButtonAction(ActionEvent event) {
        //Lag ny kunde og test tableview.
        System.out.println("You clicked me!");

        //Test for om data blir endret i tabellen når de endres i kundeobjektet:
        kunderegister.getKundeliste().get(0).setFornavn("Test");
        kunderegister.getKundeliste().get(1).setEtternavn("Test");
    }

    @FXML
    private void opprettNyKunde(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/KundeopprettelseOgRedigering.fxml"));
            Scene kundeScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(kundeScene);
            newStage.show();

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void visValgtKunde(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Kundeforhold.fxml"));
            Scene kundeForholdScene = new Scene(root);
            //Bruker tableKunder som node for å få tak i vinduet.
            Stage stage = (Stage) tableKunder.getScene().getWindow();
            stage.setScene(kundeForholdScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Kunderegister kunderegister = Kunderegister.getInstance();

        tableKunder.setItems(kunderegister.getKundeliste());

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
