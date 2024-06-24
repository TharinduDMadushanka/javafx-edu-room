package CheckBox;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;

public class MainFormController {
    public CheckBox cboxJava;
    public CheckBox cboxPython;
    public CheckBox cboxRuby;

    public void printOnAction(ActionEvent actionEvent) {

        ArrayList<String> languages = new ArrayList<>();

        if (cboxJava.isSelected()) {
            languages.add("Java");
        }
        if (cboxPython.isSelected()) {
            languages.add("Python");
        }
        if (cboxRuby.isSelected()) {
            languages.add("Ruby");
        }

        System.out.println(languages);

    }
}
