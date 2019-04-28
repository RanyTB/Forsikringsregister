package com.rtbeb.model.filemanagement.write;

import com.rtbeb.model.filemanagement.exception.InvalidFileTypeException;
import javafx.concurrent.Task;

public class RegisterWriter extends Task<Void> {

    String path;
    FileSaveStrategy fileSaveStrategy;
    Runnable successAlertFunc;
    Runnable activateButtonsFunc;

    public RegisterWriter(String path, Runnable successAlertFunc, Runnable activateButtonsFunc) throws InvalidFileTypeException {
        this.path = path;
        this.fileSaveStrategy = getStrategyBasedOnExtension(path);
        this.successAlertFunc = successAlertFunc;
        this.activateButtonsFunc = activateButtonsFunc;
    }



    private FileSaveStrategy getStrategyBasedOnExtension(String path) throws InvalidFileTypeException {

        String[] pathArray = path.split("\\.");
        String filtype = pathArray[pathArray.length - 1].toLowerCase();

        if(filtype.equals("csv")){
            return new CSVSaveStrategy();
        } else if(filtype.equals("jobj")){
            return new JOBJSaveStrategy();
        } else{
            throw new InvalidFileTypeException("Ugyldig filtype: " + filtype);
        }
    }

    @Override
    protected Void call() throws Exception {

        //Simulerer lang task.
        Thread.sleep(3000);
        fileSaveStrategy.writeToFile(path);

        return null;
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        activateButtonsFunc.run();
        successAlertFunc.run();
    }
}
