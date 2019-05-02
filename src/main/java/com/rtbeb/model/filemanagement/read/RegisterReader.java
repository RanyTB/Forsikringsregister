package com.rtbeb.model.filemanagement.read;

import com.rtbeb.model.base.exception.InvalidForsikringException;
import com.rtbeb.model.filemanagement.exception.InvalidFileContentException;
import com.rtbeb.model.filemanagement.exception.InvalidFileTypeException;
import javafx.concurrent.Task;

import java.io.IOException;

public class RegisterReader extends Task<Void> {

    String path;
    FileReadStrategy fileReadStrategy;
    Runnable successAlertFunc;
    Runnable activateButtonsFunc;

    public RegisterReader(String path, Runnable successAlertFunc, Runnable activateButtonsFunc) throws InvalidFileTypeException {
        this.path = path;
        this.fileReadStrategy = getStrategyBasedOnExtension(path);
        this.successAlertFunc = successAlertFunc;
        this.activateButtonsFunc = activateButtonsFunc;
    }

    private FileReadStrategy getStrategyBasedOnExtension(String path) throws InvalidFileTypeException {

        String[] pathArray = path.split("\\.");

        String filtype = pathArray[ pathArray.length -1 ].toLowerCase();

        if( filtype.equals("csv")){
            return new CSVReadStrategy();
        } else if( filtype.equals("jobj")){
            return new JOBJReadStrategy();
        } else{
            throw new InvalidFileTypeException("Ugyldig filtype " + filtype);
        }
    }

    @Override
    protected Void call() {

        //Simulerer lang task.
        try {
        Thread.sleep(3000);
            fileReadStrategy.readFromFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidForsikringException e) {
            e.printStackTrace();
        } catch (InvalidFileContentException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        successAlertFunc.run();
        activateButtonsFunc.run();
    }
}
