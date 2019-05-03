package com.rtbeb.controller.redigering;

import com.rtbeb.model.base.Kunde;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Abstract kontroller for redigering av forsikringer. Inneholder noen felles metoder.
 * @author Rany Tarek Bouorm - s236210
 */
abstract class RedigerforsikringController implements Initializable {

    private Kunde kunde;

    RedigerforsikringController(Kunde kunde){
        this.kunde = kunde;
    }

    /**
     * Legger til listeneres på TextFields slik at det kun kan skrives inn tall i disse.
     * @param numericFields TextField som kun skal inneholde tall.
     */
    void addNumericListeners(TextField[] numericFields){

        for(TextField numericField : numericFields){

            //Legger til listener som hindrer at ikke-numeriske characters blir skrevet i felt.
            numericField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    numericField.setText(oldValue);
                }
            });
        }
    }

}
