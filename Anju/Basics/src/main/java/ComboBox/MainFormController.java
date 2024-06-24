package ComboBox;

import javafx.scene.control.ComboBox;

public class MainFormController {
    public ComboBox cmbLanguages;

    public void initialize(){

        cmbLanguages.getItems().add("Java");
        cmbLanguages.getItems().add("Python");
        cmbLanguages.getItems().add("Ruby");
        cmbLanguages.getItems().add("JS");


    }

}
