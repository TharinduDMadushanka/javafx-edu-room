package edu.ijse.UIController;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class OrderViewController {
    public AnchorPane txtCustomerId;
    public TextField txtOrderId;
    public TextField txtCustomerShow;
    public TextField txtItemId;
    public TextField txtDiscount;
    public TextField txtItemShow;
    public TextField txtQty;
    public TableView tblOrder;
    public TableColumn colItemCode;
    public TableColumn colQty;
    public TableColumn colDiscount;

    public void customerSearchOnAction(ActionEvent actionEvent) {
    }

    public void itemSearchOnAction(ActionEvent actionEvent) {
    }

    public void itemAddOnAction(ActionEvent actionEvent) {
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
    }
}
