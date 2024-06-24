package ComboBox;

import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Arrays;

public class MainFormController {
    public ComboBox<String> cmbLanguages;

    ArrayList<String> languages = new ArrayList<>(
            Arrays.asList("Java","Python","Ruby","JS")
    );

    public void initialize(){

//        cmbLanguages.getItems().add("Java");
//        cmbLanguages.getItems().add("Python");
//        cmbLanguages.getItems().add("Ruby");
//        cmbLanguages.getItems().add("JS");

        for(String lang : languages){
            cmbLanguages.getItems().add(lang);
        }

    }

}
