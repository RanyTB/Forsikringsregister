package com.rtbeb.model.validation;

import javafx.scene.Node;

public class FieldStyler {

    public static void setValidStyle(Node node){
        node.setStyle("-fx-border-color: green");
    }

    public static void setInvalidStyle(Node node){
        node.setStyle("-fx-border-color: red");
    }

}
