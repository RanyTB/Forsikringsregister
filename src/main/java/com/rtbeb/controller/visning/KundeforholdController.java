package com.rtbeb.controller.visning;


import com.rtbeb.controller.helper.FXMLStyler;
import com.rtbeb.controller.redigering.RedigerKundeController;
import com.rtbeb.controller.redigering.RedigerSkademeldingController;
import com.rtbeb.controller.redigering.UtbetalErstatningController;
import com.rtbeb.controller.redigering.helper.RedigerforsikringHelper;
import com.rtbeb.controller.registrering.RegistrerNyForsikringController;
import com.rtbeb.controller.registrering.RegistrerSkademeldingController;
import com.rtbeb.model.base.forsikring.Skademelding;
import com.rtbeb.model.base.forsikring.Forsikring;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Kontroller for kundeforholdsvisningen. Valgt kunde settes med kontrollerens konstruktør.
 * FXML-filen og Kontrolleren må bindes.
 * @author Rany Tarek Bouorm - s236210
 * @author Eirik Bøyum - Markerte deler med Skademelding og Ubetalteerstatniger.
 */
public class KundeforholdController implements Initializable {

    private Kunde valgtKunde;

    KundeforholdController(Kunde valgtKunde){
        this.valgtKunde = valgtKunde;
    }

    @FXML
    private Label lblFornavn;
    @FXML
    private Label lblEtternavn;
    @FXML
    private Label lblForsikringsnummer;
    @FXML
    private Label lblFakturaadresse;
    @FXML
    private Label lblPostnummer;
    @FXML
    private Button btnSlettKunde;
    @FXML
    private Button btnNySkademelding;

    @FXML
    private Button backButton;

