package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/views/page1.fxml")));

        stage.setScene(scene);
        stage.setTitle("tharindu's view");
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }
}