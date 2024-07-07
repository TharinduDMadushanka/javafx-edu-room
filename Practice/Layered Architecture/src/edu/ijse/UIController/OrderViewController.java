package edu.ijse.UIController;

import edu.ijse.dto.CustomerDto;
import edu.ijse.dto.OrderDetailDto;
import edu.ijse.dto.OrderDto;
import edu.ijse.service.custom.impl.CustomerServiceImpl;
import edu.ijse.service.custom.impl.ItemServiceImpl;
import edu.ijse.service.custom.impl.OrderServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class OrderViewController {
    public AnchorPane rootPane;
    public TextField txtOrderId;
    public TextField txtCustomerIdInput;
    public TextField txtCustomerShow;
    public TextField txtItemId;
    public TextField txtDiscount;
    public TextField txtItemShow;
    public TextField txtQty;
    public TableView<OrderDetailDto> tblOrder;
    public TableColumn<OrderDetailDto, String> colItemCode;
    public TableColumn<OrderDetailDto, Integer> colQty;
    public TableColumn<OrderDetailDto, Integer> colDiscount;

    private CustomerServiceImpl customerService = new CustomerServiceImpl();
    private ItemServiceImpl itemService = new ItemServiceImpl();
    private OrderServiceImpl orderService = new OrderServiceImpl();
    private ObservableList<OrderDetailDto> orderDetails = FXCollections.observableArrayList();

    public void initialize() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
    }

    public void customerSearchOnAction(ActionEvent actionEvent) {
        try{
            String customerId = txtCustomerIdInput.getText();
            CustomerDto customer = customerService.getCustomer(customerId);

            if(customer != null){
                txtCustomerShow.setText(customer.getTitle()+". "+customer.getName()+" | "+customer.getCity());
            }else {
                txtCustomerShow.setText("Customer not fount!");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void itemSearchOnAction(ActionEvent actionEvent) {
    }

    public void itemAddOnAction(ActionEvent actionEvent) {
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {

    }
}
