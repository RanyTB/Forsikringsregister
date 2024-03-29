package com.rtbeb.model.filemanagement.read;

import com.rtbeb.model.filemanagement.exception.FileReadException;
import com.rtbeb.model.filemanagement.exception.InvalidFileContentException;
import com.rtbeb.model.filemanagement.exception.InvalidFileStructureException;
import com.rtbeb.model.filemanagement.read.helper.CSVReadHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Eirik Bøyum
 * Klassen for lesing av fil.
 * Metoden readFromFile tar imot filepathen og prøver lese inn filen.
 * Den leser inn hver linje fra filen og splittern den. Resultatet av splitten sendes videre til klassen CSVReadHelper.
 * linjenummeret telles opp slik at bruker vet i hvilken linje lesing fra fil eventuelt feilet.
 */
public class CSVReadStrategy implements FileReadStrategy {

    @Override
    public void readFromFile(String filePath) throws FileReadException {
        Path path = Paths.get(filePath);
        CSVReadHelper csvReadHelper = new CSVReadHelper();
        int linjenummer = 0;

        try(BufferedReader bufferedReader = Files.newBufferedReader(path)){
            String linje;
            String[] splittetLinje;

            while ((linje = bufferedReader.readLine()) != null){
                linjenummer++;
                splittetLinje = linje.split("\"");
                csvReadHelper.håndterArray(splittetLinje);
            }
            csvReadHelper.addToRegistry();
        }catch (IOException e){
            throw new FileReadException("Kunne ikke lese filen.");
        }catch(FileReadException e){
            //Fanger FileReadException og legger på linjenummeret i filen før den kastes videre.
            throw new FileReadException("Linje " + linjenummer + ": " + e.getMessage());
        }

    }
}
