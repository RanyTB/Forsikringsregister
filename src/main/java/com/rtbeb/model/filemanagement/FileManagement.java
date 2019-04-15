package com.rtbeb.model.filemanagement;

//Strategy for writing to file
public interface FileManagement {
    public void writeToFile(String filePath);

    public void readFromFile(String filePath) throws Exception;
}