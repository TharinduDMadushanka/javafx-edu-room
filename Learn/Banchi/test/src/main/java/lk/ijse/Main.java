package lk.ijse;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane layout =new StackPane();

        Button button =new Button("Hello world");

        button.setOnAction(actionEvent -> {
            System.out.println(System.getProperty("java.version"));
        });

        layout.getChildren().add(button);
        Scene scene=new Scene(layout,300,300);
        stage.getScene();
        stage.setTitle("Java fx 22");
        stage.show();
    }
}