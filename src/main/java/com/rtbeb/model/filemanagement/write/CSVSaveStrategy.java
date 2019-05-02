package com.rtbeb.model.filemanagement.write;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *  @author Eirik Bøyum
 *  Klassen itererer over kundeobjekter, kaller så på csvFormatter og skriver resultatet til fil som er gitt som
 *  parameter til writeToFile metoden
 */
public class CSVSaveStrategy implements FileSaveStrategy{

    @Override
    public void writeToFile(String filePath) throws IOException {
        Kunderegister kunderegister = Kunderegister.getInstance();
        CSVFormatter csvFormatter = null;
        Path path = Paths.get(filePath);
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(filePath, "UTF-8");

            for (Kunde kunde : kunderegister.getKundeliste()) {
                printWriter.print(csvFormatter.håndterkundeobjekt(kunde));
                printWriter.print("\n");
            }

        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
