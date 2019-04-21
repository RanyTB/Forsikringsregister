package com.rtbeb.controller.helper;

import javafx.scene.Node;

/**
 * Hjelpeklasse som endrer stil på input-noder. Gir feedback på om inputen er lovlig eller ikke.
 */
public class FieldStyler {

    private static String validColor="green";
    private static String invalidColor="red";

    public static void setValidStyle(Node node){
        node.setStyle("-fx-border-color: " + validColor);
    }

    public static void setInvalidStyle(Node node){
        node.setStyle("-fx-border-color: " + invalidColor);
    }

}
