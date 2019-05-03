package com.rtbeb.controller.helper;

import javafx.scene.Scene;

/**
 * Hjelpeklasse for å knytte sammen CSS og FXML
 * @author Rany Tarek Bouorm
 */
public class FXMLStyler {

    public static void addDefaultStyleSheet(Scene scene){
        scene.getStylesheets().add("/styles/Styles.css");
    }

}
