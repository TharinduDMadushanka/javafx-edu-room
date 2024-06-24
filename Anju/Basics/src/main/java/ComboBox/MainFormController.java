package ComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;

public class MainFormController {
    //public ComboBox<String> cmbLanguages;
    public ComboBox cmbLanguages;
    public TextField txtLoadLanguage;

    ArrayList<String> languages = new ArrayList<>(
            Arrays.asList("Java","Python","Ruby","JS")
    );



    public void initialize(){

//        cmbLanguages.getItems().add("Java");
//        cmbLanguages.getItems().add("Python");
//        cmbLanguages.getItems().add("Ruby");
//        cmbLanguages.getItems().add("JS");

//        for(String lang : languages){
//            cmbLanguages.getItems().add(lang);
//        }

        ObservableList<String> items = FXCollections.observableArrayList(languages);

        cmbLanguages.setItems(items);

        cmbLanguages.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            txtLoadLanguage.setText(t1.toString());
        }); // show in text area

    }

}
