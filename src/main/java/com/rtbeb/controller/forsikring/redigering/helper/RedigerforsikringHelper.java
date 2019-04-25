package com.rtbeb.controller.forsikring.redigering.helper;

import com.rtbeb.controller.forsikring.redigering.RedigerBåtforsikringController;
import com.rtbeb.controller.forsikring.redigering.RedigerInnboforsikringController;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.forsikring.Bolig.Innboforsikring;
import com.rtbeb.model.base.forsikring.Båt.Båtforsikring;
import com.rtbeb.model.base.forsikring.Forsikring;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RedigerforsikringHelper {

    public static void openRedigeringScene(Forsikring forsikring, Kunde kunde){

        if(forsikring instanceof Innboforsikring){

                FXMLLoader loader = new FXMLLoader(RedigerforsikringHelper.class.getResource("/fxml/redigerInnboforsikring.fxml"));
                loader.setController(new RedigerInnboforsikringController(kunde, (Innboforsikring) forsikring));
                openScene(loader);

        } else if(forsikring instanceof Båtforsikring){
            FXMLLoader loader = new FXMLLoader(RedigerforsikringHelper.class.getResource("/fxml/redigerBåtforsikring.fxml"));
            loader.setController(new RedigerBåtforsikringController(kunde, (Båtforsikring) forsikring));
            openScene(loader);
        }
    }

    private static void openScene(FXMLLoader loader){
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
