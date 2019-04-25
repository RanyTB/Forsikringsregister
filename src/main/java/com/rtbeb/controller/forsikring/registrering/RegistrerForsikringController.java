package com.rtbeb.controller.forsikring.registrering;

import com.rtbeb.model.base.Kunde;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public abstract class RegistrerForsikringController implements Initializable {

    protected Kunde kunde;

    public RegistrerForsikringController(Kunde kunde){
        this.kunde = kunde;
    }

    /**
     * Legger til listeneres på TextFields slik at det kun kan skrives inn tall i disse.
     * @param numericFields TextField som kun skal inneholde tall.
     */
    public void addNumericListeners(TextField[] numericFields){

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
