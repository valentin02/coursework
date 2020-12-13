package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.classes.ClockGenerator;
import sample.classes.Scheduler;

import java.io.IOException;

public class Controller extends Thread {

    @FXML
    TextArea taTact = new TextArea();
    @FXML
    TextArea taQueue = new TextArea();
    @FXML
    TextArea taRejects = new TextArea();
    @FXML
    TextArea taProcess = new TextArea();

    Scheduler scheduler = new Scheduler(4, 4096);

    @Override
    public void run() {
        this.start();
        this.finish();
        super.run();
    }

    public void start() {
        if(ClockGenerator.getTime() == 0){
        }

        scheduler.algorithm();
        taTact.setText(String.valueOf(ClockGenerator.getTime()));
        taProcess.setText(ClockGenerator.printP());
        taTact.setText(String.valueOf(ClockGenerator.getTime()));
        taQueue.setText(ClockGenerator.printQ());
        taRejects.setText(ClockGenerator.printQ());
        scheduler.incTimeProcess();
        scheduler.interruptProcess();
        scheduler.releaseMemory();

    }

    public void finish() {

    }
}
