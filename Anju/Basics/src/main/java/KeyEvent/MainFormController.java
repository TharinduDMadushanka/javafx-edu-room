package KeyEvent;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainFormController {
    public TextField txtTypeHere;
    public TextField txtAnswer;

    public void typeOnAction(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            txtAnswer.setText(txtTypeHere.getText());
        }
    }
}
