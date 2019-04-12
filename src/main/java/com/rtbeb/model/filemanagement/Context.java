package com.rtbeb.model.filemanagement;

public class Context {
    private FileManagement fileManagement;

    public Context(FileManagement fileManagement){
        this.fileManagement = fileManagement;
    }

    public void fileStrategy(String filePath){
        fileManagement.writeToFile(filePath);
    }
}
