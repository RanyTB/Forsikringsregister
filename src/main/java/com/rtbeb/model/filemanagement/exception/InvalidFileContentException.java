package com.rtbeb.model.filemanagement.exception;

/**
 * Exception for feil innhold i en fil.
 * @Author Eirik Bøyum - s170002
 */
public class InvalidFileContentException extends Exception{
    public InvalidFileContentException(String message){
        super(message);
    }
}
