package com.rtbeb.model.filemanagement;
import com.rtbeb.model.base.Kunderegister;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class WriteToJOBJ implements FileManagement {

    @Override
    public void writeToFile(String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            Kunderegister kunderegister = Kunderegister.getInstance();
            objectOutputStream.writeObject(kunderegister);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