    @FXML
    private void redigerKundeClicked() throws IOException {

        //Oppretter redigerKunde kontroller med valgt kunde og en loader for fxml-filen. Binder kontroller og view sammen.
        RedigerKundeController redigerKundeController = new RedigerKundeController(valgtKunde);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RedigerKunde.fxml"));
        loader.setController(redigerKundeController);

        //Loader parent og åpner kunderedigering i nytt vindu og setter stylesheet.
        Parent root = loader.load();
        Scene scene = new Scene(root);
        FXMLStyler.addDefaultStyleSheet(scene);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void slettKundeClicked(){

        //Henter kunderegister-instansen
        Kunderegister kunderegister = Kunderegister.getInstance();

        //Genererer alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Slett kunde");
        alert.setHeaderText("Er du sikker på at du vil slette kunden?");

        //Sletter kunden dersom brukeren trykker OK.
        alert.showAndWait().ifPresent(respons -> {
            if (respons == ButtonType.OK) {
                kunderegister.deleteKunde(this.valgtKunde);
                returnToKundeVisning();
            }
        });
    }

    private void returnToKundeVisning(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Kundevisning.fxml"));
            Scene scene = new Scene(root);
            FXMLStyler.addDefaultStyleSheet(scene);
            Stage stage = (Stage) btnSlettKunde.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void backButtonClicked(ActionEvent event){

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/kundevisning.fxml"));
            Scene previousScene = new Scene(root);
            FXMLStyler.addDefaultStyleSheet(previousScene);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(previousScene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindLabelsWithCustomerInfo();
        setupForsikringsTable();
        setupSkademeldingsTable();
        setupUbetalteErstatningerTable();
    }

    private void bindLabelsWithCustomerInfo(){
        //Binder TextFields til kundens datafelt.
        lblForsikringsnummer.textProperty().bind(valgtKunde.forsikringsnummerProperty().asString());
        lblFornavn.textProperty().bind(valgtKunde.fornavnProperty());
        lblEtternavn.textProperty().bind(valgtKunde.etternavnProperty());
        lblFakturaadresse.textProperty().bind(valgtKunde.fakturaadresseProperty());
        lblPostnummer.textProperty().bind(valgtKunde.postnummerProperty());
    }

    //---------------Forsikringer-----------------//


    @FXML
    private TableView<Forsikring> tableForsikringer;

    @FXML
    private TableColumn<Forsikring,String> forsikringstypeColumn;

    @FXML
    private TableColumn<Forsikring, LocalDate> datoOpprettetColumn;

    @FXML
    private TableColumn<Forsikring, Integer> forsikringsbelopColumn;

    @FXML
    private TableColumn<Forsikring, LocalDate> forsikringspremieColumn;


    private void setupForsikringsTable(){

        forsikringstypeColumn.setCellValueFactory(new PropertyValueFactory<>("forsikringstype"));
        datoOpprettetColumn.setCellValueFactory(new PropertyValueFactory<>("datoOpprettet"));
        forsikringsbelopColumn.setCellValueFactory(new PropertyValueFactory<>("forsikringsbeløp"));
        forsikringspremieColumn.setCellValueFactory(new PropertyValueFactory<>("forsikringspremie"));

        //Håndterer dobbelklikk på tabellen.
        tableForsikringer.setOnMouseClicked((MouseEvent clickEvent) -> {
            if (clickEvent.getButton().equals(MouseButton.PRIMARY) && clickEvent.getClickCount() == 2){
                redigerForsikringButtonClicked(new ActionEvent());
            }
        });

        tableForsikringer.setItems(valgtKunde.getForsikringsListe());
    }

    @FXML
    private void nyForsikringButtonClicked(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistrerNyForsikring.fxml"));
            RegistrerNyForsikringController controller = new RegistrerNyForsikringController(valgtKunde);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            FXMLStyler.addDefaultStyleSheet(scene);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void redigerForsikringButtonClicked(ActionEvent event){

        Forsikring valgtForsikring = tableForsikringer.getSelectionModel().getSelectedItem();

        RedigerforsikringHelper.openRedigeringScene(valgtForsikring, valgtKunde);
    }

    @FXML
    private void slettForsikringButtonClicked(ActionEvent event){
       Forsikring valgtForsikring = tableForsikringer.getSelectionModel().getSelectedItem();
        //Genererer alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Slett forsikring");
        alert.setHeaderText("Er du sikker på at du vil slette forsikringen?");

        //Sletter forsikringen dersom brukeren trykker OK.
        alert.showAndWait().ifPresent(respons -> {
            if (respons == ButtonType.OK) {
                valgtKunde.slettForsikring(valgtForsikring);
            }
        });

    }


    //---------------Skademeldinger-----------------//
    /**
     * @author Eirik Bøyum
     */

    @FXML
    private TableView<Skademelding> tableSkademeldinger;

    @FXML
    private TableColumn<Skademelding, LocalDate> datoForSkadeColumn;

    @FXML
    private TableColumn<Skademelding, Integer> skadennummerColumn;

    @FXML
    private TableColumn<Skademelding, String> typeSkadeColumn;

    @FXML
    private TableColumn<Skademelding, Integer> takseringAvSkadeColumn;

    @FXML
    private TableColumn<Skademelding, Integer> utbetaltErstatningsbeløpColumn;

    private void setupSkademeldingsTable(){

      datoForSkadeColumn.setCellValueFactory(new PropertyValueFactory<>("skademeldingsDato"));
      skadennummerColumn.setCellValueFactory(new PropertyValueFactory<>("skadenummer"));
      typeSkadeColumn.setCellValueFactory(new PropertyValueFactory<>("typeSkade"));
      takseringAvSkadeColumn.setCellValueFactory(new PropertyValueFactory<>("takseringAvSkaden"));
      utbetaltErstatningsbeløpColumn.setCellValueFactory(new PropertyValueFactory<>("utbetaltErstatningsbeløp"));
      tableSkademeldinger.setItems(valgtKunde.getSkademeldinger());


        tableSkademeldinger.setOnMouseClicked((MouseEvent clickEvent) -> {
            if (clickEvent.getButton().equals(MouseButton.PRIMARY) && clickEvent.getClickCount() == 2){
                redigerSkademeldingButtonClicked(new ActionEvent());
            }
        });
    }

    @FXML
    private void nySkademeldingButtonClicked(ActionEvent event) {

        try {
            RegistrerSkademeldingController registrerSkademeldingController = new RegistrerSkademeldingController(valgtKunde);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistrerSkademelding.fxml"));

            //Setter loaderens kontroller til kontroller-instansen. fx:controller er ikke satt i FXML-filen.
            loader.setController(registrerSkademeldingController);


            //Loader FXML-hierarkiet
            Parent root = loader.load();

            //Oppretter ny scene og setter stylesheet
            Scene scene = new Scene(root);
            FXMLStyler.addDefaultStyleSheet(scene);

            //Oppretter ny stage
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void slettSkademeldingButtonClicked(ActionEvent event){
        Skademelding skademelding = tableSkademeldinger.getSelectionModel().getSelectedItem();
        System.out.println("Valgt forsikringsnummer: " + skademelding.getSkadenummer());
        valgtKunde.slettSkademelding(skademelding);
    }

    @FXML
    private void redigerSkademeldingButtonClicked(ActionEvent event){

        try {
            Skademelding valgtSkademelding = tableSkademeldinger.getSelectionModel().getSelectedItem();

            RedigerSkademeldingController redigerSkademeldingController = new RedigerSkademeldingController(valgtKunde, valgtSkademelding);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RedigerSkademelding.fxml"));

            //Setter loaderens kontroller til kontroller-instansen. fx:controller er ikke satt i FXML-filen.
            loader.setController(redigerSkademeldingController);

            //Loader FXML-hierarkiet
            Parent root = loader.load();

            //Oppretter ny scene og setter stylesheet
            Scene scene = new Scene(root);
            FXMLStyler.addDefaultStyleSheet(scene);

            //Oppretter ny stage
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //---------------Skademeldinger end-----------------//

    //---------------Ubetalt erstatning-----------------//

    /**
     * @author Eirik Bøyum
     */

    @FXML
    private TableView<Skademelding> tableUbetalteErstatninger;

    @FXML
    private TableColumn<Skademelding, LocalDate> ubetaltDatoForSkadeColumn;

    @FXML
    private TableColumn<Skademelding, Integer> ubetaltSkadennummerColumn;

    @FXML
    private TableColumn<Skademelding, String> ubetaltTypeSkadeColumn;

    @FXML
    private TableColumn<Skademelding, Integer> ubetaltTakseringAvSkadeColumn;


    private void setupUbetalteErstatningerTable(){
        //Lager et filter for å hente ut ubetalte skademeldinger
        FilteredList<Skademelding> filteredList = new FilteredList<>(valgtKunde.getSkademeldinger());

        /*Predicate som filtrerer listen med skademeldinger basert på om det er foreligger et taksert beløp, men ikke
        utbetalt beløp. Skademeldingslisten i kundeklassen sender update events
         når taksert beløp eller utbetalt beløp endres vha. extractor.*/
        filteredList.setPredicate(skademelding -> Integer.parseInt(skademelding.getUtbetaltErstatningsbeløp()) == 0 && Integer.parseInt(skademelding.getTakseringAvSkaden()) > 0);

        ubetaltDatoForSkadeColumn.setCellValueFactory(new PropertyValueFactory<>("skademeldingsDato"));
        ubetaltSkadennummerColumn.setCellValueFactory(new PropertyValueFactory<>("skadenummer"));
        ubetaltTypeSkadeColumn.setCellValueFactory(new PropertyValueFactory<>("typeSkade"));
        ubetaltTakseringAvSkadeColumn.setCellValueFactory(new PropertyValueFactory<>("takseringAvSkaden"));
        tableUbetalteErstatninger.setItems(filteredList);

        tableUbetalteErstatninger.setOnMouseClicked((MouseEvent clickEvent) -> {
            if (clickEvent.getButton().equals(MouseButton.PRIMARY) && clickEvent.getClickCount() == 2){
                ubetalteErstatningButtonClicked(new ActionEvent());
            }
        });
    }

    @FXML
    private void ubetalteErstatningButtonClicked(ActionEvent event) {

        try {
            Skademelding valgtUbetaltErstatning = tableUbetalteErstatninger.getSelectionModel().getSelectedItem();

            UtbetalErstatningController utbetalErstatningController = new UtbetalErstatningController(valgtKunde, valgtUbetaltErstatning);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UtbetalErstatning.fxml"));

            //Setter loaderens kontroller til kontroller-instansen. fx:controller er ikke satt i FXML-filen.
            loader.setController(utbetalErstatningController);

            //Loader FXML-hierarkiet
            Parent root = loader.load();

            //Oppretter ny scene og setter stylesheet
            Scene scene = new Scene(root);
            FXMLStyler.addDefaultStyleSheet(scene);

            //Oppretter ny stage
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void slettUbetalteErstatningButtonClicked(ActionEvent event){
        Skademelding skademelding = tableSkademeldinger.getSelectionModel().getSelectedItem();
        System.out.println("Valgt forsikringsnummer: " + skademelding.getSkadenummer());
        valgtKunde.slettSkademelding(skademelding);
    }

    //---------------Ubetalt erstatning end-----------------//


}
