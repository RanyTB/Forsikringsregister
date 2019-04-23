package com.rtbeb.controller.forsikring.redigering.helper;

import com.rtbeb.controller.forsikring.redigering.RedigerInnboforsikringController;
import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.forsikring.Bolig.Innboforsikring;
import com.rtbeb.model.base.forsikring.Forsikring;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RedigerforsikringHelper {

    public static void openRedigeringScene(Forsikring forsikring, Kunde kunde){

        if(forsikring instanceof Innboforsikring){

            try {
                FXMLLoader loader = new FXMLLoader(RedigerforsikringHelper.class.getResource("/fxml/redigerInnboforsikring.fxml"));
                loader.setController(new RedigerInnboforsikringController(kunde, (Innboforsikring) forsikring));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch(IOException e){
                e.printStackTrace();
            }
        }

    }
}
