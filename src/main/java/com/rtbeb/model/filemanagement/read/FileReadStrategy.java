package com.rtbeb.model.filemanagement.read;

import java.io.IOException;

public interface FileReadStrategy {

    void readFromFile(String filePath) throws IOException;


}
