package first;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainFormController {
    public Label lblName;
    public TextField txtName;

    public void clickMeOnAction(ActionEvent actionEvent) {

        String name = txtName.getText();

        //lblName.setText("Welcome");
        lblName.setText(name);
    }
}
