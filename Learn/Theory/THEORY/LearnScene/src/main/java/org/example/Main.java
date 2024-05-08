package org.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root =new Group();
        Scene scene=new Scene(root,600,600);
        Stage stage2 =new Stage();

        stage2.setScene(scene);
        stage2.show();
    }
}