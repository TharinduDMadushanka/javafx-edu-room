package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/view/eventHandle.fxml")));

        Group root= new Group();
        Stage stage2=new Stage();

        stage2.setScene(scene);
        stage2.show();
    }
}