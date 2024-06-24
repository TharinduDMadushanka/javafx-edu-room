package List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainFormController {
    public ListView<String> lstLanguages;
    public TextField txtOutput;
    public TextField textInput;

    public void initialize(){
        lstLanguages.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtOutput.setText(newValue);
        });
    }

    ObservableList<String> oblist = FXCollections.observableArrayList();

    public void inputOnAction(ActionEvent actionEvent) {

        String value = textInput.getText();

        if (isExists(value)){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Already Exists");
            alert.show();
            return;
        }
        oblist.add(value);
        lstLanguages.setItems(oblist);
        textInput.clear();
    }

    private boolean isExists(String value) {
        for (String temp : oblist) {
            if (temp.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
