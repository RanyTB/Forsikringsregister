package com.rtbeb.controller.registrering.helper;

import javafx.scene.control.Alert;

public class AlertGenerator {

    /**
     * Genererer og viser en feilmelding for registreringsfeil.
     * @param message Filmeldingen som skal vises.
     */
    public static void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Registreringsfeil");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
