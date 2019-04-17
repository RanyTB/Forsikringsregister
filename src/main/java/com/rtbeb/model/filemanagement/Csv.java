package com.rtbeb.model.filemanagement;

import com.rtbeb.model.base.Kunderegister;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Csv implements FileManagement{

    //TODO implementer denne metoden
    @Override
    public void writeToFile(String filePath) throws IOException {
        Kunderegister kunderegister = Kunderegister.getInstance();
        Path path = Paths.get(filePath);
    }

    @Override
    public void readFromFile(String filePath) {

    }
}
