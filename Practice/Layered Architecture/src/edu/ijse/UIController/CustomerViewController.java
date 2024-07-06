package edu.ijse.UIController;

import edu.ijse.dto.CustomerDto;
import edu.ijse.service.custom.impl.CustomerServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

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

    public TableView<CustomerDto> customerTable;
    public TableColumn<CustomerDto,String> colId;
    public TableColumn<CustomerDto,String> colTitle;
    public TableColumn<CustomerDto,String> colName;
    public TableColumn<CustomerDto,String> colDoB;
    public TableColumn<CustomerDto,String> colSalary;
    public TableColumn<CustomerDto,String> colAddress;
    public TableColumn<CustomerDto,String> colCity;
    public TableColumn<CustomerDto,String> colProvince;
    public TableColumn<CustomerDto,String> colPostal;

    private final CustomerServiceImpl customerService = new CustomerServiceImpl();
    private final ObservableList<CustomerDto> customerList = FXCollections.observableArrayList();

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDoB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostal.setCellValueFactory(new PropertyValueFactory<>("postal"));

        loadCustomer();

        // Add listener to the table selection
        customerTable.setOnMouseClicked(this::selectValue);
    }

    public void saveOnAction(ActionEvent actionEvent) {
        CustomerDto customer = new CustomerDto(
                txtId.getText(),
                txtTitle.getText(),
                txtName.getText(),
                txtDob.getText(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostal.getText()
        );

        try {
            String result = customerService.save(customer);
            if ("Success".equals(result)) {
                customerList.add(customer);
                customerTable.refresh();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Customer Saved...!").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Customer Save Failed...").show();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        CustomerDto customer = new CustomerDto(
                txtId.getText(),
                txtTitle.getText(),
                txtName.getText(),
                txtDob.getText(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostal.getText()
        );

        try {
            String result = customerService.update(customer);
            if ("Success".equals(result)) {
                loadCustomer();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Customer Updated...!").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Customer Update Failed...").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String customerId = txtId.getText();

        try {
            String result = customerService.delete(customerId);
            if ("Success".equals(result)) {
                loadCustomer();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted...!").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Customer Delete Failed...").show();
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void loadCustomer() {
        try {
            ArrayList<CustomerDto> customers = customerService.getAll();
            if (customers != null) {
                customerList.setAll(customers);
                customerTable.setItems(customerList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtId.clear();
        txtTitle.clear();
        txtName.clear();
        txtDob.clear();
        txtSalary.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostal.clear();
    }

    private void selectValue(MouseEvent mouseEvent) {
        CustomerDto selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            txtId.setText(selectedCustomer.getId());
            txtTitle.setText(selectedCustomer.getTitle());
            txtName.setText(selectedCustomer.getName());
            txtDob.setText(selectedCustomer.getDob());
            txtSalary.setText(String.valueOf(selectedCustomer.getSalary()));
            txtAddress.setText(selectedCustomer.getAddress());
            txtCity.setText(selectedCustomer.getCity());
            txtProvince.setText(selectedCustomer.getProvince());
            txtPostal.setText(selectedCustomer.getPostal());
        }
    }
}
