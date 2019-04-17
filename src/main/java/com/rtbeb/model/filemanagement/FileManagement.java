package com.rtbeb.model.filemanagement;

import java.io.IOException;

//Strategy for writing to file
public interface FileManagement {
    public void writeToFile(String filePath) throws IOException;

    public void readFromFile(String filePath) throws Exception;
}