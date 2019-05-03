package com.rtbeb.controller.helper;

import javafx.scene.control.Alert;

public class AlertGenerator {

    /**
     * Genererer og viser en feilmelding for redigeringsfeil.
     * @param message Filmeldingen som skal vises.
     */
    public static void redigeringsfeilAlert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Redigeringsfeil");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    /**
     * Genererer og viser en feilmelding for registreringsfeil.
     * @param message Filmeldingen som skal vises.
     */
    public static void registreringsfeilAlert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Redigeringsfeil");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
