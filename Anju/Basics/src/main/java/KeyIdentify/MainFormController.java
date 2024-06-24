package KeyIdentify;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController{

    public TextField txtInput;
    public TextField txtOutput;

    public void initialize() {
        //add a listener
        txtInput.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("New Value: " + newValue);
            System.out.println("Old Value: " + oldValue);
        });
    }


    //functional interface -- > have only one method
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        System.out.println("hello");
//    }

    //Error
//    MainFormController(){
//        System.out.println("Hello");
//    }

    //OK
//    static {
//        System.out.println("Hello");
//    }

    // OK
//    {
//        System.out.println("Hello");
//    }

}
