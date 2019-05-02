package com.rtbeb.model.filemanagement.write;

import com.rtbeb.model.base.Kunderegister;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class JOBJSaveStrategy implements FileSaveStrategy {

    @Override
    public void writeToFile(String filePath) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            Kunderegister kunderegister = Kunderegister.getInstance();
            objectOutputStream.writeObject(new ArrayList<>(kunderegister.getKundeliste()));
            objectOutputStream.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
