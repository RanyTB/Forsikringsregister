package com.rtbeb.model.filemanagement;

public class Context {
    private FileManagement fileManagement;

    public Context(FileManagement fileManagement){
        this.fileManagement = fileManagement;
    }

    public void writeStrategy(String filePath){
        fileManagement.writeToFile(filePath);
    }

    public void readStrategy(String filePath) throws Exception {
        fileManagement.readFromFile(filePath);
    }
}
