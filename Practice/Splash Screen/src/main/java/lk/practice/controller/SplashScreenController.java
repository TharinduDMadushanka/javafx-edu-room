package lk.practice.controller;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.practice.service.LoadingTask;

public class SplashScreenController {

    public Rectangle recMain;
    public Rectangle recSub;
    public Label lblProgress;

    public void initialize() {

        LoadingTask task = new LoadingTask();
        task.progressProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println(newValue.doubleValue()*100);

            String formatNumber = String.format("%.0f", newValue.doubleValue()*100);
            System.out.println(formatNumber);

            lblProgress.setText(formatNumber+" %");

            recSub.setWidth(recMain.getWidth()*newValue.doubleValue());

            if (newValue.doubleValue()==1.0){ // close when splash screen done
                Window window = lblProgress.getScene().getWindow();
                Stage stage = (Stage) window;
                stage.close();
            }


        });

        new Thread(task).start();
    }

}
