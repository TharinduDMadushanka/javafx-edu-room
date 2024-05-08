package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/view/css.fxml")));
        //scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm()); //link css file

        String css =this.getClass().getResource("app.css").toExternalForm(); // for more than one file
        scene.getStylesheets().add(css);

        Group root= new Group();
        Stage stage2=new Stage();

        stage2.setScene(scene);
        stage2.show();
    }
}