package edu.ijse.UIController;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ItemViewController {
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtPackSize;
    public TextField txtUnitPrice;
    public TextField txtQoh;
    public TableView itemTable;
    public TableColumn colItemId;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colQoh;
    public TableColumn colUnitPrice;

    public void saveOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }
}
