package com.rtbeb.model.filhåndtering;

import javafx.concurrent.Task;

import static java.lang.Thread.sleep;

public class TestTask extends Task<Void> {

    @Override
    protected Void call() throws Exception {

        try {
            doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void doWork() throws InterruptedException {
        sleep(3000);
    }
}
