package com.rtbeb.model.filemanagement.read;

import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.filemanagement.exception.InvalidFileContentException;

import java.io.IOException;

public interface FileReadStrategy {

    void readFromFile(String filePath) throws IOException, InvalidForsikringException, InvalidFileContentException;


}
