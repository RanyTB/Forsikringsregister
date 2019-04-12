package com.rtbeb.controller;

import com.rtbeb.model.base.Forsikring;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KundeforholdController implements Initializable {

    Kunde valgtKunde;

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
    Button backButton;

    @FXML
    private void redigerKundeClicked() throws IOException {

        //Oppretter redigerKunde kontroller med valgt kunde og en loader for fxml-filen. Binder kontroller og view sammen.
        RedigerKundeController redigerKundeController = new RedigerKundeController(valgtKunde);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RedigerKunde.fxml"));
        loader.setController(redigerKundeController);

        //Loader parent og åpner kunderedigering i nytt vindu.
        Parent root = loader.load();
        Scene scene = new Scene(root);
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
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(previousScene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblForsikringsnummer.textProperty().bind(valgtKunde.forsikringsnummerProperty().asString());
        lblFornavn.textProperty().bind(valgtKunde.fornavnProperty());
        lblEtternavn.textProperty().bind(valgtKunde.etternavnProperty());
        lblFakturaadresse.textProperty().bind(valgtKunde.fakturaadresseProperty());
        lblPostnummer.textProperty().bind(valgtKunde.postnummerProperty());
    }
}
