package com.rtbeb.model.filemanagement.read;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class JOBJReadStrategy implements FileReadStrategy{

    @Override
    public void readFromFile(String filePath) throws IOException {
        try(FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            Object object = objectInputStream.readObject();
            System.out.println("Lastet inn: " + object);
            Kunderegister kunderegister = Kunderegister.getInstance();
            kunderegister.setKundeliste((ArrayList<Kunde>) object);

        }catch (IOException e){
            //System.err.println("Kunne ikke lese fra fil grunnet: " + e.getCause());
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            System.err.println("Kunne ikke konvertere objekt");
        }
    }
}
