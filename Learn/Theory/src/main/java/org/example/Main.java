package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root= new Group(); // create roo node
        Scene scene=new Scene(root, Color.BLACK);// add scene and change background color to black

//        Image icon =new Image("image.png");
//        stage.getIcons().add(icon);

        stage.setTitle("Hello Fx");
        stage.setResizable(false);//  resizability

        /**
         * set escape full screen --> press "q"
         */
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("Press 'q' to escape");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));

        stage.setWidth(420);
        stage.setHeight(420);
//        stage.setX(50); ---> change opening position of window
//        stage.setY(50);

        stage.setScene(scene);


        stage.show();
    }
}