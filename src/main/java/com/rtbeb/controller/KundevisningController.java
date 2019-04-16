package com.rtbeb.controller;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;
import com.rtbeb.model.filemanagement.Context;
import com.rtbeb.model.filemanagement.Jobj;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class KundevisningController implements Initializable {

    /*Deklarerer kunderegisteret her*/
    private Kunderegister kunderegister;

    @FXML
    TextField txtSearch;

    @FXML
    TableColumn<Kunde,Integer> forsikringsnummerColumn;

    @FXML
    TableColumn<Kunde,String> fornavnColumn;

    @FXML
    TableColumn<Kunde,String> etternavnColumn;

    @FXML
    TableColumn<Kunde,String> adresseColumn;

    @FXML
    TableColumn<Kunde, String> dateColumn;

    @FXML
    private TableView<Kunde> tableKunder;

    @FXML
    private void openFile() throws  Exception, IOException{
        //TODO implementer FileChooser her
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());

        String extension = file.getName().substring(file.getName().lastIndexOf("."));

        Context context;
        if (extension.equals(".jobj")){
            context = new Context(new Jobj());
            context.readStrategy(file.getPath());

        } else if (extension.equals(".csv")){

        } else {
            System.out.println("Feil filtype");
        }


    }

    @FXML
    private void saveFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilterJOBJ
                = new FileChooser.ExtensionFilter(".jobj format", "*.jobj");
        FileChooser.ExtensionFilter extensionFilterCSV
                = new FileChooser.ExtensionFilter(".csv format", "*.csv");

        fileChooser.getExtensionFilters().addAll(extensionFilterJOBJ, extensionFilterCSV);
        File file = fileChooser.showSaveDialog(new Stage());

        String extension = file.getName().substring(file.getName().lastIndexOf("."));

        //test
        System.out.println("Filendelse: " + extension);

        Context context;
        if (extension.equals(".jobj")){
            context = new Context(new Jobj());
            context.writeStrategy(file.getPath());

        } else if (extension.equals(".csv")){

        } else {
            System.out.println("Feil filtype");
        }

        //Context context = new Context(new Jobj());
        //context.fileStrategy(file.getPath());

    }


    @FXML
    private void testButtonAction(ActionEvent event) {
        //TODO Implementer denne som en test og fjern den herfra.
        //Lag ny kunde og test tableview.
        System.out.println("You clicked me!");

        //Test for om data blir endret i tabellen når de endres i kundeobjektet:
        kunderegister.getKundeliste().get(0).setFornavn("Test");
        kunderegister.getKundeliste().get(1).setEtternavn("Test");
    }

    @FXML
    private void opprettNyKunde(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/NyKunde.fxml"));
            Scene kundeScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(kundeScene);
            newStage.show();

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickOnTable(MouseEvent event){

        //TODO kall dette noe annet enn actualevent?
        //Dersom det blir oppdaget dobbeltklikk på tabellen, åpnes kundeforhold for markert kunde.
        tableKunder.setOnMouseClicked((MouseEvent actualevent) -> {
            if (actualevent.getButton().equals(MouseButton.PRIMARY) && actualevent.getClickCount() == 2){
                visValgtKunde(new ActionEvent());
            }
        });
    }

    @FXML
    private void visValgtKunde(ActionEvent event){

        try {

            Kunde valgtKunde = tableKunder.getSelectionModel().getSelectedItem();

            if (valgtKunde != null){
                /*
                Kilder/inspirasjon for sending av parametre til kontroller:
                https://github.com/jalopezsuarez/javafx-screens/blob/master/javafx-screens/src/com/vemovi/javafx/NavigationController.java
                https://stackoverflow.com/questions/14187963/passing-parameters-javafx-fxml
                 */

                //Oppretter en ny kontroller-instans og setter kunde til den valgte kunden.
                KundeforholdController kundeforholdController = new KundeforholdController(valgtKunde);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Kundeforhold.fxml"));

                //Setter loaderens kontroller til kontroller-instansen. fx:controller er ikke satt i FXML-filen.
                loader.setController(kundeforholdController);

                //Loader FXML-hierarkiet
                Parent root = loader.load();

                //Oppretter ny scene
                Scene scene = new Scene(root);

                //Henter nåværende vindu
                Stage stage = (Stage) tableKunder.getScene().getWindow();

                //Setter ny scene på nåværende vindu.
                stage.setScene(scene);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Henter Singleton instansen av kunderegisteret
        kunderegister = Kunderegister.getInstance();

        //TODO Fjern gammel setItems som har blitt erstattet av FilteredListen under.
        //Setter data for tabellen til kunderegisterets liste over kunder
        //tableKunder.setItems(kunderegister.getKundeliste());

        //Wrapper kundeListen fra kunderegisteret (observablelisten) i en FilteredList for å støtte søkefunksjonalitet.
        FilteredList<Kunde> filteredList = new FilteredList<>(kunderegister.getKundeliste());

        //Kolonne for forsikringsnummer
        forsikringsnummerColumn.setCellValueFactory(new PropertyValueFactory<>("forsikringsnummer"));
        tableKunder.getSortOrder().add(forsikringsnummerColumn);

        //Kolonne for fornavn
        fornavnColumn.setCellValueFactory(new PropertyValueFactory<>("fornavn"));

        //Kolonne for etternavn
        etternavnColumn.setCellValueFactory(new PropertyValueFactory<>("etternavn"));

        //Kolonne for adresse
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("fakturaadresse"));

        //Kolonne for dato
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("kundeOpprettelsesDato"));

        /*-------------Søkefunksjonalitet----------------
        Kilder:
        https://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
        https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableView.html
        */

        //Setter opp en listener for søkefeltet (txtSearch), slik at filteret oppdateres hver gang man skriver noe nytt.
        txtSearch.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredList.setPredicate(kunde -> {

                //Vis alt dersom søkefeltet er tomt:
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                //Teksten som skal søkes på omgjøres til lowercase.
                String lowerCaseFilter = newValue.toLowerCase();

                //Sammenligner lowerCaseFilter med fornavn, etternavn og forsikringsnummer.
                if(kunde.getFornavn().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (kunde.getEtternavn().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(kunde.getFakturaadresse().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(Long.toString(kunde.getForsikringsnummer()).contains(lowerCaseFilter)){
                    return true;
                }
                return false; //Ingen match
            });
        });

        //Wrapper filteredList i en SortedList for å kunne sortere etter de forskjellige kolonnene.
        SortedList<Kunde> sortedList = new SortedList<>(filteredList);
        //Binder sortedlist sin comparator til tableKunder sin comparator.
        sortedList.comparatorProperty().bind(tableKunder.comparatorProperty());
        //Setter tabellens data-source til sortedList.
        tableKunder.setItems(sortedList);


    }

}
