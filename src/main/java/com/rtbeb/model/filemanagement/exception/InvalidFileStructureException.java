package com.rtbeb.model.filemanagement.exception;

/**
 * Exception for feil struktur på filen.
 * @Author Eirik Bøyum - s170002
 */
public class InvalidFileStructureException extends Exception{
    public InvalidFileStructureException(String message){
        super(message);
    }
}
