package com.rtbeb.model.filemanagement.write;

import java.io.IOException;

public interface FileSaveStrategy {

    void writeToFile(String filePath) throws IOException;

}
