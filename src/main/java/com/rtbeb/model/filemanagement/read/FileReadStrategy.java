package com.rtbeb.model.filemanagement.read;

import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.base.exception.InvalidSkademeldingException;
import com.rtbeb.model.filemanagement.exception.FileReadException;
import com.rtbeb.model.filemanagement.exception.InvalidFileContentException;
import com.rtbeb.model.filemanagement.exception.InvalidFileStructureException;

import java.io.ObjectStreamException;

public interface FileReadStrategy {

    void readFromFile(String filePath) throws FileReadException, InvalidSkademeldingException, InvalidForsikringException, ClassNotFoundException;


}
