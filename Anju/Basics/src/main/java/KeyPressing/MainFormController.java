package KeyPressing;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class MainFormController {
    public TextField txtKeyPress;
    public Label lblKeyPressAnswer;
    public TextField txtKeyRelease;
    public Label lblKeyReleaseAnswer;

    public void keyPressOnAction(KeyEvent actionEvent) {

        String value = txtKeyPress.getText(); // get old value
        lblKeyPressAnswer.setText(value);

    }

    public void onKeyRelease(KeyEvent keyEvent) {
        String value = txtKeyRelease.getText();
        lblKeyReleaseAnswer.setText(value); // give same enter answer
    }
}
