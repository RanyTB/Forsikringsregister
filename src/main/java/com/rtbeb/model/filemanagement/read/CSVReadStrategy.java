package com.rtbeb.model.filemanagement.read;

import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.filemanagement.exception.InvalidFileContentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVReadStrategy implements FileReadStrategy {

    @Override
    public void readFromFile(String filePath) throws IOException, InvalidForsikringException, InvalidFileContentException {
        Path path = Paths.get(filePath);
        CSVReadHelper csvReadHelper = new CSVReadHelper();

        try(BufferedReader bufferedReader = Files.newBufferedReader(path)){
            String linje = null;
            String[] splittetLinje;

            while ((linje = bufferedReader.readLine()) != null){
                splittetLinje = linje.split("\"");
                csvReadHelper.håndterArray(splittetLinje);
            }
            csvReadHelper.addToRegistry();
            System.out.println("Ferdig med å lese fil");
        }catch (IOException e){
            e.printStackTrace();
        }


        /*BufferedReader bufferedReader = null;
        try{
            bufferedReader = Files.newBufferedReader(path);
            String linje = null;
            String[] splittetLinje;

            while ((linje = bufferedReader.readLine()) != null){
                splittetLinje = linje.split("\"");
                csvReadHelper.håndterArray(splittetLinje);
            }
            //csvReadHelper.addToRegistry();
            System.out.println("Ferdig med å lese fil");
        }finally {
            if (bufferedReader != null){
                bufferedReader.close();
            }
        }*/
    }
}
