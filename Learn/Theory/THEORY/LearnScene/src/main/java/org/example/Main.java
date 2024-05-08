package org.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root =new Group();
        Scene scene=new Scene(root,600,600, Color.LIGHTBLUE);
        Stage stage2 =new Stage();

        /**
         * text field
         */
//        Text text =new Text();
//        text.setText("Heyyyyy");
//        text.setX(50);
//        text.setY(50);
//        text.setFont();
//        text.setFill();

        stage2.setScene(scene);
        stage2.show();
    }
}