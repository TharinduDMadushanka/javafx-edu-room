package KeyPressing;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class MainFormController {
    public TextField txtKeyPress;
    public Label lblKeyPressAnswer;

    public void keyPressOnAction(KeyEvent actionEvent) {

        String value = txtKeyPress.getText();
        lblKeyPressAnswer.setText(value);

    }
}
