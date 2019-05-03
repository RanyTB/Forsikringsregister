package com.rtbeb.model.filemanagement.read;

import com.rtbeb.model.filemanagement.exception.FileReadException;

public interface FileReadStrategy {

    void readFromFile(String filePath) throws FileReadException;


}
