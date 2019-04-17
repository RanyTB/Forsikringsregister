package com.rtbeb.model.filemanagement;

import java.io.IOException;

public class Context {
    private FileManagement fileManagement;

    public Context(FileManagement fileManagement){
        this.fileManagement = fileManagement;
    }

    public void writeStrategy(String filePath) throws IOException {
        fileManagement.writeToFile(filePath);
    }

    public void readStrategy(String filePath) throws IOException {
        fileManagement.readFromFile(filePath);
    }
}
