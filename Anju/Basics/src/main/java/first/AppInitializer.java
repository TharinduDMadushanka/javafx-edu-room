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
        // Catch FXML file
        URL resource = getClass().getResource("/first/MainForm.fxml");


        // Load to the RAM
        Parent load = FXMLLoader.load(resource);

        // Create a scene
        Scene scene = new Scene(load);

        // Set Scene to stage
        stage.setScene(scene);

        // Show Stage
        stage.show();
    }
}
