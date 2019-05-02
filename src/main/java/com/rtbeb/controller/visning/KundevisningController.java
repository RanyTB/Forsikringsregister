package com.rtbeb.controller.visning;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;
import com.rtbeb.model.filemanagement.exception.InvalidFileTypeException;
import com.rtbeb.model.filemanagement.read.RegisterReader;
import com.rtbeb.model.filemanagement.write.RegisterWriter;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Entry-point for programmet. Viser en oversikt over alle kunder, tillater sletting og redigering av kunder
 * og dobbeltklikk på kunder for å vise kundeforholdet.
 *
 * @author Rany Tarek Bouorm - s236210
 * @author Eirik Bøyum - -saveFile() og openFile()
 */
public class KundevisningController implements Initializable {

    // Kunderegisteret representert.
    private Kunderegister kunderegister;

    @FXML
    TextField txtSearch;

    @FXML
    TableColumn<Kunde, Integer> forsikringsnummerColumn;

    @FXML
    TableColumn<Kunde, String> fornavnColumn;

    @FXML
    TableColumn<Kunde, String> etternavnColumn;

    @FXML
    TableColumn<Kunde, String> adresseColumn;

    @FXML
    TableColumn<Kunde, String> dateColumn;

    @FXML
    private TableView<Kunde> tableKunder;

    @FXML
    private MenuItem btnÅpneFil;
    @FXML
    private MenuItem btnLagreTilFil;
    @FXML
    private Button btnOpprettNyKunde;
    @FXML
    private Button btnVisValgtKunde;

    //Deklarerer variabel for lesetråd.
    Thread readThread;

    /**
     * @author Eirik Bøyum
     *
     */
    @FXML
    private void openFile() {
        //TODO implementer FileChooser her
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {

            try {
                //En ny tråd settes opp med to Runnable funksjoner som blir kalt når tråden er ferdig med å kjøre.
                Task readTask = new RegisterReader(file.getPath(), this::generateFileLoadedAlert, this::activateButtons);
                Thread readThread = new Thread(readTask);

                //Dersom tråden feiler, hentes exception-meldingen fra tråden og genererer en feilmelding.
                readTask.setOnFailed(workerStateEvent -> readFailedHandler(readTask.getException().getMessage()));
                deactivateButtons();
                readThread.start();

            } catch (InvalidFileTypeException e) {

                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Deaktiverer knapper og menyikoner.
     */
    private void deactivateButtons() {
        btnÅpneFil.setDisable(true);
        btnLagreTilFil.setDisable(true);
        btnOpprettNyKunde.setDisable(true);
        btnVisValgtKunde.setDisable(true);
    }

    /**
     * Aktiverer knapper og menyikoner.
     */
    private void activateButtons() {
        btnÅpneFil.setDisable(false);
        btnLagreTilFil.setDisable(false);
        btnOpprettNyKunde.setDisable(false);
        btnVisValgtKunde.setDisable(false);
    }

    private void generateFileLoadedAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Registeret ble lastet inn.");
        alert.show();
    }

    private void readFailedHandler(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Feil ved filinnlasting.");
        alert.setContentText(errorMessage + "\nFilinnlasting avbrytes.");
        activateButtons();
        alert.showAndWait();
    }

    /**
     * @author Eirik Bøyum
     */
    @FXML
    private void saveFile(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilterJOBJ
                = new FileChooser.ExtensionFilter(".jobj format", "*.jobj");
        FileChooser.ExtensionFilter extensionFilterCSV
                = new FileChooser.ExtensionFilter(".csv format", "*.csv");

        fileChooser.getExtensionFilters().addAll(extensionFilterJOBJ, extensionFilterCSV);
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try {

                Thread saveThread = new Thread(new RegisterWriter(file.getPath(), this::generateFileSavedAlert, this::activateButtons));
                deactivateButtons();
                saveThread.start();

            } catch (InvalidFileTypeException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateFileSavedAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Kunderegisteret ble lagret");
        alert.show();
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
    private void opprettNyKunde(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/NyKunde.fxml"));
            Scene kundeScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(kundeScene);
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clickOnTable(MouseEvent event) {

        //Dersom det blir oppdaget dobbeltklikk på tabellen, åpnes kundeforhold for markert kunde.
        tableKunder.setOnMouseClicked((MouseEvent clickEvent) -> {
            if (clickEvent.getButton().equals(MouseButton.PRIMARY) && clickEvent.getClickCount() == 2) {
                visValgtKunde(new ActionEvent());
            }
        });
    }

    @FXML
    private void visValgtKunde(ActionEvent event) {

        try {

            Kunde valgtKunde = tableKunder.getSelectionModel().getSelectedItem();

            if (valgtKunde != null) {
                /*
                Kilder/inspirasjon for sending av parametre til kontroller:
                https://github.com/jalopezsuarez/javafx-screens/blob/master/javafx-screens/src/com/vemovi/javafx/NavigationController.java
                https://stackoverflow.com/questions/14187963/passing-parameters-javafx-fxml
                */

                //Oppretter en ny kontroller-instans og setter kunde til den valgte kunden.
                KundeforholdController kundeforholdController = new KundeforholdController(valgtKunde);

                //Oppretter en FXMLLoader med fxml-filen.
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Kundeforhold.fxml"));

                //Setter loaderens kontroller til kontroller-instansen da fx:controller er ikke satt i FXML-filen.
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
        setUpTableKunder();
    }

    /**
     * Setter opp kundetabellen med tilhørende søkefunksjonalitet og sortering.
     */
    private void setUpTableKunder() {

        //Henter Singleton instansen av kunderegisteret
        kunderegister = Kunderegister.getInstance();

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

        //Wrapper kundeListen fra kunderegisteret (observablelisten) i en FilteredList for å støtte søkefunksjonalitet.
        FilteredList<Kunde> filteredList = new FilteredList<>(kunderegister.getKundeliste());

        //Setter opp en listener for søkefeltet (txtSearch), slik at filteret oppdateres hver gang man skriver noe nytt.
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(kunde -> {

                //Vis alt dersom søkefeltet er tomt:
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //Teksten som skal søkes på omgjøres til lowercase for sammenligning.
                String lowerCaseFilter = newValue.toLowerCase();

                //Sammenligner lowerCaseFilter med fornavn, etternavn og forsikringsnummer.
                if (kunde.getFornavn().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (kunde.getEtternavn().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (kunde.getFakturaadresse().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Long.toString(kunde.getForsikringsnummer()).contains(lowerCaseFilter)) {
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
