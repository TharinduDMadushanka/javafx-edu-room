package KeyIdentify;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController{

    public void initialize() {
        System.out.println("Hello");
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
