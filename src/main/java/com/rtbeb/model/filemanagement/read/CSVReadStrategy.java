package com.rtbeb.model.filemanagement.read;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVReadStrategy implements FileReadStrategy {

    @Override
    public void readFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        CSVReadHelper csvReadHelper = new CSVReadHelper();

        try(BufferedReader bufferedReader = Files.newBufferedReader(path)){
            String linje = null;
            String[] splittetLinje;

            while ((linje = bufferedReader.readLine()) != null){
                splittetLinje = linje.split("\"");
                csvReadHelper.håndterArray(splittetLinje);
                System.out.println(splittetLinje[1]);
            }
            csvReadHelper.addToRegistry();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
