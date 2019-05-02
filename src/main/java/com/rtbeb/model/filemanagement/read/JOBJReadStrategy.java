package com.rtbeb.model.filemanagement.read;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;
import com.rtbeb.model.filemanagement.exception.FileReadException;

import java.io.*;
import java.util.ArrayList;

public class JOBJReadStrategy implements FileReadStrategy{

    @Override
    public void readFromFile(String filePath) throws ClassCastException, ClassNotFoundException, FileReadException {
        try(FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            Object object = objectInputStream.readObject();
            System.out.println("Lastet inn: " + object);
            Kunderegister kunderegister = Kunderegister.getInstance();
            kunderegister.setKundeliste((ArrayList<Kunde>) object);

        }catch (IOException e){
            throw new FileReadException("Feil ved innlesning av fil. Filen kan være korrupt");
        }catch (ClassNotFoundException e){
            throw new FileReadException("Feil med innlesning av fil. Sjekk at du har valgt riktig fil");
        }
    }
}
