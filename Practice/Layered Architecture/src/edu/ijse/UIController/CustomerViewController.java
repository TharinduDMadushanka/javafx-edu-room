package edu.ijse.UIController;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerViewController {
    public TextField txtId;
    public TextField txtTitle;
    public TextField txtName;
    public TextField txtDob;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtProvince;
    public TextField txtSalary;
    public TextField txtPostal;
    public TableView customerTable;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colName;
    public TableColumn colDoB;
    public TableColumn colSalary;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostal;

    public void saveOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void clearOnAction(ActionEvent actionEvent) {
    }
}
