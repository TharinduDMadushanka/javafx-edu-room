package first;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //catch fxml file
        URL resource = getClass().getResource("MainForm.fxml");

        //load to the RAM
        Parent load = FXMLLoader.load(resource);

        // create a scene
        Scene scene = new Scene(load);

    }
}
