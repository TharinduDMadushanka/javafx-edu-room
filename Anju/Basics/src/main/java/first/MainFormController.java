package first;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class MainFormController {
    public Label lblName;

    public void clickMeOnAction(ActionEvent actionEvent) {

        lblName.setText("Welcome ");

    }
}
