package com.rtbeb.model.filemanagement.write;

import com.rtbeb.model.base.Kunde;
import com.rtbeb.model.base.Kunderegister;
import com.rtbeb.model.base.forsikring.Båt.Båtforsikring;
import com.rtbeb.model.base.forsikring.Forsikring;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVSaveStrategy implements FileSaveStrategy{

    @Override
    public void writeToFile(String filePath) throws IOException {
        Kunderegister kunderegister = Kunderegister.getInstance();
        CSVWriteHelper csvWriteHelper = null;
        Path path = Paths.get(filePath);
        PrintWriter printWriter = null;

        try {
            //TODO æøå
            printWriter = new PrintWriter(filePath, "UTF-8");
            for (Kunde kunde : kunderegister.getKundeliste()) {
                printWriter.print(csvWriteHelper.håndterkundeobjekt(kunde));

                printWriter.print("\n");
            }

        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
