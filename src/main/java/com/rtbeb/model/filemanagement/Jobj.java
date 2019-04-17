package com.rtbeb.model.filemanagement;
import com.rtbeb.model.base.Kunderegister;
import com.rtbeb.model.base.Kunde;

import java.io.*;

import java.util.ArrayList;



public class Jobj implements FileManagement {

    @Override
    public void writeToFile(String filePath) throws IOException{
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

    @Override
    public void readFromFile(String filePath) {
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

