package com.rtbeb.model.filemanagement;
import com.rtbeb.model.base.Kunderegister;
import com.rtbeb.model.base.Kunde;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.ArrayList;



public class WriteToJOBJ implements FileManagement {

    @Override
    public void writeToFile(String filePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            Kunderegister kunderegister = Kunderegister.getInstance();
            objectOutputStream.writeObject(new ArrayList<Kunde>(kunderegister.getKundeliste()));
            objectOutputStream.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

